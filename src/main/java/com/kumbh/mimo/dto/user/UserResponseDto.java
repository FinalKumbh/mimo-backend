package com.kumbh.mimo.dto.user;

import com.kumbh.mimo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private String skinType;
    private String skinTone;
    private String gender;
    private String birthdate;

    public UserResponseDto(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.imageUrl = entity.getImageUrl();
        this.skinType = entity.getSkinType();
        this.skinTone = entity.getSkinTone();
        this.gender = entity.getGender();
        this.birthdate = entity.getBirthdate();
    }

}
