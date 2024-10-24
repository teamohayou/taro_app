package com.taro.tarocard.feedback;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 추가적인 커스텀 쿼리가 필요하면 정의할 수 있습니다.
}

