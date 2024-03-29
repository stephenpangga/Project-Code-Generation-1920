package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.service.AccountService;
import io.swagger.service.LoginService;
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
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    @Autowired
    private AccountService accountService;

    @Autowired
    private LoginService loginService;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> accountsDelete(@ApiParam(value = "Account IBAN to delete",required=true) @PathVariable("IBAN") String IBAN
)   {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.EMPLOYEE)) {
            accountService.DeleteAccount(IBAN);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<List<Account>> accountsGet(@ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
,@ApiParam(value = "The numbers of items to return") @Valid @RequestParam(value = "limit", required = false) Integer limit
,@ApiParam(value = "The balance of the account") @Valid @RequestParam(value = "balance", required = false) Integer balance
) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.CUSTOMER)) {

            return new ResponseEntity<List<Account>>(accountService.GetAllAccounts(),HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<Account> accountsIBANGet(@ApiParam(value = "Account IBAN to find",required=true) @PathVariable("IBAN") String IBAN
) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.CUSTOMER)) {

            return new ResponseEntity<Account>(accountService.GetAccount(IBAN),HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<Account> accountsIBANPut(@ApiParam(value = "Account IBAN to find",required=true) @PathVariable("IBAN") String IBAN
,@ApiParam(value = ""  )  @Valid @RequestBody Account body
) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.CUSTOMER)) {
            return new ResponseEntity<Account>(accountService.UpdateAccount(body,IBAN),HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<Account> accountsPost(@ApiParam(value = "creates a new account for a existing user" ,required=true )  @Valid @RequestBody Account body
) {
        if (loginService.isUserAuthorized(request.getHeader("Authorization"), User.AccessLevelEnum.CUSTOMER)) {
            return new ResponseEntity<Account>(accountService.CreateAccount(body),HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }
}
