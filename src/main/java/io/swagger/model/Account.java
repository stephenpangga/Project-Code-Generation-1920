package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
@Entity
public class Account   {
  @JsonProperty("authorId")
  private Integer authorId = null;

  @JsonProperty("iban")
  @Id
  private String iban = null;

  @JsonProperty("balance")
  private Double balance = null;

  @JsonProperty("accountType")
  private AccountTypeEnum accountType = null;


  public Account() {
  }

  public Account(Integer authorId, String iban, Double balance, AccountTypeEnum accountType) {
    this.authorId = authorId;
    this.iban = iban;
    this.balance = balance;
    this.accountType = accountType;
  }

  public Account iban(String iban) {
    this.iban = iban;
    return this;
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


  public Account authorId(Integer authorId) {
    this.authorId = authorId;
    return this;
  }

  /**
   * Get authorId
   * @return authorId
  **/
  @ApiModelProperty(example = "1", value = "")
  
    public Integer getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Integer authorId) {
    this.authorId = authorId;
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

  public Account balance(Double balance) {
    this.balance = balance;
    return this;
  }


  /**
   * Get balance
   * @return balance
   **/
  @ApiModelProperty(example = "0", value = "")

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return Objects.equals(authorId, account.authorId) &&
            Objects.equals(iban, account.iban) &&
            Objects.equals(balance, account.balance) &&
            accountType == account.accountType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorId, iban, balance, accountType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    sb.append("    authorId: ").append(toIndentedString(authorId)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("}");
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
