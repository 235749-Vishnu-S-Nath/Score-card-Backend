package com.ust.controller;

import com.ust.dto.QuestionDto;
import com.ust.entity.Question;
import com.ust.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDto> saveQuestion(@RequestBody QuestionDto questionDto){
        final var question = questionService.getQuestion(questionDto.questionId());
        if(question.isPresent()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(entityToDto(questionService.saveQuestion(dtoToEntity(questionDto))));
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions(){
        final var questions = questionService.getQuestions();
        if(questions.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(questions.stream().map(this::entityToDto).toList());
    }

    public Question dtoToEntity(QuestionDto questionDto){
        return new Question(questionDto.questionId(),questionDto.question());
    }
    public QuestionDto entityToDto(Question question){
        return new QuestionDto(question.getQuestionId(),question.getQuestion());
    }
}
