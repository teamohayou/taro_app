package com.taro.tarocard.feedback;

import com.taro.tarocard.user.SiteUser;
import com.taro.tarocard.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final UserService userService;

    @GetMapping("")
    public String listFeedbacks(Model model) {
        List<Feedback> feedbacks = feedbackService.getlist();
        model.addAttribute("feedbacks", feedbacks);
        return "feedback_page";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String showNewFeedbackForm(Model model) {
        model.addAttribute("feedbackForm", new FeedbackForm());
        return "feedback_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createFeedback(@ModelAttribute FeedbackForm form, Principal principal) {
        if (principal == null) {
            throw new RuntimeException("인증된 사용자가 아닙니다.");
        }
        SiteUser user = userService.getUser(principal.getName());
        feedbackService.saveFeedback(form, user);

        return "redirect:/feedback";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String modifyFeedback(@PathVariable("id") Long id, Model model, Principal principal) {
        Feedback feedback = feedbackService.findById(id);
        if (feedback == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "피드백을 찾을 수 없습니다.");
        }
        checkFeedbackOwner(feedback, principal);

        FeedbackForm feedbackForm = new FeedbackForm(); // 피드백 폼 생성
        feedbackForm.setTitle(feedback.getTitle());
        feedbackForm.setContent(feedback.getContent());

        model.addAttribute("feedbackForm", feedbackForm); // 기존 데이터로 폼 채우기
        model.addAttribute("feedbackId", id); // 수정할 피드백 ID 추가

        return "feedback_form"; // 수정 폼으로 이동
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{feedbackId}")
    public String modifyFeedback(@PathVariable("feedbackId") Long feedbackId,
                                 @Valid FeedbackForm feedbackForm,
                                 BindingResult bindingResult,
                                 Principal principal) {
        if (bindingResult.hasErrors()) {
            return "feedback_form"; // 오류가 있는 경우 오류 메시지를 보여줌
        }

        Feedback feedback = feedbackService.findById(feedbackId);
        if (feedback == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "피드백을 찾을 수 없습니다.");
        }
        checkFeedbackOwner(feedback, principal);

        // 피드백 업데이트 로직을 호출합니다.
        feedbackService.updateFeedback(feedbackId, feedbackForm);

        return "redirect:/feedback";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable("id") Long id, Principal principal) {
        Feedback feedback = feedbackService.findById(id);
        if (feedback == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "피드백을 찾을 수 없습니다.");
        }
        checkFeedbackOwner(feedback, principal);
        feedbackService.deleteFeedback(id);
        return "redirect:/feedback"; // 삭제 후 리다이렉트
    }

    private void checkFeedbackOwner(Feedback feedback, Principal principal) {
        if (!feedback.getUser().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정/삭제 권한이 없습니다.");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/like/{id}")
    public String likeFeedback(@PathVariable("id") Long id, Principal principal) {
        Feedback feedback = feedbackService.findById(id);
        feedbackService.addLike(feedback);
        return "redirect:/feedback";
    }
}
