package io.swagger.api;

import io.swagger.model.User;
import io.swagger.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class UsersApiControllerTest {

    @Autowired
    private MockMvc mvc;

    private User user;

    @MockBean
    private UserService userService;

    public void setUp(){
        user = new User("jjk@gmail.com","jjk97","jeon","jungkook", User.AccessLevelEnum.CUSTOMER);
    }

    @Test
    public void callingAllUserShouldReturnOK() throws Exception {
        given(userService.getAllUser()).willReturn(Arrays.asList(user));

        this.mvc.perform(get("/users")).andExpect(status().isOk());
    }


}