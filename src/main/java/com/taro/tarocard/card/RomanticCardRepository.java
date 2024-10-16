package com.taro.tarocard.card;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RomanticCardRepository extends JpaRepository<RomanticCard, Integer> {

    List<RomanticCard> findAllByCardId(Integer id);

    Optional<RomanticCard> findAllById(Integer id);
}
