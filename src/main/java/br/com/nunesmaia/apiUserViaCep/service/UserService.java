package br.com.nunesmaia.apiUserViaCep.service;

import br.com.nunesmaia.apiUserViaCep.exception.InvalidDataException;
import br.com.nunesmaia.apiUserViaCep.model.User;
import br.com.nunesmaia.apiUserViaCep.model.dto.DoisDTO;
import br.com.nunesmaia.apiUserViaCep.model.dto.UmDTO;
import br.com.nunesmaia.apiUserViaCep.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public DoisDTO login(UmDTO login) throws Exception {

        this.validLogin(login.getEmail(), login.getPassword());
        String passwordEncrypted = this.encryptPassword(login.getPassword());

        // FIXME precisa de regra de negocio para tratar usuarios nao existentes
        User user = userRepository.findByEmailAndPassword(login.getEmail(), passwordEncrypted);


        RestTemplate restTemplate = new RestTemplate();

        // FIXME o CEP esta fixo, correto seria CEP do usuario
        ResponseEntity<DoisDTO> resp = restTemplate.getForEntity("https://viacep.com.br/ws/40150080/json/", DoisDTO.class);

        DoisDTO dd = resp.getBody();


        dd.setName(user.getName());
        dd.setEmail(user.getEmail());


        return dd;

    }

    private void validLogin(String email, String password) throws InvalidDataException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("email vazio");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new InvalidDataException("senha vazia");
        }
    }


}
