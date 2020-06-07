package io.swagger.model;

import io.swagger.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.threeten.bp.LocalDateTime;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;


class TransactionTest {

    Transaction transaction;

    @Autowired
    TransactionRepository transactionRepository;

    private User employee;

    @BeforeEach
    public void setup() {
        employee = new User("inholland@gmail.com",
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
        transaction.setTransactionId(10001);
    }

    //constructor
    @Test
    public void testEmptyConstructor(){
        transaction = new Transaction();
        assertNotNull(transaction);
    }

    //getAllTransactions
    @Test
    public void whenGettingTransactionsShouldBeNotBeNull()
    {
        assertNotNull(transaction);
    }

    //specific Transactions
    @Test
    public void gettingSpecificIdTransactionItShouldNotBeNull()
    {
        assertNotNull(transaction.getTransactionId());
    }

    //the transactions limits
    //absolute limit 10
    @Test
    public void absoluteLimitShouldBeFixed(){
        assertEquals(transaction.getAbsoluteLimit(),10);
    }
    //cumulative limit
    @Test
    public void cumulativeLimitShouldBeFixed(){
        assertEquals(transaction.getCumulativeTransaction(),5);
    }
    //transaction amount limit
    @Test
    public void transactionAmountLimitShouldBeFixed(){
        assertEquals(transaction.getTransactionAmoutLimit(),10000.0);
    }

    //variables to make transactions
    //sender and recipients
    @Test
    public void getSenderShouldNotBeNull()
    {
        assertNotNull(transaction.getSender());
    }

    @Test
    public void getRecipientShouldNotBeNull()
    {
        assertNotNull(transaction.getRecipient());
    }

    @Test
    public void setRecipientShouldNotBeNull() {
        transaction.setRecipient(null);
        assertNull(transaction.getRecipient());
    }

    @Test
    public void setSenderShouldBeNull() {
        transaction.setSender(null);
        assertNull(transaction.getSender());
    }

    //amount
    @Test
    public void AmountShouldBeDouble() {
        assertEquals(transaction.getAmount().getClass(), Double.class);
    }

    @Test
    public void getAmountShouldNotBeNull() {
        assertNotNull(transaction.getAmount());
    }


    @Test
    public void settingAmountToNegativeShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                ()->transaction.setAmount(-1.0));
    }

    @Test
    public void settingNegativeAmountForTransactionShouldThrowIllegalArgumentExceptionWithMessage() {
    }

    //transaction

    //transactionType
    @Test
    public void setTransactionShouldNotBeNull()
    {
        transaction.setTransactionType(null);
        assertNull(transaction.getTransactionType());
    }
    @Test
    public void getTransactionTypeEnumShouldBeCorrect(){
        assertEquals(Transaction.TransactionTypeEnum.TRANSFER,transaction.getTransactionType());
    }

    @Test
    public void enumTest(){
      assertEquals(Transaction.TransactionTypeEnum.fromValue("withdraw"), Transaction.TransactionTypeEnum.WITHDRAW);
    }

    @Test
    public void wrongEnumTest(){
        assertNull(Transaction.TransactionTypeEnum.fromValue("withdarw"));
    }


    //user Performing
    @Test
    public void userPerformingShouldNotBeNull(){
        assertNotNull(transaction.getUserPerforming());
    }

    @Test
    public void getUserPerformingShouldNotBeNull() {
        assertEquals(employee, transaction.getUserPerforming());
    }


    //date time
    @Test
    public void getterAndSetterForDateTimeShouldNotBeNull(){
        transaction.setDatetime(LocalDateTime.of(12,12,12,12,12,12,12));
        assertEquals(transaction.getDatetime(), LocalDateTime.of(12,12,12,12,12,12,12));
    }

    //transaction id
    @Test
    public void getTransactionIdShouldNotBeNull(){
        assertNotNull(transaction.getTransactionId());
        assertEquals(transaction.getTransactionId(),10001);
    }

    //new things
    @Test
    public void setAmountShouldNotBeNull() {
        transaction.setAmount(100.0);
        assertEquals(transaction.getAmount(),100.0);
    }

    @Test
    public void setUserPerformingShoutNotBeNull(){
        transaction.setUserPerforming(null);
        assertNull(transaction.getUserPerforming());
    }



/*
    @Test
    public void toStringTest(){
        employee = new User("inholland@gmail.com",
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
        transaction.setTransactionId(10001);
        String tString = "class Transaction {\n" +
                "    sender: class Account {\n" +
                "        authorId: class RegistrationUser {\n" +
                "            io.swagger.model.User@553a7110\n" +
                "            email: inholland@gmail.com\n" +
                "            password: inhollandbank\n" +
                "            accessLevel: employee\n" +
                "        }\n" +
                "        accountType: current\n" +
                "        iban: NL01INHO0000000001\n" +
                "    }\n" +
                "    recipient: class Account {\n" +
                "        authorId: class RegistrationUser {\n" +
                "            io.swagger.model.User@cae6c6f6\n" +
                "            email: stephen@gmail.com\n" +
                "            password: password\n" +
                "            accessLevel: employee\n" +
                "        }\n" +
                "        accountType: current\n" +
                "        iban: NL01INHO0000000019\n" +
                "    }\n" +
                "    amount: 100.0\n" +
                "    transactionType: transfer\n" +
                "    transactionId: 10001\n" +
                "    userPerforming: class RegistrationUser {\n" +
                "        io.swagger.model.User@553a7110\n" +
                "        email: inholland@gmail.com\n" +
                "        password: inhollandbank\n" +
                "        accessLevel: employee\n" +
                "    }\n";
        assertTrue(transaction.toString().contains(tString));
    }
*/


}

