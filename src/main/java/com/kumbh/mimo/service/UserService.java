package com.kumbh.mimo.service;

import com.kumbh.mimo.domain.user.User;
import com.kumbh.mimo.domain.user.UserRepository;
import com.kumbh.mimo.dto.user.UserResponseDto;
import com.kumbh.mimo.dto.user.UserUpdateDetailsRequestDto;
import com.kumbh.mimo.dto.user.UserUpdateSkinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public String updateSkin(String email, UserUpdateSkinRequestDto requestDto){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new
                IllegalArgumentException("해당 유저가 없습니다. email="+ email));
        user.updateSkin(requestDto.getSkinType(), requestDto.getSkinTone());

        return email;
    }

    @Transactional
    public String updateDetails(String email, UserUpdateDetailsRequestDto requestDto){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new
                IllegalArgumentException("해당 유저가 없습니다. email="+ email));
        user.updateDetails(requestDto.getGender(), requestDto.getBirthdate());

        return email;
    }

    @Transactional
    public UserResponseDto findById(Long id){
        User entity = userRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 유저가 없습니다. id="+id));
        return new UserResponseDto(entity);
    }


}
