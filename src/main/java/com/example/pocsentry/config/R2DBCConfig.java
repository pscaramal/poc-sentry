package com.example.pocsentry.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.example.pocsentry.repository.postgresql")
public class R2DBCConfig extends AbstractR2dbcConfiguration {

  @Value("${spring.r2dbc.username}")
  private String userName;

  @Value("${spring.r2dbc.password}")
  private String password;

  @Value("${spring.r2dbc.host}")
  private String host;

  @Value("${spring.r2dbc.port}")
  private Integer port;

  @Value("${spring.r2dbc.db}")
  private String db;

  @Override
  @Bean(name = "connectionFactoryPostgresql")
  public ConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(
        PostgresqlConnectionConfiguration.builder()
            .host(host)
            .port(port)
            .username(userName)
            .password(password)
            .database(db)
            .build()
    );
  }
}
