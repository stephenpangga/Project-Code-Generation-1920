package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
public class Transaction   {
  @JsonProperty("sender")
  private String sender = null;

  @JsonProperty("recipient")
  private String recipient = null;

  @JsonProperty("amount")
  private Double amount = null;

  public Transaction() {
  }

  public Transaction(String sender, String recipient, Double amount, TransactionTypeEnum transactionType, Integer transactionId, Integer userPerforming) {
    this.sender = sender;
    this.recipient = recipient;
    this.amount = amount;
    this.transactionType = transactionType;
    this.transactionId = transactionId;
    this.userPerforming = userPerforming;
  }

  /**
   * a different transaction can occur.
   */
  public enum TransactionTypeEnum {
    TRANSFER("transfer"),
    
    WITHDRAW("withdraw"),
    
    DEPOSIT("deposit");

    private String value;

    TransactionTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TransactionTypeEnum fromValue(String text) {
      for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("transactionType")
  private TransactionTypeEnum transactionType = null;

  @JsonProperty("transaction_id")
  private Integer transactionId = null;

  @JsonProperty("userPerforming")
  private Integer userPerforming = null;

  public Transaction sender(String sender) {
    this.sender = sender;
    return this;
  }

  /**
   * Get sender
   * @return sender
  **/
  @ApiModelProperty(example = "NL01INHO0000000001", value = "")
  
    public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public Transaction recipient(String recipient) {
    this.recipient = recipient;
    return this;
  }

  /**
   * Get recipient
   * @return recipient
  **/
  @ApiModelProperty(example = "NL53INHO0858545222", value = "")
  
    public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public Transaction amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(example = "500.73", value = "")
  
    public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Transaction transactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * a different transaction can occur.
   * @return transactionType
  **/
  @ApiModelProperty(example = "transaction", value = "a different transaction can occur.")
  
    public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
  }

  public Transaction transactionId(Integer transactionId) {
    this.transactionId = transactionId;
    return this;
  }

  /**
   * Get transactionId
   * @return transactionId
  **/
  @ApiModelProperty(example = "488558", value = "")
  
    public Integer getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Integer transactionId) {
    this.transactionId = transactionId;
  }

  public Transaction userPerforming(Integer userPerforming) {
    this.userPerforming = userPerforming;
    return this;
  }

  /**
   * Get userPerforming
   * @return userPerforming
  **/
  @ApiModelProperty(example = "1", value = "")
  
    public Integer getUserPerforming() {
    return userPerforming;
  }

  public void setUserPerforming(Integer userPerforming) {
    this.userPerforming = userPerforming;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.sender, transaction.sender) &&
        Objects.equals(this.recipient, transaction.recipient) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.transactionType, transaction.transactionType) &&
        Objects.equals(this.transactionId, transaction.transactionId) &&
        Objects.equals(this.userPerforming, transaction.userPerforming);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sender, recipient, amount, transactionType, transactionId, userPerforming);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    recipient: ").append(toIndentedString(recipient)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
    sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
    sb.append("    userPerforming: ").append(toIndentedString(userPerforming)).append("\n");
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
