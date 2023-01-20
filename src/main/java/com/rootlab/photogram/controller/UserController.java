package com.rootlab.photogram.controller;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable Long id, Model model) {
        User user = userService.getUserProfile(id);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id,
                         @AuthenticationPrincipal PrincipalDetails userDetails) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        PrincipalUserDetails principal = (PrincipalUserDetails) auth.getPrincipal();
//        log.info("[UserController] authentication.getPrincipal: " + principal.getUser()); // PrincipalUserDetails
        return "user/update";
    }

}
