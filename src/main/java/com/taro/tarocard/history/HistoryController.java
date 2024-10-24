package com.taro.tarocard.history;

import com.taro.tarocard.card.CardService;
import com.taro.tarocard.card.Category;
import com.taro.tarocard.card.CategoryRepository;
import com.taro.tarocard.card.RomanticCard;
import com.taro.tarocard.user.SiteUser;
import com.taro.tarocard.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;
    private final UserService userService;
    private final CardService cardService;
    private CategoryRepository categoryRepository;

    @PostMapping("/saveHistory")
    public String saveHistory(@RequestParam("cardId") Integer cardId, @RequestParam("categoryname") String categoryname, Principal principal) {
        String username = principal.getName();
        SiteUser user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 카테고리 조회
        Category category = categoryRepository.findByCategoryname(categoryname)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        // 카드 조회
        RomanticCard card = cardService.getCardByRcCardId(cardId)
                .orElseThrow(() -> new IllegalArgumentException("Card not found"));

        // 히스토리 저장 (카드 이름과 설명 포함)
        History history = new History(user, card, card.getCardname(), card.getDescription(), category, LocalDateTime.now());
        historyService.save(history);

        return "redirect:/history";
    }

    @GetMapping("/history")
    public String viewHistory(Model model, Principal principal) {
        String username = principal.getName();
        SiteUser user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<History> histories = historyService.findByUserId(user.getId());
        model.addAttribute("histories", histories);
        return "history_page";
    }
}
