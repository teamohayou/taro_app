package com.taro.tarocard.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    // 현재 로그인한 사용자의 프로필 보기
    @GetMapping("/profile")
    public String viewProfile(Model model) {
        SiteUser user = userService.getCurrentUser(); // 현재 로그인한 사용자 정보 가져오기
        if (user == null) {
            return "redirect:/user/login"; // 로그인 페이지로 리다이렉트
        }
        model.addAttribute("user", user); // 사용자 정보를 모델에 추가
        return "profile_form"; // profile_form.html 반환
    }

    // 특정 사용자의 프로필 보기
    @GetMapping("/profile/{id}")
    public String viewProfiles(@PathVariable Long id, Model model) {
        try {
            SiteUser user = userService.getUserProfile(id);
            model.addAttribute("user", user);
            return "profile_form"; // 템플릿 이름
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error_page"; // 오류 페이지로 리다이렉트
        }
    }

    // 프로필 업데이트 처리
    @GetMapping("/profile/update")
    public String showUpdateProfileForm(Model model) {
        SiteUser currentUser = userService.getCurrentUser();
        if(currentUser==null){
            return "redirect:/user/login";
        }
        model.addAttribute("user",currentUser);
        return "profile_update_form";
    }

    @PostMapping("/profile/update")
    public String updateProfile(SiteUser updateUser,String newPassword,Model model){
        SiteUser currentUser = userService.getCurrentUser();
        if(currentUser == null){
            return "redirect:/user/login";
        }
        if(newPassword != null && )
    }



}