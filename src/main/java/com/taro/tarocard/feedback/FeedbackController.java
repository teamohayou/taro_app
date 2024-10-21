package com.taro.tarocard.feedback;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/list")
    public String list () {
        return "main_page.";
    }
}
