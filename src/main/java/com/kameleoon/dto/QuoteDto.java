package com.kameleoon.dto;

import com.kameleoon.entity.QuoteEntity;
import com.kameleoon.entity.ScoreEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class QuoteDto {
    public String content;
    public Timestamp modifyAt;
    public double score;
    public String user;
    public static QuoteDto toDto(QuoteEntity quote) {
        QuoteDto dto = new QuoteDto();
        dto.content = quote.getContent();
        dto.modifyAt = quote.getModifyAt();
        if (quote.getScores().isEmpty()) {
            dto.score = 0;
        } else {
            System.out.println();
            List<ScoreEntity> scoreEntities = quote.getScores();
            Map<String, Timestamp> map = new HashMap<>();
            scoreEntities.forEach(score -> {
                if (
                        !map.containsKey(score.getUser().getLogin()) ||
                        map.get(score.getUser().getLogin()).before(score.getModifyAt())
                ) {
                    map.put(score.getUser().getLogin(), score.getModifyAt());
                }
            });
            scoreEntities = scoreEntities.stream().filter(score -> map.containsKey(score.getUser().getLogin()) && map.containsValue(score.getModifyAt())).toList();

            dto.score = scoreEntities.stream().mapToInt(score -> score.getScore()).average().getAsDouble();
        }
        dto.user = quote.getUser().getLogin();
        return dto;
    }
}
