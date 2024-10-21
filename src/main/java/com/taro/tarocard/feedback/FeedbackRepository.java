package com.taro.tarocard.feedback;

import com.taro.tarocard.card.CardRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByCardId(Integer id);
}
