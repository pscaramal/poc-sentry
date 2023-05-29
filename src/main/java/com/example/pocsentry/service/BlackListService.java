package com.example.pocsentry.service;

import com.example.pocsentry.controller.payload.BlacklistFieldRequestDTO;
import com.example.pocsentry.controller.payload.BlacklistFieldResponseDTO;
import com.example.pocsentry.model.blacklist.BlackListField;
import com.example.pocsentry.repository.postgresql.BlacklistRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BlackListService {

  @Autowired
  private BlacklistRepository repository;

  public Mono<BlacklistFieldResponseDTO> insertBlacklistField(BlacklistFieldRequestDTO requestDTO) {
    return this.repository.save(new BlackListField(requestDTO.getFieldName()))
        .map(BlacklistFieldResponseDTO::fromModel);
  }

  public Mono<List<BlacklistFieldResponseDTO>> getAllBlackListFields() {
    return this.repository.findAll()
        .map(BlacklistFieldResponseDTO::fromModel)
        .collectList();
  }
}
