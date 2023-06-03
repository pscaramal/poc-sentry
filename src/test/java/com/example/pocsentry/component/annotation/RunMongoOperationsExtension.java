package com.example.pocsentry.component.annotation;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClients;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.util.StreamUtils;
import org.testcontainers.containers.MongoDBContainer;

public class RunMongoOperationsExtension implements BeforeTestExecutionCallback {

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {

    Method testMethod = context.getRequiredTestMethod();
    Annotation[] annotations = testMethod.getAnnotations();
    ReactiveMongoTemplate mongoTemplate = buildReactiveMongoTemplate(getUrlConnection(context));

    for (Annotation annotation : annotations) {
      if(annotation.annotationType() == CleanCollection.class) {
        CleanCollection cleanCollection = (CleanCollection) annotation;
        String collectionName = cleanCollection.value();

        mongoTemplate.dropCollection(collectionName).block();
      }
    }

    for (Annotation annotation : annotations) {
      if(annotation.annotationType() == InsertMany.class) {
        InsertMany insertMany = (InsertMany) annotation;

        String collectionName = insertMany.collectionName();
        String file = insertMany.file();

        List<Document> documents = getDocumentsFrom(file);

        for (Document document : documents){
          mongoTemplate.insert(document, collectionName).block();
        }
      }
    }
  }

  private List<Document> getDocumentsFrom(String file) {
    try {
      Resource resource = new ClassPathResource(file);
      String script = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

      List<Document> documents = new ArrayList<>();

      JSONArray jsonArray = new JSONArray(script);

      for (int i = 0; i < jsonArray.length(); i++){
        documents.add(Document.parse(jsonArray.getJSONObject(i).toString()));
      }

      return documents;
    } catch (IOException e){
      throw new IllegalStateException("Ocorreu um problema ao ler o arquivo");
    } catch (JSONException e){
      throw new IllegalStateException("Verifique se o formato do json está correto");
    }
  }

  private String getUrlConnection(ExtensionContext context) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Class<?> testClass = context.getRequiredTestClass();
    Optional<Field> mongoDBContainerField = findMongoDBContainerField(testClass);
    if (mongoDBContainerField.isPresent()) {
      Field field = mongoDBContainerField.get();
      field.setAccessible(true);
      Object testInstance = context.getRequiredTestInstance();
      Object mongoDBContainer = field.get(testInstance);

      Method getReplicaSetUrl = mongoDBContainer.getClass().getMethod("getReplicaSetUrl");
      return (String) getReplicaSetUrl.invoke(mongoDBContainer);
    } else {
      throw new IllegalStateException("MongoDBContainer field not found");
    }
  }

  private Optional<Field> findMongoDBContainerField(Class<?> testClass) {
    return Arrays.stream(testClass.getFields())
        .filter(field -> field.getType().equals(MongoDBContainer.class))
        .findFirst();
  }

  private ReactiveMongoTemplate buildReactiveMongoTemplate(String connectionUrl) throws URISyntaxException {
    ConnectionString connectionString = new ConnectionString(connectionUrl);
    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build();

    return new ReactiveMongoTemplate(MongoClients.create(mongoClientSettings), getDatabaseName(connectionUrl));
  }

  private String getDatabaseName(String connectionUrl) throws URISyntaxException {
    URI uri = new URI(connectionUrl);

    return uri.getPath().substring(1);
  }
}
