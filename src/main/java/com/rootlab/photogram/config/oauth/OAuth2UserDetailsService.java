package com.rootlab.photogram.config.oauth;

import com.rootlab.photogram.config.auth.PrincipalDetails;
import com.rootlab.photogram.domain.User;
import com.rootlab.photogram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> userInfo = oAuth2User.getAttributes();

        String username = "facebook_" + userInfo.get("id");
        String password = passwordEncoder.encode(UUID.randomUUID().toString());
        String name = (String) userInfo.get("name");
        String email = (String) userInfo.get("email");

        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .email(email)
                    .role("ROLE_USER")
                    .build();

            return new PrincipalDetails(userRepository.save(user), userInfo);
        }
        return new PrincipalDetails(userEntity, userInfo);
    }
}
