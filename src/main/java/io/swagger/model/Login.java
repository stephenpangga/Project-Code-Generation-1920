package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.Objects;

/**
 * InlineResponse200
 */
@Validated
@Entity
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-18T19:26:09.389Z[GMT]")
public class Login {
  @Id
  @JsonProperty("token")
  private String token = null;

  @JsonProperty("userId")
  @OneToOne(cascade = {CascadeType.REMOVE})
  @JoinColumn(name = "userId", referencedColumnName = "id")
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Login token(String token) {
    this.token = token;
    return this;
  }

  /**
   * An authorization token used to authorize for future request
   * @return token
  **/
  @ApiModelProperty(value = "An authorization token used to authorize for future request")
  
    public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Login user (User user) {
    this.user = user;
    return this;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Login login = (Login) o;
    return Objects.equals(this.token, login.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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
