package com.taro.tarocard.history;

import com.taro.tarocard.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String resultDescription;

    private LocalDateTime savedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private SiteUser user;


    private String provider;
}
