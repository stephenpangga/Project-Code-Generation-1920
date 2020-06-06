package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public void deteleteUserById(int id) {
        userRepository.delete(id);
    }

    public void updateUser(int userId, User newUserValues) { // you need an account object with new values

        User userToBeEdited = userRepository.findOne(userId);
        if (userToBeEdited.getEmail() != null) { //Check if any one of the properties is not empty just to make sure the object was retrieved;
            userToBeEdited.setEmail(newUserValues.getEmail()); // replace all values with new values
            userToBeEdited.setPassword(newUserValues.getPassword());
            userRepository.save(userToBeEdited);
        }

    }

    public User registerUser(String email, String password, String firstName, String lastName, User.AccessLevelEnum accessLevel){
        User user = new User(email,password,firstName,lastName,User.AccessLevelEnum.CUSTOMER);
        userRepository.save(user);
        return user;
    }

}
