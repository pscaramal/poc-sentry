package com.example.pocsentry.controller.payload;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ParamsValidationDTO {

  private List<String> value;

  private String type;

  public List<String> getValue() {
    return value;
  }

  public String getType() {
    return type;
  }
}
