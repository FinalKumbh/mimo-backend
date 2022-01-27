package com.kumbh.mimo.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),      //앞에 ROLE_ 필수
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}