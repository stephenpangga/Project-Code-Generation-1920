package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Login;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.LoginRepository;
import io.swagger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoginRepository loginRepository;

    private final TokenGenerator tokenGenerator = new TokenGenerator();

    public UserService() {
    }

    public List<User> getAllUser()
    {
        return (List<User>) userRepository.findAll();
    }


    public User deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        // Fixed crash for deleting initial users.
        // Now checking if the user is indeed in the login repository.
        // If not, ignore.
        // TODO: make sure all initial users are added to login repository.
        String email = user.getEmail();
        Login login = loginRepository.findByEmail(email);
        if (login != null) {
            loginRepository.delete(login);
        }
        userRepository.delete(user);
        return user;
    }

    public User findUser(int userId){
        User user = userRepository.findById(userId).orElse(null);
        return user;
    }


    public void updateUser(int userId, User newUserValues) { // you need an account object with new values

        User userToBeEdited = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);
        if (userToBeEdited.getEmail() != null) { //Check if any one of the properties is not empty just to make sure the object was retrieved;
            userToBeEdited.setEmail(newUserValues.getEmail()); // replace all values with new values
            userToBeEdited.setPassword(newUserValues.getPassword());
            userToBeEdited.setFirstName(newUserValues.getFirstName());
            userToBeEdited.setLastName(newUserValues.getLastName());
            userToBeEdited.setAccessLevel(newUserValues.getAccessLevel());
            userRepository.save(userToBeEdited);
        }

    }

    public User registerUser(String email, String password, String firstName, String lastName, User.AccessLevelEnum accessLevel){
        User user = new User(email,password,firstName,lastName,accessLevel);
        Login login = new Login().token(tokenGenerator.generate(32)).user(user);
        userRepository.save(user);
        loginRepository.save(login);
        return user;
    }

    public List<Account> GetCustomerAccounts(User customer) {
        List<Account> accounts = new java.util.ArrayList<>(Collections.emptyList());

        for (Account account : accountRepository.findAll()) {
            if (account.getOwner() == customer) {
                accounts.add(account);
            }
        }

        return  accounts;
    }
}
