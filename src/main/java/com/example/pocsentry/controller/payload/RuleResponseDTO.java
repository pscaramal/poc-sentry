package com.example.pocsentry.controller.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RuleResponseDTO {

  private final String id;

  private final String ruleName;

  private final String ruleType;

  private final String segment;

  private final Boolean active;

  private final List<ValidationDTO> validations;

  private RuleResponseDTO(String id, String ruleName, String ruleType, String segment, Boolean active, List<ValidationDTO> validations) {
    this.id = id;
    this.ruleName = ruleName;
    this.ruleType = ruleType;
    this.segment = segment;
    this.active = active;
    this.validations = validations;
  }

  public static RuleResponseBuilder builder(){
    return new RuleResponseBuilder();
  }

  public static class RuleResponseBuilder{
    private String id;

    private String ruleName;

    private String ruleType;

    private String segment;

    private Boolean active;

    private List<ValidationDTO> validations;

    public RuleResponseBuilder id(String id){
      this.id = id;
      return this;
    }

    public RuleResponseBuilder ruleName(String ruleName){
      this.ruleName = ruleName;
      return this;
    }

    public RuleResponseBuilder ruleType(String ruleType){
      this.ruleType = ruleType;
      return this;
    }

    public RuleResponseBuilder segment(String segment){
      this.segment = segment;
      return this;
    }

    public RuleResponseBuilder isActive(boolean active){
      this.active = active;
      return this;
    }

    public RuleResponseBuilder validations(List<ValidationDTO> validations){
      this.validations = validations;
      return this;
    }

    public RuleResponseDTO build(){
      return new RuleResponseDTO(this.id, this.ruleName, this.ruleType, this.segment, this.active, this.validations);
    }
  }
}
