package com.kumbh.mimo.service;

import com.kumbh.mimo.domain.user.User;
import com.kumbh.mimo.domain.user.UserRepository;
import com.kumbh.mimo.dto.UserResponseDto;
import com.kumbh.mimo.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public String updateDetails(String email, UserUpdateRequestDto requestDto){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new
                IllegalArgumentException("해당 유저가 없습니다. email="+ email));
        user.update(requestDto.getSkinType(), requestDto.getSkinTone());

        return email;
    }

    @Transactional
    public UserResponseDto findById(Long id){
        User entity = userRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 유저가 없습니다. id="+id));
        return new UserResponseDto(entity);
    }


}
