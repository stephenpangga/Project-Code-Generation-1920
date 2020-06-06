package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Body;
import io.swagger.model.Login;
import io.swagger.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
@Controller
public class LoginApiController implements LoginApi {

    @Autowired
    private LoginService loginService;

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Login> login(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Body body
) {
        String token = loginService.login(body.getEmail(), body.getPassword());
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            ResponseEntity<Login> loginResponseEntity = new ResponseEntity<Login>(HttpStatus.OK);
            return loginResponseEntity;
        }

        return new ResponseEntity<Login>(HttpStatus.BAD_REQUEST);
    }

}
