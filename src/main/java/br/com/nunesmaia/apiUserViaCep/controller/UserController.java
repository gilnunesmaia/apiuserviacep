package br.com.nunesmaia.apiUserViaCep.controller;

import br.com.nunesmaia.apiUserViaCep.model.User;
import br.com.nunesmaia.apiUserViaCep.model.dto.DoisDTO;
import br.com.nunesmaia.apiUserViaCep.model.dto.UmDTO;
import br.com.nunesmaia.apiUserViaCep.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody User newUser) throws Exception {
        userService.reg(newUser);
    }

    @PostMapping("/login")
    public DoisDTO login(@RequestBody @Valid UmDTO umdto) throws Exception {

        return userService.login(umdto);

    }


}
