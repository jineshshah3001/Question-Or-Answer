package com.pnc.project.stackoverflow.Repository;

import com.pnc.project.stackoverflow.Entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerRepository extends MongoRepository<Answer, Long> {
}
