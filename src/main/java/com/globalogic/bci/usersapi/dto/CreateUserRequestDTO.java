
package com.globalogic.bci.usersapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "email",
        "password",
        "phones"
})
public class CreateUserRequestDTO {

    @JsonProperty("name")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "El formato del email debe ser \"aaaaaaa@undominio.algo\"")
    @JsonProperty("email")
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(?=(?:[^A-Z]*[A-Z]){1})(?=(?:[^0-9]*[0-9]){2,})(?!.*[^a-zA-Z0-9]).{8,12}$",
            message = "El password debe tener solo una Mayúscula y solamente dos números " +
                    "(no necesariamente consecutivos), en combinación de letras minúsculas," +
                    " largo máximo de 12 y mínimo 8. ")
    @JsonProperty("password")
    private String password;

    @JsonProperty("phones")
    private List<PhoneDTO> phones;

}
