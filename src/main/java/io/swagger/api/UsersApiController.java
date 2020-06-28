package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.service.LoginService;
import io.swagger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private LoginService loginService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<User> deleteUser(@ApiParam(value = "User id to get from the database",required=true) @PathVariable("userId") Integer userId) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.EMPLOYEE)) {
            User user = userService.findUser(userId);
            return user != null ? new ResponseEntity<User>(userService.deleteUser(userId), HttpStatus.OK) :
                                    new ResponseEntity(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<User> getUser(@ApiParam(value = "User id to get from the database",required=true) @PathVariable("userId") Integer userId
    ) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.CUSTOMER)) {

            return new ResponseEntity<User>(userService.findUser(userId),HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<List<User>> getUsers(@ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
, @ApiParam(value = "The numbers of items to return") @Valid @RequestParam(value = "limit", required = false) Integer limit
, @ApiParam(value = "First name of the user") @Valid @RequestParam(value = "firstName", required = false) String firstName
, @ApiParam(value = "Last name of the user") @Valid @RequestParam(value = "lastName", required = false) String lastName
, @ApiParam(value = "Access level of this user") @Valid @RequestParam(value = "accessLevel", required = false) BigDecimal accessLevel
) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.CUSTOMER)) {
            return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<User> registerUser(@ApiParam(value = "User object to register to the database") @Valid @RequestParam(value = "firstName", required = false) String firstName
, @ApiParam(value = "User object to register to the database") @Valid @RequestParam(value = "lastName", required = false) String lastName
) {
        //return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
        return new ResponseEntity<User>(userService.registerUser("test02@gmail.com","test02","test","02", User.AccessLevelEnum.CUSTOMER), HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "User id to get from the database",required=true) @PathVariable("userId") Integer userId
    ,@ApiParam(value = ""  )  @Valid @RequestBody User body
    ) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.CUSTOMER)) {
            String accept = request.getHeader("Accept");
            userService.updateUser(userId, body);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<List<Account>> usersUserIdAccountsGet(@ApiParam(value = "customer name the account is in",required=true) @PathVariable("userId") String userId
    ) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.CUSTOMER)) {

            User currentUser = new User();
            for(User user : userService.getAllUser()){
                if (user.getId() == Integer.parseInt(userId)){
                    currentUser = user;
                }
            }

            return new ResponseEntity<List<Account>>(userService.GetCustomerAccounts(currentUser),HttpStatus.NOT_IMPLEMENTED);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

}
