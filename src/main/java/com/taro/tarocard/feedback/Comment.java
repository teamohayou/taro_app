package com.taro.tarocard.feedback;

import com.taro.tarocard.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;


    public Comment(Feedback feedback, String content, String username) {
        this.feedback = feedback;
        this.content = content;
        this.username = username;
        this.createdAt = LocalDateTime.now();
    }


    public void updateContent(String newContent) {
        this.content = newContent;
        this.createdAt = LocalDateTime.now();
    }
}