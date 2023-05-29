package com.example.pocsentry.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.example.pocsentry.controller.payload.RuleRequestDTO;
import com.example.pocsentry.model.Rule;
import com.example.pocsentry.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rules")
public class RuleController {

  @Autowired
  private RuleService service;

  @PostMapping
  @ResponseStatus(value = CREATED)
  public Mono<Rule> createRule(@RequestBody RuleRequestDTO request){
    return service.insertRule(request);
  }

  @GetMapping
  public void getRules(){

  }
}
