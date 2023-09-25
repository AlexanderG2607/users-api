
package com.globalogic.bci.usersapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "email",
    "password",
    "phones"
})
public class CreateUserResponseDTO {

    @JsonProperty("id")
    public UUID id;
    @JsonProperty("created")
    public String created;
    @JsonProperty("lastLogin")
    public String lastLogin;
    @JsonProperty("token")
    public String token;
    @JsonProperty("isActive")
    public Boolean isActive;

}
