package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TransactionTest {

    Transaction transaction;
    @BeforeEach
    public void setup()
    {
        User employee = new User("inholland@gmail.com",
                "inhollandbank",
                "Bank",
                "bank",
                User.AccessLevelEnum.EMPLOYEE);
        User stephen = new User("stephen@gmail.com",
                "password",
                "Stephen",
                "Pangga",
                User.AccessLevelEnum.EMPLOYEE);
        Account bankAccount = new Account("NL01INHO0000000001",100.0, employee, Account.AccountTypeEnum.CURRENT);
        Account stephenAccount = new Account("NL01INHO0000000019",100.0, stephen, Account.AccountTypeEnum.CURRENT);

        transaction = new Transaction(bankAccount,
                            stephenAccount,
                            100.0,
                            Transaction.TransactionTypeEnum.TRANSFER,
                            employee,
                            LocalDateTime.now());
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
    //amount
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

