package com.kameleoon.model;

import com.kameleoon.dto.ScoreDto;
import com.kameleoon.dto.UserDto;
import com.kameleoon.entity.QuoteEntity;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class QuoteModel {
    private String content;
    private Timestamp modifyAt;
    private UserDto userDto;
    private List<ScoreDto> scores;

    public static QuoteModel fromEntity(QuoteEntity quoteEntity) {
        return QuoteModel
                .builder()
                .content(quoteEntity.getContent())
                .modifyAt(quoteEntity.getModifyAt())
                .userDto(UserDto.toDto(quoteEntity.getUser()))
                .scores(quoteEntity.getScores().stream().map(score -> ScoreDto.toDto(score)).toList())
                .build();
    }
}
