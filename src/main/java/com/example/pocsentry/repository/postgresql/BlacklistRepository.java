package com.example.pocsentry.repository.postgresql;

import com.example.pocsentry.model.blacklist.BlackListField;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistRepository extends ReactiveCrudRepository<BlackListField, Integer> {

}
