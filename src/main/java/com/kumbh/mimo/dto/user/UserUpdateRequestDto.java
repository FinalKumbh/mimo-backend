package com.kumbh.mimo.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String skinType;
    private String skinTone;

    @Builder
    public UserUpdateRequestDto(String skinType, String skinTone){
        this.skinType = skinType;
        this.skinTone = skinTone;
    }
}
