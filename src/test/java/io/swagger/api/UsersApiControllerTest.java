package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class UsersApiControllerTest {

    @Autowired
    private MockMvc mvc;

    private User user;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp(){
        user = new User("jjk@gmail.com","jjk97","jeon","jungkook", User.AccessLevelEnum.CUSTOMER);
    }

    @Test
    public void callingAllUserShouldReturnOK() throws Exception {
        List<User> userList = Arrays.asList(user);
        given(userService.getAllUser()).willReturn(userList);

        this.mvc.perform(get("/users")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void createUserShouldReturn201Created() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();

        this.mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(user)))
                .andExpect(status().isCreated());
    }


}