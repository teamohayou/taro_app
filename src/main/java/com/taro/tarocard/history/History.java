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

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryname;

    @Column(nullable = false)
    private String cardname;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime savedAt;

    public History(SiteUser user, RomanticCard card, String cardname, String description, Category category, LocalDateTime savedAt) {
        this.user = user;
        this.card = card;
        this.cardname = cardname;
        this.description = description;
        this.categoryname = categoryname;
        this.savedAt = savedAt;
    }
}