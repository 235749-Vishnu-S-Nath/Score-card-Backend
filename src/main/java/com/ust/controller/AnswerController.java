package com.ust.controller;

import com.ust.dto.AnswerDto;
import com.ust.entity.Answer;
import com.ust.entity.Question;
import com.ust.service.AnswerService;
import com.ust.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<AnswerDto> saveAnswer(@RequestBody AnswerDto answerDto){
        final var answer = answerService.getAnswer(answerDto.answerId());
        final var question = questionService.getQuestion(answerDto.questionId());
        if(answer.isPresent() || question.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(entityToDto(answerService.saveAnswer(dtoToEntity(answerDto,question.get()))));
    }

    @GetMapping
    public ResponseEntity<List<AnswerDto>> getAllAnswers(){
        final var answers = answerService.getAnswers();
        if(answers.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(answers.stream().map(this::entityToDto).toList());
    }

    public AnswerDto entityToDto(Answer answer){
        return new AnswerDto(answer.getAnswerId(),answer.getMark(),answer.getQuestion().getQuestionId());
    }
    public Answer dtoToEntity(AnswerDto answerDto, Question question){
        return new Answer(answerDto.answerId(),answerDto.mark(),question);
    }
}