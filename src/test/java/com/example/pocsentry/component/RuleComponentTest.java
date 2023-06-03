package com.example.pocsentry.component;

import com.example.pocsentry.component.annotation.CleanCollection;
import com.example.pocsentry.component.annotation.InsertMany;
import com.example.pocsentry.component.base.BaseConfigTest;
import com.example.pocsentry.controller.payload.ParamsValidationDTO;
import com.example.pocsentry.controller.payload.RuleRequestDTO;
import com.example.pocsentry.controller.payload.RuleResponseDTO;
import com.example.pocsentry.controller.payload.ValidationDTO;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

class RuleComponentTest extends BaseConfigTest {

  @Autowired
  private WebTestClient webTestClient;

  private final static String RULE_URL = "/rules";

  @Test
  void shouldInsertNewRule(){
    webTestClient
        .post()
        .uri(RULE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(buildRequestDTO()), RuleRequestDTO.class)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(RuleResponseDTO.class)
        .consumeWith(ruleResponseDTOExchangeResult -> {
          RuleResponseDTO response = ruleResponseDTOExchangeResult.getResponseBody();
          Assertions.assertNotNull(response);
          Assertions.assertNotNull(response.getId());
        });
  }

  @Test
  @CleanCollection("rules")
  @InsertMany(file = "scripts/insertRules.json", collectionName = "rules")
  void shouldGetAllRules() throws IOException {

    webTestClient
        .get()
        .uri(RULE_URL)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(RuleResponseDTO.class)
        .hasSize(2);
  }

  private RuleRequestDTO buildRequestDTO() {
    return RuleRequestDTO.builder()
        .ruleName("Rule de Teste")
        .ruleType("rpv")
        .segment("BR")
        .isActive(true)
        .validations(List.of(
            ValidationDTO.builder()
                .path("merchant.merchantId")
                .isCardValidation(false)
                .operator("=")
                .paramsValidation(new ParamsValidationDTO(List.of("bd07c6c3-94e2-42a5-b8d5-a166b540e8ba "), null))
                .build(),
            ValidationDTO.builder()
                .path("userDetails.email")
                .operator("=")
                .isCardValidation(false)
                .paramsValidation(new ParamsValidationDTO(List.of("email@teste.com"), null))
                .build()
        ))
        .build();
  }
}
