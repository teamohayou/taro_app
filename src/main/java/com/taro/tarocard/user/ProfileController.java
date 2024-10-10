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

    private  final ProfileService profileService;

    @GetMapping("/profile/{userId}")
    public String viewProfiles(@PathVariable Long userId, Model model){
        SiteUser siteUser = profileService.getUserProfile(userId);
        model.addAttribute("siteUser",siteUser);
        return "profile_form";
    }

    @PostMapping("/profile/update")
    public String updateProfile(SiteUser siteUser){
        profileService.updateUserProfile(siteUser);
        return "redirect:/profile/"+ siteUser.getId();
    }
}
