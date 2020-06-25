package io.swagger.service;

import io.swagger.model.Login;
import io.swagger.model.User;
import io.swagger.repository.LoginRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UserRepository userRepository;

    public LoginService() { }

    public String login(String email, String password) {
        String token = "";
        User user = userRepository.findByEmail(email);
        if (user.getPassword().equals(password)) {
            token = loginRepository.findByEmail(email).getToken();
        }

        return "{ \"token\": \"" + token + "\" }";
    }

    public User.AccessLevelEnum getAccessLevel(String token) {
        Login login = loginRepository.findByToken(token);
        return login != null ? login.getUser().getAccessLevel() : User.AccessLevelEnum.UNAUTHORIZED;
    }
}
