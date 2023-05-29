package com.example.pocsentry.repository;

import com.example.pocsentry.model.Rule;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public class RuleCustomRepositoryImpl implements RuleCustomRepository{

  @Autowired
  private ReactiveMongoTemplate mongoTemplate;

  @Override
  public Flux<Rule> findRulesByProperties(String segment, String active, String type, String merchantId) {
    final Query query = new Query();
    final List<Criteria> criterias = new ArrayList<>();

    if (segment != null && !segment.isBlank()){
      criterias.add(Criteria.where("segment").is(segment));
    }

    if (active != null && !active.isBlank()) {
      criterias.add(Criteria.where("active").is(Boolean.valueOf(active)));
    }

    if (type != null && !type.isBlank()) {
      criterias.add(Criteria.where("rule_type").is(type));
    }

    if (merchantId != null && !merchantId.isBlank()){
      Criteria criteria = new Criteria();
      criterias.add(criteria.orOperator(
          Criteria.where("validations.path").is("merchant.merchantId")
          .and("validations.params_validation.value").is(merchantId),
          Criteria.where("validations.path").ne("merchant.merchantId")));
    }

    if (criterias.isEmpty()) {
      return mongoTemplate.findAll(Rule.class);
    }

    query.addCriteria(new Criteria().andOperator(criterias.toArray(new Criteria[0])));
    return mongoTemplate.find(query, Rule.class);
  }
}
