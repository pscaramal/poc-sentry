CREATE TABLE IF NOT EXISTS tb_risk_blacklist (
id INTEGER not null,
field_value VARCHAR(150) NOT NULL,
primary key (id, field_value),
constraint fk_id
  foreign key (id) references tb_risk_blacklist_fields (id_field)
);