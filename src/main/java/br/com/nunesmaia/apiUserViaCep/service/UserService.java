package br.com.nunesmaia.apiUserViaCep.service;

import br.com.nunesmaia.apiUserViaCep.model.User;
import org.springframework.stereotype.Service;


@Service
public class UserService {


        public void reg(User u) {
            if (u.getName() ==null || u.getName().trim().isEmpty()){
                System.out.println("invalid name");
            }

            if (u.getEmail() ==null || u.getEmail().trim().isEmpty()){
                System.out.println("invalid email");
            }

            if (u.getPassword() ==null || u.getPassword().trim().isEmpty()){
                System.out.println("invalid password");
            }

            if (u.getPostalCode() ==null || u.getPostalCode().trim().isEmpty()){
                System.out.println("invalid password");
            }
        }






}
