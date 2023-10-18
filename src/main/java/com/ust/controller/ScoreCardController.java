package com.ust.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/scorecard")
public class ScoreCardController {
    @GetMapping("/data")
    public ResponseEntity<String> getData(){
        return ResponseEntity.ok().body("Success");
    }
}
