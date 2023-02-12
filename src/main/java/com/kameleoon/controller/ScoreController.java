package com.kameleoon.controller;

import com.kameleoon.model.ScoreModel;
import com.kameleoon.model.ScoreModelCreation;
import com.kameleoon.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score")
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping
    public List<ScoreModel> getAllScores() {
        return scoreService.getAllScores();
    }

    @PostMapping("/add")
    private String addScore(@RequestBody ScoreModelCreation scoreModelCreation) {
        scoreService.addScore(scoreModelCreation);

        return "Score is ADDED";
    }
}
