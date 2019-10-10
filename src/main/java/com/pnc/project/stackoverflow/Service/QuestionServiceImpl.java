package com.pnc.project.stackoverflow.Service;


import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAll() {

        return questionRepository.findAll();
    }


    @Override
    public void postQuestion(Question question) {

        questionRepository.save(question);
    }

    @Override
    public Optional<Question> findById(String id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question findQuestionByAnswerId(long id){
        return questionRepository.findQuestionByAnswerId(id);
    }
}
