package com.taro.tarocard.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Service
public class UserService {
    private  final UserRepository  userRepository;
    private  final PasswordEncoder passwordEncoder;

    public SiteUser create(String username,String password,String nickname){
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(nickname);
        this.userRepository.save(user);
        return user;
    }
    @Transactional(readOnly=true)
    public SiteUser getUserProfile(Long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("사용자를 찾을 수 없습니다."));

    }


    public SiteUser getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String username = userDetails.getUsername();
            return userRepository.findByusername(username)
                    .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        }
        System.out.println("No user is authenticated");
        return null; // 로그인하지 않은 경우
    }

    public void updateUserProfile(SiteUser user) {
        userRepository.save(user); // user 객체를 데이터베이스에 저장하여 업데이트
    }
    @Transactional(readOnly = true)
    public SiteUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }



}
