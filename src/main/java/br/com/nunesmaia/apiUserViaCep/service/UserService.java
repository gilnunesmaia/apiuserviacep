package br.com.nunesmaia.apiUserViaCep.service;

import br.com.nunesmaia.apiUserViaCep.exception.InvalidDataException;
import br.com.nunesmaia.apiUserViaCep.model.User;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    public void reg(User u) throws InvalidDataException {
        this.validateEAP(u.getEmail(), u.getPassword());
        if (u.getName() == null || u.getName().trim().isEmpty()){
            throw new InvalidDataException("Nome vazio");
        }



    }

    private void validateEAP(String email, String password) throws InvalidDataException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("email vazio");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidDataException("senha vazia");
        }
    }


}
