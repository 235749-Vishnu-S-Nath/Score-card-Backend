package com.ust.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "answers")
public class Answer {
    @Id
    private long answerId;
    private float mark;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
