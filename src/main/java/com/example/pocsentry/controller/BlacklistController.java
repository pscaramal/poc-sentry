package com.example.pocsentry.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.example.pocsentry.controller.payload.BlacklistFieldRequestDTO;
import com.example.pocsentry.controller.payload.BlacklistFieldResponseDTO;
import com.example.pocsentry.model.blacklist.BlackListField;
import com.example.pocsentry.service.BlackListService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/blacklist")
public class BlacklistController {

  @Autowired
  private BlackListService service;

  @PostMapping("/fields")
  @ResponseStatus(value = CREATED)
  public Mono<BlacklistFieldResponseDTO> insertField(@RequestBody BlacklistFieldRequestDTO request){
    return service.insertBlacklistField(request);
  }

  @GetMapping("/fields")
  @ResponseStatus(value = OK)
  public Mono<List<BlacklistFieldResponseDTO>> getAllFields() {
    return service.getAllBlackListFields();
  }
}
