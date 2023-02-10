package com.kameleoon.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "scores")
public class ScoreEntity {

    public ScoreEntity(int score, UserEntity user, QuoteEntity quoteEntity) {
        this.score = score;
        this.user = user;
        this.quote = quoteEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private Timestamp modifyAt;

    private int score;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private QuoteEntity quote;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
