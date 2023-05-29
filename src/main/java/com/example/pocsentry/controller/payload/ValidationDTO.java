package com.example.pocsentry.controller.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationDTO {

  private final String path;

  private final String operator;

  private final Boolean cardValidation;

  private final ParamsValidationDTO paramsValidation;

  private ValidationDTO(String path, String operator, Boolean cardValidation, ParamsValidationDTO paramsValidation) {
    this.path = path;
    this.operator = operator;
    this.cardValidation = cardValidation;
    this.paramsValidation = paramsValidation;
  }

  public String getPath() {
    return path;
  }

  public String getOperator() {
    return operator;
  }

  public Boolean getCardValidation() {
    return cardValidation;
  }

  public ParamsValidationDTO getParamsValidation() {
    return paramsValidation;
  }

  public static ValidationDTOBuilder builder(){
    return new ValidationDTOBuilder();
  }

  public static class ValidationDTOBuilder {
    private String path;

    private String operator;

    private Boolean cardValidation;

    private ParamsValidationDTO paramsValidation;

    public ValidationDTOBuilder path(String path){
      this.path = path;
      return this;
    }

    public ValidationDTOBuilder operator(String operator){
      this.operator = operator;
      return this;
    }

    public ValidationDTOBuilder isCardValidation(boolean cardValidation){
      this.cardValidation = cardValidation;
      return this;
    }

    public ValidationDTOBuilder paramsValidation(ParamsValidationDTO paramsValidation){
      this.paramsValidation = paramsValidation;
      return this;
    }

    public ValidationDTO build(){
      return new ValidationDTO(this.path, this.operator, this.cardValidation, this.paramsValidation);
    }
  }
}
