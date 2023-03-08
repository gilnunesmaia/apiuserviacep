package br.com.nunesmaia.apiUserViaCep.controller;

import br.com.nunesmaia.apiUserViaCep.exception.InvalidDataException;
import br.com.nunesmaia.apiUserViaCep.model.User;
import br.com.nunesmaia.apiUserViaCep.model.dto.UserDTO;
import br.com.nunesmaia.apiUserViaCep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService user;

    @PostMapping("register")
    public void register(@RequestBody User newUser) throws InvalidDataException {
        user.reg(newUser);
    }

   // @PostMapping("/login")
   // public User login(@RequestBody UserDTO userdto){

    //}




}
