package io.swagger.service;

import io.swagger.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public LoginService() { }

    public String login(String email, String password) {
        // TODO
        return "";
    }
}
