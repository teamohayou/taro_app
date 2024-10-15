package com.taro.tarocard.feedback;

import com.taro.tarocard.user.SiteUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;

    private String content;
    private int rating; // 별점
    private int likes;

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    private List<Comment> comments;


}

