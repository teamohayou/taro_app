package com.taro.tarocard.history;

import com.taro.tarocard.user.SiteUser;
import com.taro.tarocard.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    @Transactional
    public void saveHistory(String nickname, String resultDescription) {
        SiteUser user = userRepository.findByUsername(nickname)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        History history = new History();
        history.setUser(user);
        history.setResultDescription(resultDescription);
        history.setSavedDate(LocalDateTime.now());

        historyRepository.save(history);
    }

    @Transactional(readOnly = true)
    public List<History> findHistoriesByUser(SiteUser user) {
        return historyRepository.findByUser(user); // 사용자의 히스토리 목록 조회
    }
}
