package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    class AccountTest {
    Account acc = null;
    @BeforeEach
    public void Setup(){
        acc = new Account();
    }
    @Test
    public void WhenICreatNewAccShouldNotBeNull(){
        assertNotNull(acc);
    }
    @Test
    public void CurrencyIsEuro(){
        assertTrue(acc.getCurrency() == "Euro");
    }
    @Test
    public void IBANLegnthIs18(){
        assertTrue(acc.getIban().length() == 18);
    }

}