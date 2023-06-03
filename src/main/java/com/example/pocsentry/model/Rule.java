package com.example.pocsentry.model;

import com.example.pocsentry.controller.payload.RuleRequestDTO;
import com.example.pocsentry.controller.payload.RuleResponseDTO;
import com.example.pocsentry.controller.payload.ValidationDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Document(collection = "rules")
public class Rule {

  @Id
  private ObjectId id;

  private final String ruleName;

  private final String ruleType;

  @Indexed
  private final String segment;

  private final Boolean active;

  private final List<Validation> validations;

  public String getRuleId() {
    return this.id.toString();
  }

  public String getRulename() {
    return this.ruleName;
  }

  private Rule(String ruleName, String ruleType, String segment, Boolean active, List<Validation> validations) {
    this.ruleName = ruleName;
    this.ruleType = ruleType;
    this.segment = segment;
    this.active = active;
    this.validations = validations;
  }
  public static Rule fromDTO(RuleRequestDTO dto) {
    return new RuleBuilder()
        .ruleName(dto.getRuleName())
        .ruleType(dto.getRuleType())
        .segment(dto.getSegment())
        .isActive(dto.getActive())
        .validations(dto.getValidations()
            .stream()
            .map(Validation::fromDTO)
            .collect(Collectors.toList()))
        .build();
  }

  public RuleResponseDTO toDTO() {
    return RuleResponseDTO.builder()
        .id(this.id.toString())
        .ruleName(this.ruleName)
        .ruleType(this.ruleType)
        .segment(this.segment)
        .isActive(this.active)
        .validations(this.validations.stream()
            .map(Validation::toDTO)
            .collect(Collectors.toList()))
        .build();
  }

  public static class RuleBuilder{
    private String ruleName;

    private String ruleType;

    private String segment;

    private Boolean active;

    private List<Validation> validations;

    public RuleBuilder ruleName(String ruleName){
      this.ruleName = ruleName;
      return this;
    }

    public RuleBuilder ruleType(String ruleType){
      this.ruleType = ruleType;
      return this;
    }

    public RuleBuilder segment(String segment){
      this.segment = segment;
      return this;
    }

    public RuleBuilder validations(List<Validation> validations) {
      this.validations = validations;
      return this;
    }

    public RuleBuilder isActive(boolean active){
      this.active = active;
      return this;
    }
    public Rule build(){
      return new Rule(this.ruleName, this.ruleType, this.segment, this.active, this.validations);
    }
  }
}
