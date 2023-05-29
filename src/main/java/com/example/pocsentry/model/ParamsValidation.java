package com.example.pocsentry.model;

import com.example.pocsentry.controller.payload.ParamsValidationDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ParamsValidation {

  private final List<String> value;

  private final String type;

  private ParamsValidation (List<String> value, String type){
    this.value = value;
    this.type = type;
  }

  public static ParamsValidation fromDTO(ParamsValidationDTO dto){
    return new ParamsValidation(dto.value(), dto.type());
  }

  public ParamsValidationDTO toDTO(){
    return new ParamsValidationDTO(this.value, this.type);
  }
}
