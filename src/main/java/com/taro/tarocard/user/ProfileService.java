package com.taro.tarocard.user;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileService {
    private  final UserRepository userRepository;



    @Transactional
    public void updateUserProfile(SiteUser user){

        userRepository.save(user);
    }

}
