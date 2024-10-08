package com.taro.tarocard.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findAllById(Integer id);
}
