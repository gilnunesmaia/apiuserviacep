package br.com.nunesmaia.apiUserViaCep.service;

import br.com.nunesmaia.apiUserViaCep.exception.InvalidDataException;
import br.com.nunesmaia.apiUserViaCep.model.User;
import br.com.nunesmaia.apiUserViaCep.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public void reg(User u) throws Exception {

        this.validateEAP(u.getEmail(), u.getPassword(), u.getName(), u.getPostalCode());

        String password = this.encryptPassword(u.getPassword());

        u.setPassword(password);

        userRepository.save(u);


    }


    private void validateEAP(String email, String password, String name, String PC) throws InvalidDataException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("email vazio");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidDataException("senha vazia");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("nome inválido");
        }
        if (PC == null || PC.trim().isEmpty()) {
            throw new InvalidDataException("CEP inválido");
        }

    }

    private String encryptPassword(String password) throws Exception {
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception();
        }
        m.update(password.getBytes(), 0, password.length());
        String pass = new BigInteger(1, m.digest()).toString(16);

        return pass;
    }


}
