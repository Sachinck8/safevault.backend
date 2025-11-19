package com.safevault.backend.config;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.safevault.backend.model.User;
import com.safevault.backend.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public OAuth2SuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws java.io.IOException {

        DefaultOAuth2User oauthUser = (DefaultOAuth2User) auth.getPrincipal();
        String email = oauthUser.getAttribute("email");

        
        User existingUser = userRepository.findByUsername(email).orElse(null);

        if (existingUser == null) {
            User newUser = new User();
            newUser.setUsername(email);
            newUser.setPassword("GOOGLE_USER"); 
            newUser.setRole("USER");
            userRepository.save(newUser);
        }

        response.sendRedirect("/welcome");
    }
}

