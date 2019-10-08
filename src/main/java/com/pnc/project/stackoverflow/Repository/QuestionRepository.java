package com.pnc.project.stackoverflow.Repository;

import com.pnc.project.stackoverflow.Entity.Question;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface QuestionRepository extends MongoRepository<Question, String> {

}
