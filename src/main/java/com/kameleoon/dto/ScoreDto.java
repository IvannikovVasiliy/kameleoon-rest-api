package com.kameleoon.dto;

import com.kameleoon.entity.ScoreEntity;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScoreDto {
    public int score;
    public Timestamp modifyAt;
    public String user;
    public static ScoreDto toDto(ScoreEntity scoreEntity) {
        ScoreDto scoreDto = new ScoreDto();
        scoreDto.score = scoreEntity.getScore();
        scoreDto.modifyAt = scoreEntity.getModifyAt();
        scoreDto.user = scoreEntity.getUser().getLogin();

        return scoreDto;
    }
}
