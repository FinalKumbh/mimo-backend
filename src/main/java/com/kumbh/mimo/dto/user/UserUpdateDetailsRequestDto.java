package com.kumbh.mimo.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateDetailsRequestDto {
    private String gender;
    private String birthdate;

    @Builder
    public UserUpdateDetailsRequestDto(String gender, String birthdate){
        this.gender = gender;
        this.birthdate = birthdate;
    }
}
