package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @BeforeEach
    public void setUp(){
        user =  new User("jjh@gmail.com",
                "jjh97",
                "Jaehyun",
                "Jung",
                User.AccessLevelEnum.EMPLOYEE);
    }

    @Test
    public void whenICreatNewUserItShouldNotBeNull(){
        assertNotNull(user);
    }

    /*@Test
    public void userIdLengthShouldbe5Digits(){
        assertEquals(user.get);
    }*/

}