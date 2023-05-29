package com.example.pocsentry.controller.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RuleRequestDTO {

  private String ruleName;

  private String ruleType;

  private String segment;

  private Boolean active;

  private List<ValidationDTO> validations;

  public String getRuleName() {
    return ruleName;
  }

  public String getRuleType() {
    return ruleType;
  }

  public String getSegment() {
    return segment;
  }

  public Boolean getActive() {
    return active;
  }

  public List<ValidationDTO> getValidations() {
    return validations;
  }
}
