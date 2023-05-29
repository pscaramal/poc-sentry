package com.example.pocsentry.controller.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ValidationDTO {

  private String path;

  private String operator;

  private Boolean cardValidation;

  private ParamsValidationDTO paramsValidation;

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
}
