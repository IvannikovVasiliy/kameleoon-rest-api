package com.kameleoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "quotes")
@NoArgsConstructor
public class QuoteEntity {

    public QuoteEntity(String content, UserEntity userEntity) {
        this.content = content;
        this.user = userEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "modify_at")
    @UpdateTimestamp
    private Timestamp modifyAt;

    @OneToMany
    @JoinColumn(name = "quote_id")
    private List<ScoreEntity> scores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
