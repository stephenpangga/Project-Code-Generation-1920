package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account acc = null;
    @BeforeEach
    public void Setup(){
        acc = new Account(null,0.0,null,null);
        acc.setIban("NL01INHO0000000001");
    }
    @Test
    public void WhenICreatNewAccShouldNotBeNull(){
        assertNotNull(acc);
    }
    @Test
    public void CurrencyIsEuro(){
        assertSame(acc.getCurrency(),"Euro");
    }
    @Test
    public void IBANLegnthIs18(){
        assertEquals(acc.getIban().length(),18);
    }


}