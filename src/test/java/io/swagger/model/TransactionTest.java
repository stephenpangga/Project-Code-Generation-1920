package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void setup()
    {
        transaction = new Transaction();
    }

    @Test
    public void getTransactions()
    {
        assertNotNull(transaction);
    }

}