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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PatchMapping("/skin/{email}")
    public ResponseEntity<?> updateSkin(@PathVariable String email, @RequestBody UserUpdateSkinRequestDto requestDto){
        System.out.println("in user update skin controller");
        System.out.println(requestDto.getSkinType() + requestDto.getSkinTone());
        String result = "";
        try{
            userService.updateSkin(email, requestDto);
            result = "피부 톤타입 수정 성공 !";
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch(Exception e){
            result = "피부 톤타입 수정 실패";
            return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);
        }
    }

    @PatchMapping("/details/{email}")
    public ResponseEntity<?> updateDetails(@PathVariable String email, @RequestBody UserUpdateDetailsRequestDto requestDto) {
        System.out.println("in user update details controller");
        String result = "";
        try{
            userService.updateDetails(email, requestDto);
            result = "피부 정보 수정 성공 !";
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch(Exception e){
            result = "피부 정보 수정 실패";
            return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id){
        return userService.findById(id);
    }
}
