package com.taro.tarocard.history;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryRequest {
    private Long userId;
    private Integer cardId;
    private String categoryname;
    private String cardname;
    private String description;
}