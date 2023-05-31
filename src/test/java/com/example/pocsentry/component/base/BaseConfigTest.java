package com.example.pocsentry.component.base;

import java.time.Duration;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public class BaseConfigTest {

  private static final String MONGO_IMAGE = "mongo:6.0.6";
  private static final String USERNAME = "app_user";
  private static final String PASSWORD = "app_password";
  private static final int MONGO_PORT = 27017;
  private static final String TEST_DATABASE = "poc-sentry-db";

  //@Container
  public static final GenericContainer<?> MONGODB = new GenericContainer<>(DockerImageName.parse(MONGO_IMAGE))
      .withEnv("MONGO_INITDB_ROOT_USERNAME", USERNAME)
      .withEnv("MONGO_INITDB_ROOT_PASSWORD", PASSWORD)
      .withEnv("MONGO_INITDB_DATABASE", TEST_DATABASE)
      //.withFileSystemBind(MountableFile.forClasspathResource("users.js"), "docker-entrypoint-initdb.d/users.js:ro")
      .withExposedPorts(MONGO_PORT)
      .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));

  private static final Logger LOGGER = LoggerFactory.getLogger(BaseConfigTest.class);

  static {
    MONGODB.start();
    Slf4jLogConsumer logConsumer = new Slf4jLogConsumer(LOGGER);
    MONGODB.followOutput(logConsumer);
  }
  @DynamicPropertySource
  static void setUp(DynamicPropertyRegistry registry){


    final String uri = "mongodb://"+USERNAME+":"+PASSWORD+"@"+MONGODB.getHost()+":"+MONGODB.getMappedPort(27017)+"/"+TEST_DATABASE;
    System.out.println(uri);

    registry.add("spring.data.mongodb.uri", () -> uri);
  }
}
