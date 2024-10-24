package com.taro.tarocard.history;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping("/saveHistory")
    public String saveHistory (@RequestParam("resultDescription") String resultDescription, @AuthenticationPrincipal UserDetails userDetails) {
        String nickname = userDetails.getUsername();
        historyService.saveHistory(nickname, resultDescription);
        return "redirect:/profile";
    }
}
