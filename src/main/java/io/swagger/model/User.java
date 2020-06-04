package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

/**
 * RegistrationUser
 */
@Validated
@Entity
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
public class User  {

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Id
  @SequenceGenerator(name = "user_seq", initialValue = 1, allocationSize = 10001)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("email")
  private String email;

  @JsonProperty("password")
  private String password;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  public User() {

  }

  public User(String email, String password, String firstName, String lastName, AccessLevelEnum accessLevel) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.accessLevel = accessLevel;
  }

  /**
   * Gets or Sets accessLevel
   */
  public enum AccessLevelEnum {
    EMPLOYEE("employee"),

    CUSTOMER("customer");

    private String value;

    AccessLevelEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccessLevelEnum fromValue(String text) {
      for (AccessLevelEnum b : AccessLevelEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("accessLevel")
  private AccessLevelEnum accessLevel = null;

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(example = "stephen@gmail.com", value = "")

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(example = "stephen", value = "")

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User accessLevel(AccessLevelEnum accessLevel) {
    this.accessLevel = accessLevel;
    return this;
  }

  /**
   * Get accessLevel
   * @return accessLevel
  **/
  @ApiModelProperty(example = "employee", value = "")
  
    public AccessLevelEnum getAccessLevel() {
    return accessLevel;
  }

  public void setAccessLevel(AccessLevelEnum accessLevel) {
    this.accessLevel = accessLevel;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User registrationUser = (User) o;
    return Objects.equals(this.email, registrationUser.email) &&
        Objects.equals(this.password, registrationUser.password) &&
        Objects.equals(this.accessLevel, registrationUser.accessLevel) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, accessLevel, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistrationUser {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    accessLevel: ").append(toIndentedString(accessLevel)).append("\n");
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
