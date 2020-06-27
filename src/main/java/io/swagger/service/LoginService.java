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

    public User getUserByToken(String token) {
        Login login = loginRepository.findByToken(token);
        return login != null ? login.getUser() : null;
    }

    public User.AccessLevelEnum getAccessLevel(String token) {
        User user = getUserByToken(token);
        return user != null ? user.getAccessLevel() : User.AccessLevelEnum.UNAUTHORIZED;
    }

    public Boolean isUserAuthorized(String token, User.AccessLevelEnum accessLevel) {
        return getAccessLevel(token).compareTo(accessLevel) >= 0;
    }
}
