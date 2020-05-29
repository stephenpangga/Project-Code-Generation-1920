package io.swagger.service;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public List<User> getAllUser()
    {
        return (List<User>) userRepository.findAll();
    }

    public List<User> deteleteUserById(int id) {
        userRepository.delete(id);
        List<User> user = userRepository.findAll();
        return user;
    }

    public User registerUser(String email, String password, String firstName, String lastName, User.AccessLevelEnum accessLevel){
        User user = new User(email,password,firstName,lastName,User.AccessLevelEnum.CUSTOMER);
        userRepository.save(user);
        return user;
    }

}
