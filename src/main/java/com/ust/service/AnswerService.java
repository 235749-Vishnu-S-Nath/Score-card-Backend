package com.ust.service;

import com.ust.entity.Answer;
import com.ust.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public Optional<Answer> getAnswer(long l) {
        return answerRepository.findById(l);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }
}
