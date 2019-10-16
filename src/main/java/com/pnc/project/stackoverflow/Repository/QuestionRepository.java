package com.pnc.project.stackoverflow.Repository;

import com.pnc.project.stackoverflow.Entity.Question;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface QuestionRepository extends PagingAndSortingRepository<Question, String> {

    @Query("{'answers.id' : ?0}")
    Question findQuestionByAnswerId(Long id);

}
