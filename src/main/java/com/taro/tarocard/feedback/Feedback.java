package com.taro.tarocard.feedback;

import com.taro.tarocard.card.Card;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    // 나중에 유저 ID 받아오고 글쓴이는 nickname 사용 어떻게될지몰라서 주석처리 씨발
    //private User user;

    @Column(columnDefinition = "text")
    private String content;

    private LocalDateTime createDate;


}
