package com.kumbh.mimo.web;

import com.kumbh.mimo.domain.user.UserRepository;
import com.kumbh.mimo.dto.user.UserUpdateDetailsRequestDto;
import com.kumbh.mimo.dto.user.UserUpdateSkinRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext context;

    @AfterEach
    public void tearDown() throws Exception {
        //this.userRepository.deleteAll();
    }

    @Test
    public void updateSkin() throws Exception{

        String email = "chaehoon.gwak@gmail.com";
        String skinType = "복합성";
        String skinTone = "쿨톤";

        UserUpdateSkinRequestDto requestDto = UserUpdateSkinRequestDto.builder()
                .skinType(skinType)
                .skinTone(skinTone)
                .build();

        String url = "http://localhost:" + port + "/user/update/skin/" + email;

//        User user = userRepository.findByEmail(email).orElseThrow();
//        System.out.println(user);
//        System.out.println(url);
        HttpEntity<?> requestUpdate = new HttpEntity<>(requestDto);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

        System.out.println(responseEntity);
    }

    @Test
    public void updateDetails() throws Exception{

        String email = "chaehoon.gwak@gmail.com";
        String gender = "남성";
        String birthdate = "1994-03-14";

        UserUpdateDetailsRequestDto requestDto = UserUpdateDetailsRequestDto.builder()
                .gender(gender)
                .birthdate(birthdate)
                .build();

        String url = "http://localhost:" + port + "/user/update/details/" + email;

//        User user = userRepository.findByEmail(email).orElseThrow();
//        System.out.println(user);
//        System.out.println(url);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

        System.out.println(responseEntity);
    }
}
