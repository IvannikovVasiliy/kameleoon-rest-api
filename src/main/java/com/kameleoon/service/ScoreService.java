package com.kameleoon.service;

import com.kameleoon.model.ScoreModel;
import com.kameleoon.model.ScoreModelCreation;

import java.util.List;

public interface ScoreService {
    List<ScoreModel> getAllScores();
    boolean addScore(ScoreModelCreation scoreModelCreation);

}
