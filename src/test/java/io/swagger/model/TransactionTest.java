package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TransactionTest {

    Transaction transaction;
    @BeforeEach
    public void setup()
    {
        //Account bank = new Account(2, 0.0,Account.AccountTypeEnum.CURRENT);
        //Account stephen = new Account(2, 0.0,Account.AccountTypeEnum.SAVINGS);
        transaction = new Transaction();
        transaction.setAmount(1.1);
    }

    //getAllTransactions
    @Test
    public void whenGettingTransactionsShouldBNotBeNull()
    {
        assertNotNull(transaction);
    }

    //Create Transactions
    @Test
    public void whenICreateTransactionItShoutNotBeNull()
    {
        assertNotNull(transaction);
    }

    //specific Transactions
    @Test
    public void getIbanShouldNotBeNull()
    {

    }

    //the transactions limits

    //variables to make transactions
    @Test
    public void getSenderShouldNotBeNull()
    {
        assertNotNull(transaction.getSender());
    }

    @Test
    public void getAmountShouldNotBeNull()
    {
        assertNotNull(transaction.getAmount());
    }
    @Test
    public void settingAmountToNegativeShouldThrowIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class,
        ()->transaction.setAmount(-1.0));
    }

    @Test
    public void settingNegativeAmountForTransactionShouldThrowIllegalArgumentExceptionWithMessage()
    {
    }



}