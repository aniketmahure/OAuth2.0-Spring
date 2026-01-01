package com.practice.authorization.controller;

import com.practice.authorization.service.UserService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model, OAuth2AuthenticationToken auth) {
        OidcUser user = (OidcUser) auth.getPrincipal();

        model.addAttribute("name", user.getFullName());
        model.addAttribute("claims", user.getClaims());
        model.addAttribute("idToken", user.getIdToken().getTokenValue());

        return "profile";
    }

}

