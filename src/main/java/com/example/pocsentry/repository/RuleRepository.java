package com.example.pocsentry.repository;

import com.example.pocsentry.model.Rule;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends ReactiveMongoRepository<Rule, ObjectId>, RuleCustomRepository{
}
