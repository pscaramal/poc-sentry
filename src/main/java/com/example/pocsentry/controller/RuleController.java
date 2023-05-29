package com.example.pocsentry.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.example.pocsentry.controller.payload.RuleRequestDTO;
import com.example.pocsentry.controller.payload.RuleResponseDTO;
import com.example.pocsentry.service.RuleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public Mono<RuleResponseDTO> createRule(@RequestBody RuleRequestDTO request){
    return service.insertRule(request);
  }

  @GetMapping
  @ResponseStatus(value = OK)
  public Mono<List<RuleResponseDTO>> getRules(@RequestParam(name = "segment", required = false) String segment,
                                              @RequestParam(name = "active", required = false) String active,
                                              @RequestParam(name = "type", required = false) String type,
                                              @RequestParam(name = "merchantId", required = false) String merchantId) {
    return service.getRules(segment, active, type, merchantId);
  }

  @GetMapping("/{id}")
  @ResponseStatus(value = OK)
  public Mono<RuleResponseDTO> getRuleById(@PathVariable String id){
    return service.getRuleById(id);
  }
}
