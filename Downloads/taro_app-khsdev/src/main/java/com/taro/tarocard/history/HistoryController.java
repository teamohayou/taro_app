package com.taro.tarocard.history;

import com.taro.tarocard.card.CardService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HistoryController {
    private final UserService userService;
    private final CardService cardService;
    private final HistoryService historyService;

    @PostMapping("/saveHistory")
    public String saveHistory(@RequestParam("cardId") RomanticCard cardId,
                              @RequestParam("categoryname") String categoryname,
                              Principal principal,
                              RedirectAttributes redirectAttributes) {
        try {
            // 로그인 여부 확인
            if (principal == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
                return "redirect:/user/login"; // 로그인 페이지로 리다이렉트
            }

            // 로그인된 사용자 정보 가져오기
            String username = principal.getName();
            SiteUser user = userService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. username: " + username));

            // History 객체 생성 및 필드 설정
            History history = new History();
            history.setUser(user);
            history.setCard(cardId); // 카드 ID만 설정
            history.setCategoryname(categoryname); // 카테고리 이름 설정
            history.setSavedAt(LocalDateTime.now()); // 저장 시간 설정

            // 히스토리 저장 서비스 호출
            historyService.save(history);

            // 저장 성공 메시지 설정
            redirectAttributes.addFlashAttribute("message", "히스토리가 성공적으로 저장되었습니다.");
            return "redirect:/history"; // 저장 후 히스토리 페이지로 리다이렉트

        } catch (Exception e) {
            e.printStackTrace(); // 예외의 전체 스택 트레이스 로그 출력
            redirectAttributes.addFlashAttribute("errorMessage", "히스토리 저장 중 오류가 발생했습니다: " + e.getMessage());
            return "redirect:/cardresult_page"; // 오류 발생 시 결과 페이지로 리다이렉트
        }
    }

    @GetMapping("/history")
    public String viewHistory(Model model, Principal principal) {
        try {
            // 로그인된 사용자 정보 가져오기
            String username = principal.getName();
            SiteUser user = userService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            // 사용자의 히스토리 조회
            List<History> histories = historyService.findByUserId(user.getId());

            // 히스토리 데이터를 모델에 추가
            model.addAttribute("histories", histories);
            return "history_page"; // 히스토리 페이지로 이동
        } catch (Exception e) {
            e.printStackTrace(); // 예외의 전체 스택 트레이스 로그 출력
            model.addAttribute("errorMessage", "히스토리 조회 중 오류가 발생했습니다: " + e.getMessage());
            return "error_page"; // 오류 발생 시 오류 페이지로 이동
        }
    }
}
