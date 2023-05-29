package com.example.pocsentry.model;

import com.example.pocsentry.controller.payload.ValidationDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Validation {

  private String path;

  private String operator;

  private Boolean cardValidation;

  private ParamsValidation paramsValidation;

  private Validation(String path, String operator, Boolean cardValidation, ParamsValidation paramsValidation) {
    this.path = path;
    this.operator = operator;
    this.cardValidation = cardValidation;
    this.paramsValidation = paramsValidation;
  }

  public static Validation fromDTO(ValidationDTO dto){
    return new ValidationBuilder()
        .path(dto.getPath())
        .operator(dto.getOperator())
        .cardValidation(dto.getCardValidation())
        .paramsValidation(ParamsValidation.fromDTO(dto.getParamsValidation()))
        .build();
  }

  public static class ValidationBuilder{
    private String path;

    private String operator;

    private Boolean cardValidation;

    private ParamsValidation paramsValidation;

    public ValidationBuilder path(String path) {
      this.path = path;
      return this;
    }

    public ValidationBuilder operator(String operator){
      this.operator = operator;
      return this;
    }

    public ValidationBuilder cardValidation(boolean cardValidation){
      this.cardValidation = cardValidation;
      return this;
    }

    public ValidationBuilder paramsValidation(ParamsValidation paramsValidation){
      this.paramsValidation = paramsValidation;
      return this;
    }

    public Validation build(){
      return new Validation(this.path, this.operator, this.cardValidation, this.paramsValidation);
    }
  }
}
