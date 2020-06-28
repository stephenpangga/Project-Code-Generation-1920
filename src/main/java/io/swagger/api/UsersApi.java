/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Account;
import io.swagger.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "Delete registered user by id", nickname = "deleteUser", notes = "User is deleted", response = User.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Indicates that user is deleted", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Invalid user id"),
        @ApiResponse(code = 404, message = "User with that id is not found") })
    @RequestMapping(value = "/users/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<User> deleteUser(@ApiParam(value = "User id to get from the database",required=true) @PathVariable("userId") Integer userId
);


    @ApiOperation(value = "Get registered user by id", nickname = "getUser", notes = "", response = User.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User is found", response = User.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "No user with that id") })
    @RequestMapping(value = "/users/{userId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<User> getUser(@ApiParam(value = "User id to get from the database",required=true) @PathVariable("userId") Integer userId
);


    @ApiOperation(value = "Get all registered users", nickname = "getUsers", notes = "", response = User.class, responseContainer = "List", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "No registered users") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsers(@ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
, @ApiParam(value = "The numbers of items to return") @Valid @RequestParam(value = "limit", required = false) Integer limit
, @ApiParam(value = "First name of the user") @Valid @RequestParam(value = "firstName", required = false) String firstName
, @ApiParam(value = "Last name of the user") @Valid @RequestParam(value = "lastName", required = false) String lastName
, @ApiParam(value = "Access level of this user") @Valid @RequestParam(value = "accessLevel", required = false) BigDecimal accessLevel
);


    @ApiOperation(value = "Registers a new user to the database", nickname = "registerUser", notes = "", response = User.class, authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Registration completed", response = User.class),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    public ResponseEntity<User> registerUser(@ApiParam(value = "creates a new user" ,required=true )  @Valid @RequestBody User body);


    @ApiOperation(value = "Update User information of the registered user by id", nickname = "updateUser", notes = "User information is updated", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Changes Completed"),
        @ApiResponse(code = 400, message = "Invalid userid"),
        @ApiResponse(code = 404, message = "User with that id is not found") })
    @RequestMapping(value = "/users/{userId}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "User id to get from the database",required=true) @PathVariable("userId") Integer userId
,@ApiParam(value = ""  )  @Valid @RequestBody User body
);

    @ApiOperation(value = "Find account by customer ", nickname = "usersUserIdAccountsGet", notes = "Returns customers accounts", response = Account.class, responseContainer = "List", authorizations = {
            @Authorization(value = "ApiKeyAuth")    }, tags={ "Users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Account.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid IBAN supplied"),
            @ApiResponse(code = 404, message = "Account not found") })
    @RequestMapping(value = "/users/{userId}/accounts",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Account>> usersUserIdAccountsGet(@ApiParam(value = "customer name the account is in",required=true) @PathVariable("userId") String userId
    );
}
