package com.taro.tarocard.feedback;


import com.taro.tarocard.user.SiteUser;
import com.taro.tarocard.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserService userService;

    public List<Feedback> getlist() {
        return feedbackRepository.findAllByOrderByCreatedAtDesc();
    }

    public void saveFeedback(FeedbackForm form, SiteUser user) {
        Feedback feedback = new Feedback();
        feedback.setTitle(form.getTitle());
        feedback.setContent(form.getContent());
        feedback.setRating(form.getRating());
        feedback.setUser(userService.getCurrentUser());
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setCommentCount(0);
        feedbackRepository.save(feedback);
    }

    public void updateFeedback(Long id, FeedbackForm form) {
        Feedback feedback = findById(id); // 피드백을 ID로 찾습니다.

        // 현재 사용자와 피드백의 소유자가 일치하는지 확인합니다.
        if (!feedback.getUser().equals(userService.getCurrentUser())) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        // 피드백의 필드 업데이트
        feedback.setTitle(form.getTitle());
        feedback.setContent(form.getContent());
        feedback.setRating(form.getRating());
        feedback.setUpdateAt(LocalDateTime.now());

        feedbackRepository.save(feedback); // 피드백을 업데이트합니다.
    }

    public Feedback findById(Long id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("피드백을 찾을 수 없습니다."));
    }

    public void deleteFeedback(Long id) {
        Feedback feedback = findById(id);
        if (!feedback.getUser().equals(userService.getCurrentUser())) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        feedbackRepository.delete(feedback);
    }

    public void addLike(Feedback feedback) {
        feedback.setLikes(feedback.getLikes() + 1);
        feedbackRepository.save(feedback);
    }

    public void incrementCommentCount(Long feedbackId) {
        Feedback feedback = findById(feedbackId);
        feedback.setCommentCount(feedback.getCommentCount() + 1);
        feedbackRepository.save(feedback);
    }

    public void decrementCommentCount(Long feedbackId) {
        Feedback feedback = findById(feedbackId);
        feedback.setCommentCount(feedback.getCommentCount() - 1);
        feedbackRepository.save(feedback);
    }
}