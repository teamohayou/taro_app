package com.taro.tarocard.history;

import com.taro.tarocard.user.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUser(SiteUser user);
}
