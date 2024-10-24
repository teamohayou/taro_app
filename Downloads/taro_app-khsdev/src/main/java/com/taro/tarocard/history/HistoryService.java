package com.taro.tarocard.history;

import com.taro.tarocard.card.RomanticCard;
import com.taro.tarocard.card.RomanticCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final RomanticCardRepository romanticCardRepository;

    @Transactional
    public void save(History history) {
        historyRepository.save(history);
    }

    public List<History> findByUserId(Long userId) {
        return historyRepository.findByUserId(userId);
    }
    @Transactional
    public RomanticCard findById(Integer cardId) {
        return romanticCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("카드를 찾을 수 없습니다. cardId: " + cardId));
    }

}
