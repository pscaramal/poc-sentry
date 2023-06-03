package com.example.pocsentry.controller.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RuleRequestDTO {

  private final String ruleName;

  private final String ruleType;

  private final String segment;

  private final Boolean active;

  private final List<ValidationDTO> validations;

  private RuleRequestDTO(String ruleName, String ruleType, String segment, Boolean active, List<ValidationDTO> validations) {
    this.ruleName = ruleName;
    this.ruleType = ruleType;
    this.segment = segment;
    this.active = active;
    this.validations = validations;
  }

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

  public static RuleRequestBuilder builder(){
    return new RuleRequestBuilder();
  }

  public static class RuleRequestBuilder {

    private String ruleName;

    private String ruleType;

    private String segment;

    private Boolean active;

    private List<ValidationDTO> validations;

    public RuleRequestBuilder ruleName(String ruleName){
      this.ruleName = ruleName;
      return this;
    }

    public RuleRequestBuilder ruleType(String ruleType){
      this.ruleType = ruleType;
      return this;
    }

    public RuleRequestBuilder segment(String segment){
      this.segment = segment;
      return this;
    }

    public RuleRequestBuilder isActive(boolean active){
      this.active = active;
      return this;
    }

    public RuleRequestBuilder validations(List<ValidationDTO> validations){
      this.validations = validations;
      return this;
    }

    public RuleRequestDTO build(){
      return new RuleRequestDTO(this.ruleName, this.ruleType, this.segment, this.active, this.validations);
    }
  }
}
