package com.taro.tarocard.user;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileService {
    private  final UserRepository userRepository;

    @Transactional(readOnly=true)
    public SiteUser getUserProfile(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new RuntimeException("사용자를 찾을 수 없습니다."));

    }

    @Transactional
    public void updateUserProfile(SiteUser siteUser){
        userRepository.save(siteUser);
    }
}
