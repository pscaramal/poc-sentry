package com.example.pocsentry.service;

import com.example.pocsentry.controller.payload.RuleRequestDTO;
import com.example.pocsentry.repository.RuleRepository;
import com.example.pocsentry.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RuleService {

  @Autowired
  private RuleRepository repository;

  public Mono<Rule> insertRule(RuleRequestDTO request){

    return repository.save(Rule.fromDTO(request));
  }
}
