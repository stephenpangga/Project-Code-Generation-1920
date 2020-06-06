package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
Account acc = null;
@BeforeEach
public void Start(){
    acc = new Account();
}
    @Test
    public void WhenICreatNewAccShouldNotBeNull(){
        assertNotNull(acc);
    }
    @Test
    public void CurrencyShldBeEuro(){
        assertEquals("Euro", acc.getCurrency());
    }

}