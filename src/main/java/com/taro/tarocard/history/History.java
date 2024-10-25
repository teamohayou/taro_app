package com.taro.tarocard.history;

import com.taro.tarocard.card.Category;
import com.taro.tarocard.card.RomanticCard;
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
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SiteUser user;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private RomanticCard card;

    @Column(nullable = false)
    private LocalDateTime savedAt;

    @Column(name = "categoryname", nullable = false)
    private String categoryname;

    public History(SiteUser user, RomanticCard card, LocalDateTime savedAt, String categoryname) {
        this.user = user;
        this.card = card;
        this.savedAt = savedAt;
        this.categoryname = categoryname;
    }
}