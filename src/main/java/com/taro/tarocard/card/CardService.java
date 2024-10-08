package com.taro.tarocard.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public List<Card> getCard () {
        return this.cardRepository.findAll();
    }
    public Card getCardById (Integer id){
        return this.cardRepository.findAllById(id);
    }
}
