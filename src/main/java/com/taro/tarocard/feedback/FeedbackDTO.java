package com.taro.tarocard.feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class FeedbackDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FeedbackRequest {
        private String content; // 피드백 내용
        private int rating; // 별점
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FeedbackResponse {
        private Long id; // 피드백 ID
        private String nickname; // 작성자 닉네임
        private String profileImageUrl; // 작성자 프로필 이미지 URL
        private String content; // 피드백 내용
        private int rating; // 별점
        private int likes; // 추천 수
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentRequest {
        private String content; // 댓글 내용
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponse {
        private Long id; // 댓글 ID
        private String nickname; // 작성자 닉네임
        private String content; // 댓글 내용
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FeedbackDetailResponse {
        private Long id; // 피드백 ID
        private String nickname; // 작성자 닉네임
        private String profileImageUrl; // 작성자 프로필 이미지 URL
        private String content; // 피드백 내용
        private int rating; // 별점
        private int likes; // 추천 수
        private List<CommentResponse> comments; // 댓글 리스트
    }
}

