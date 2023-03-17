package br.com.nunesmaia.apiUserViaCep.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UmDTO {

    @NotBlank(message = "Email Vazio")
    private String email;

    @NotBlank(message = "Senha Vazia")
    private String password;
}
