package com.kumbh.mimo.controller;

import com.kumbh.mimo.domain.user.User;
import com.kumbh.mimo.dto.UserResponseDto;
import com.kumbh.mimo.security.CurrentUser;
import com.kumbh.mimo.security.UserPrincipal;
import com.kumbh.mimo.service.PostsService;
import com.kumbh.mimo.service.UserService;
import com.kumbh.mimo.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model, @CurrentUser UserPrincipal user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getUsername());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }

//    @GetMapping("/user/details/update")
//    public String userDetailsUpdate(){
//
//        return "user-details";
//    }

//    @GetMapping("/user/details/update/{id}")
//    public String userDetailsUpdate(@PathVariable Long id, Model model){
//
//        UserResponseDto dto = userService.findById(id);
//        model.addAttribute("user", dto);
//        return "user-details";
//    }


}
