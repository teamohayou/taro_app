package com.taro.tarocard.feedback;

import com.taro.tarocard.feedback.FeedbackDTO.CommentRequest;
import com.taro.tarocard.feedback.FeedbackDTO.FeedbackDetailResponse;
import com.taro.tarocard.feedback.FeedbackDTO.FeedbackResponse;
import com.taro.tarocard.user.SiteUser;
import com.taro.tarocard.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    public void writeFeedback(String username, FeedbackDTO.FeedbackRequest feedbackRequest) {
        SiteUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setContent(feedbackRequest.getContent());
        feedback.setRating(feedbackRequest.getRating());
        feedbackRepository.save(feedback);
    }

    public List<FeedbackResponse> getAllFeedbacks() {
        return feedbackRepository.findAllByOrderByIdDesc().stream() // 여기에서 메서드 호출
                .map(feedback -> new FeedbackResponse(
                        feedback.getId(),
                        feedback.getUser().getNickname(),
                        feedback.getUser().getProfileImageUrl(),
                        feedback.getContent(),
                        feedback.getRating(),
                        feedback.getLikes()))
                .collect(Collectors.toList());
    }

    public FeedbackDetailResponse getFeedbackDetail(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        List<FeedbackDTO.CommentResponse> comments = feedback.getComments().stream()
                .map(comment -> new FeedbackDTO.CommentResponse(
                        comment.getId(),
                        comment.getUser().getNickname(),
                        comment.getContent()))
                .collect(Collectors.toList());

        return new FeedbackDetailResponse(
                feedback.getId(),
                feedback.getUser().getNickname(),
                feedback.getUser().getProfileImageUrl(),
                feedback.getContent(),
                feedback.getRating(),
                feedback.getLikes(),
                comments);
    }

    public void writeComment(String username, Long feedbackId, CommentRequest commentRequest) {
        SiteUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setFeedback(feedback);
        comment.setContent(commentRequest.getContent());
        commentRepository.save(comment);
    }

    public void likeFeedback(String username, Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setLikes(feedback.getLikes() + 1);
        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedbackRepository.delete(feedback);
    }
}