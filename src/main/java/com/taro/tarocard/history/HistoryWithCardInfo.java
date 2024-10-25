package com.taro.tarocard.history;

import com.taro.tarocard.card.RomanticCard;

public class HistoryWithCardInfo {
    private final History history;
    private final RomanticCard card;

    public HistoryWithCardInfo(History history, RomanticCard card) {
        this.history = history;
        this.card = card;
    }

    public History getHistory() {
        return history;
    }

    public RomanticCard getCard() {
        return card;
    }
}
