package com.example.pocsentry.model.blacklist;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("tb_risk_blacklist_fields")
public class BlackListField {

  @Id
  @Column("id_field")
  private Integer id;

  @Column("name_field")
  private String nameField;

  protected BlackListField(){}

  public BlackListField(String nameField){
    this.nameField = nameField;
  }

  public String getNameField() {
    return this.nameField;
  }
}
