package com.example.pocsentry.service;

import com.example.pocsentry.controller.payload.RuleRequestDTO;
import com.example.pocsentry.controller.payload.RuleResponseDTO;
import com.example.pocsentry.repository.RuleRepository;
import com.example.pocsentry.model.Rule;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RuleService {

  @Autowired
  private RuleRepository repository;

  public Mono<RuleResponseDTO> insertRule(RuleRequestDTO request){
    return repository.save(Rule.fromDTO(request))
        .map(Rule::toDTO);
  }

  public Mono<List<RuleResponseDTO>> getRules(String segment, String active, String type, String merchantId) {
    return repository.findRulesByProperties(segment, active, type, merchantId)
        .map(Rule::toDTO)
        .collectList();
  }

  public Mono<RuleResponseDTO> getRuleById(String id) {
    return repository.findById(new ObjectId(id))
        .map(Rule::toDTO);
  }
}
