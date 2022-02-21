package com.kumbh.mimo.web;

import com.kumbh.mimo.domain.user.User;
import com.kumbh.mimo.domain.user.UserRepository;
import com.kumbh.mimo.dto.UserUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
    public void update() throws Exception{

        String email = "chaehoon.gwak@gmail.com";
        String skinType = "복합성";
        String skinTone = "쿨톤";

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .skinType(skinType)
                .skinTone(skinTone)
                .build();

        String url = "http://localhost:" + port + "/user/update/" + email;

//        User user = userRepository.findByEmail(email).orElseThrow();
//        System.out.println(user);

        System.out.println(url);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

        System.out.println(responseEntity);
    }
}
