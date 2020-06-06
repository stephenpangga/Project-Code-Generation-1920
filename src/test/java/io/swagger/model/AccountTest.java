package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
Account acc = null;
@BeforeEach
public void start(){
    acc = new Account();
}
    @Test
    public void creatNewAccShouldNotBeNull(){
        assertNotNull(acc);
    }
    @Test
    public void currencyShldBeEuro(){
        assertEquals("Euro", acc.getCurrency());
    }

}