package com.pnc.project.stackoverflow.Service;


import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

//    @Override
//    public List<Question> findAll() {
//
//        return questionRepository.findAll();
//    }


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

    @Override
    public Page<Question> getAllQuestions(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo,pageSize);
        Page<Question> pageResult = questionRepository.findAll(paging);
        System.out.println(pageResult.getTotalPages());
       return pageResult;

    }
}
