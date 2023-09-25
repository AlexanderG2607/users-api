
package com.globalogic.bci.usersapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "email",
    "password",
    "phones"
})
public class CreateUserRequestDTO {

    @JsonProperty("name")
    public String name;

    @NotEmpty
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @JsonProperty("email")
    public String email;

    @NotEmpty
    @Pattern(regexp = "^(?=(?:[^A-Z]*[A-Z]){1})(?=(?:[^0-9]*[0-9]){2,})(?!.*[^a-zA-Z0-9]).{8,12}$")
    @JsonProperty("password")
    public String password;

    @JsonProperty("phones")
    public List<PhoneDTO> phones;

}
