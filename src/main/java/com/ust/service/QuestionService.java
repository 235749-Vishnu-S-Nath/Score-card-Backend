package com.ust.service;

import com.ust.entity.Question;
import com.ust.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getQuestion(long l) {
        return questionRepository.findById(l);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }
}
