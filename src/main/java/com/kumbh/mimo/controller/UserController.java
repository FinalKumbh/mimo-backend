package com.kumbh.mimo.controller;

import com.kumbh.mimo.domain.user.User;
import com.kumbh.mimo.domain.user.UserRepository;
import com.kumbh.mimo.dto.user.UserResponseDto;
import com.kumbh.mimo.dto.user.UserUpdateDetailsRequestDto;
import com.kumbh.mimo.dto.user.UserUpdateSkinRequestDto;
import com.kumbh.mimo.exception.ResourceNotFoundException;
import com.kumbh.mimo.security.CurrentUser;
import com.kumbh.mimo.security.UserPrincipal;
import com.kumbh.mimo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @PutMapping("/update/skin/{email}")
    public String updateSkin(@PathVariable String email, @RequestBody UserUpdateSkinRequestDto requestDto){
        System.out.println("in user update skin controller");
        return userService.updateSkin(email, requestDto);
    }

    @PutMapping("/update/details/{email}")
    public String updateDetails(@PathVariable String email, @RequestBody UserUpdateDetailsRequestDto requestDto) {
        System.out.println("in user update details controller");
        return userService.updateDetails(email, requestDto);
    }

    @GetMapping("/api/v1/user/{id}")
    public UserResponseDto findById(@PathVariable Long id){
        return userService.findById(id);
    }
}
