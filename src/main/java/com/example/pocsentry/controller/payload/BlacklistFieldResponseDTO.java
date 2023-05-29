package com.example.pocsentry.controller.payload;

import com.example.pocsentry.model.blacklist.BlackListField;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BlacklistFieldResponseDTO {

  private String fieldName;

  private BlacklistFieldResponseDTO(String fieldName){
    this.fieldName = fieldName;
  }
  public static BlacklistFieldResponseDTO fromModel(BlackListField blackListField){
    return new BlacklistFieldResponseDTO(blackListField.getNameField());
  }
}
