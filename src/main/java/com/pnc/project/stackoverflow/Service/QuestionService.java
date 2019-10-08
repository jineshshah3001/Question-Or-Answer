package com.pnc.project.stackoverflow.Service;

import com.pnc.project.stackoverflow.Entity.Question;
import org.bson.types.ObjectId;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface QuestionService {

    public List<Question> findAll();

    public void postQuestion(Question question);

    public Optional<Question> findById(String id);


}
