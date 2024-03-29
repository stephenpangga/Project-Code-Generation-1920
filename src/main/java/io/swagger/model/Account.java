package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.service.IBANGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Account
 */
@Validated
@NoArgsConstructor
@Getter
@Setter
@Entity
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
public class Account   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "acc_sq")
    @GenericGenerator(
            name = "acc_sq",
            strategy = "io.swagger.service.IBANGenerator",
            parameters = {
                    @Parameter(name = IBANGenerator.CODE_NUMBER_SEPARATOR_PARAMETER, value = "INHO"),
                    @Parameter(name = IBANGenerator.NUMBER_FORMAT_PARAMETER, value = "%010d")})
    @JsonProperty("iban")
    private String iban;


    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonIgnore
    @JsonProperty("balance")
    private Double balance = 0.0;


    public String getCurrency() {
        return currency;
    }

    @javax.persistence.Transient
    private String currency = "Euro";

    @JsonIgnore
    @OneToMany(mappedBy = "sender",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Transaction> transactions_sent = new ArrayList<Transaction>();

    @JsonIgnore
    @OneToMany(mappedBy = "recipient",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Transaction> transactions_recieve = new ArrayList<Transaction>();

    @JsonProperty("owner")
    @ManyToOne(cascade = {CascadeType.MERGE})
    ////@JoinColumn(name = "fk_user")
    private User owner = null;

    @JsonProperty("accountType")
    private AccountTypeEnum accountType = null;

    public Account(String iban, Double balance, User authorId, AccountTypeEnum accountType) {
        this.iban = iban;
        this.balance = balance;
        this.owner = authorId;
        this.accountType = accountType;
    }

    public Account(Double balance, User authorId, AccountTypeEnum accountType) {
        this.owner = authorId;
        this.balance = balance;
        this.accountType = accountType;

    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    //Limitation of transaction from transaction model.
    //the max amount of transaction that can be done per day
    private int  cumulativeTransaction = 5;

    //the maximum amount per transaction.
    private double transactionAmoutLimit = 10000.0;

    //the amount of the balance cannot exceeds.
    private double absoluteLimit = 10.0;

    public int getCumulativeTransaction() {
        return cumulativeTransaction;
    }

    public void setCumulativeTransaction(int cumulativeTransaction) {
        this.cumulativeTransaction = cumulativeTransaction;
    }

    public double getTransactionAmoutLimit() {
        return transactionAmoutLimit;
    }

    public void setTransactionAmoutLimit(double transactionAmoutLimit) {
        this.transactionAmoutLimit = transactionAmoutLimit;
    }

    public double getAbsoluteLimit() {
        return absoluteLimit;
    }

    public void setAbsoluteLimit(double absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }

    public Account(Double balance, User owner, AccountTypeEnum accountType, int cumulativeTransaction, double transactionAmoutLimit, double absoluteLimit) {
        this.balance = balance;
        this.owner = owner;
        this.accountType = accountType;
        this.cumulativeTransaction = cumulativeTransaction;
        this.transactionAmoutLimit = transactionAmoutLimit;
        this.absoluteLimit = absoluteLimit;
    }

    /**
     * type of account to be created
     */
    public enum AccountTypeEnum {
        SAVINGS("savings"),

        CURRENT("current");

        private String value;

        AccountTypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static AccountTypeEnum fromValue(String text) {
            for (AccountTypeEnum b : AccountTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }


    public Account owner(User owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Get owner
     * @return owner
     **/
    @ApiModelProperty(example = "1", value = "")

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Account accountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
        return this;
    }

    /**
     * type of account to be created
     * @return accountType
     **/
    @ApiModelProperty(example = "current", value = "type of account to be created")

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }
    /**
     * unique string that identifies the bank and account
     * @return iban
     **/
    @ApiModelProperty(example = "NL23INHO2298608059", value = "unique string that identifies the bank and account")

    @Size(min=18,max=18)   public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }


    /**
     * Get balance
     * @return balance
     **/
    @ApiModelProperty(example = "0", value = "")




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(owner, account.owner) &&
                Objects.equals(iban, account.iban) &&
                Objects.equals(balance, account.balance) &&
                accountType == account.accountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, iban, balance, accountType);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Account{");
        sb.append("iban='").append(iban).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", owner=").append(owner);
        sb.append(", accountType=").append(accountType);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
