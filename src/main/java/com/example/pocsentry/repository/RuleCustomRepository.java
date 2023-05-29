package com.example.pocsentry.repository;

import com.example.pocsentry.model.Rule;
import reactor.core.publisher.Flux;

public interface RuleCustomRepository {

  Flux<Rule> findRulesByProperties(String segment, String active, String type, String merchantId);
}
