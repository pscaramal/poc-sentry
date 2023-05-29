package com.example.pocsentry.model;

import com.example.pocsentry.controller.payload.ParamsValidationDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ParamsValidation {

  private List<String> value;

  private String type;

  private ParamsValidation (List<String> value, String type){
    this.value = value;
    this.type = type;
  }

  public static ParamsValidation fromDTO(ParamsValidationDTO dto){
    return new ParamsValidation(dto.getValue(), dto.getType());
  }
}
