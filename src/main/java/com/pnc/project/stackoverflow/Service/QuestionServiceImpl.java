package com.pnc.project.stackoverflow.Service;


import com.pnc.project.stackoverflow.Entity.Question;
import com.pnc.project.stackoverflow.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return pageResult;

    }

    @Override
    public List<Question> findNewest() {
       List<Question> questions = (List<Question>) questionRepository.findAll();
        Collections.sort(questions, new Comparator<Question>() {
            public int compare(Question q1, Question q2) {
                return q2.getDateAsked().compareTo(q1.getDateAsked());
            }
        });
        return questions;
    }

    @Override
    public List<Question> findOldest() {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        Collections.sort(questions, new Comparator<Question>() {
            public int compare(Question q1, Question q2) {
                return q1.getDateAsked().compareTo(q2.getDateAsked());
            }
        });
        return questions;
    }

    @Override
    public List<Question> findUnanswered() {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        List<Question> filtered = questions.stream().filter(q->q.getNumberOfAnswers()==0).collect(Collectors.toList());
        return filtered;
    }


}
