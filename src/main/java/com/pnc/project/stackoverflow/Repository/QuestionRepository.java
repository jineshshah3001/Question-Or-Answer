package com.pnc.project.stackoverflow.Repository;

import com.pnc.project.stackoverflow.Entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface QuestionRepository extends MongoRepository<Question, String> {

    @Query("{'answers.id' : ?0}")
    Question findQuestionByAnswerId(Long id);

}
