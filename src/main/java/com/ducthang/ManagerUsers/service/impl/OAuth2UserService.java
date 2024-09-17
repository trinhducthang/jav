package com.ducthang.ManagerUsers.service.impl;

import com.ducthang.ManagerUsers.model.Users;
import com.ducthang.ManagerUsers.repository.UsersRepository;
import com.ducthang.ManagerUsers.service.AuthenticationService;
import com.ducthang.ManagerUsers.ultil.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuth2UserService {

    private final UsersRepository usersRepository;
    private final AuthenticationService authenticationService;

    public String getUserAttributes(OAuth2User oAuth2User) {
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        Users users = usersRepository.findUsersByUsername(email);
        if (users == null) {
            Users users1 = new Users();
            users1.setEmail(email);
            users1.setUsername(email);
            users1.setName(name);
            users1.setRole(Role.USER);
            users = users1;
            usersRepository.save(users1);
        }

        return authenticationService.generateToken(users);
    }


}
