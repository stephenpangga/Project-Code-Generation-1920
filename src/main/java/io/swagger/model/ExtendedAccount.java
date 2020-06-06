package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Account;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ExtendedAccount
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-15T11:53:10.455Z[GMT]")
public class ExtendedAccount extends Account  {
  @JsonProperty("iban")
  private String iban = null;

  @JsonProperty("balance")
  private Double balance = null;

  public ExtendedAccount iban(String iban) {
    this.iban = iban;
    return this;
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

  public ExtendedAccount balance(Double balance) {
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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExtendedAccount extendedAccount = (ExtendedAccount) o;
    return Objects.equals(this.iban, extendedAccount.iban) &&
        Objects.equals(this.balance, extendedAccount.balance) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban, balance, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExtendedAccount {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
