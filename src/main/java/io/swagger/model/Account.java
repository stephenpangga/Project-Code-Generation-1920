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

  @javax.persistence.Transient
  private String currency = "Euro";

  @JsonProperty("authorId")
  private Integer authorId = null;

  @JsonProperty("accountType")
  private AccountTypeEnum accountType = null;

  public Account(Integer authorId, AccountTypeEnum accountType) {
    this.authorId = authorId;
    this.accountType = accountType;

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
    return Objects.equals(authorId, account.authorId) &&
            Objects.equals(iban, account.iban) &&
            accountType == account.accountType;
  }

  @Override
  public int hashCode() {
    return Objects.hash(authorId, iban, accountType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    sb.append("    authorId: ").append(toIndentedString(authorId)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
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
