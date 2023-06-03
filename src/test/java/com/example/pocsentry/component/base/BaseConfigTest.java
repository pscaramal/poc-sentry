package com.example.pocsentry.component.base;

import com.example.pocsentry.component.annotation.RunMongoOperationsExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ExtendWith(value = {SpringExtension.class, RunMongoOperationsExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@Testcontainers
abstract public class BaseConfigTest {

  private static final String MONGO_IMAGE = "mongo:6.0.6";
  private static final int MONGO_PORT = 27017;

  @Autowired
  protected ReactiveMongoTemplate mongoTemplate;

  public static final MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse(MONGO_IMAGE))
      .withExposedPorts(MONGO_PORT);

  static {
    mongoDBContainer.start();
  }

  @DynamicPropertySource
  static void setUp(DynamicPropertyRegistry registry){

    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }
}
