package com.taro.tarocard.feedback;

import com.taro.tarocard.feedback.FeedbackDTO.FeedbackRequest;
import com.taro.tarocard.feedback.FeedbackDTO.FeedbackResponse;
import com.taro.tarocard.feedback.FeedbackDTO.CommentRequest;
import com.taro.tarocard.feedback.FeedbackDTO.FeedbackDetailResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/write")
    public ResponseEntity<String> writeFeedback(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody FeedbackRequest feedbackRequest) {
        feedbackService.writeFeedback(userDetails.getUsername(), feedbackRequest);
        return ResponseEntity.ok("Feedback submitted successfully.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<FeedbackResponse>> getAllFeedbacks() {
        List<FeedbackResponse> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping("/{feedbackId}/comment")
    public ResponseEntity<String> writeComment(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long feedbackId,
            @RequestBody CommentRequest commentRequest) {
        feedbackService.writeComment(userDetails.getUsername(), feedbackId, commentRequest);
        return ResponseEntity.ok("Comment submitted successfully.");
    }

    @PostMapping("/{feedbackId}/like")
    public ResponseEntity<String> likeFeedback(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long feedbackId) {
        feedbackService.likeFeedback(userDetails.getUsername(), feedbackId);
        return ResponseEntity.ok("Feedback liked successfully.");
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity<FeedbackDetailResponse> getFeedbackDetail(@PathVariable Long feedbackId) {
        FeedbackDetailResponse feedbackDetail = feedbackService.getFeedbackDetail(feedbackId);
        return ResponseEntity.ok(feedbackDetail);
    }

    // DELETE 메서드로 수정
    @DeleteMapping("/{feedbackId}") // 변경된 부분
    public ResponseEntity<String> deleteFeedback(@PathVariable Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.ok("Feedback deleted successfully.");
    }
}
