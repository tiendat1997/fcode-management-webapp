package com.tiendat.spring_webmvc.BootDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendat.spring_webmvc.BootDemo.model.Account;
import com.tiendat.spring_webmvc.BootDemo.respository.AccountRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AccountRepository userRespository;

    @GetMapping(value = "/all")
    public List<Account> findAll() {
        return userRespository.findAll();
    }

    @GetMapping(value = "/{name}")
    public Account findByName(@PathVariable final String name) {
        return userRespository.findByUsername(name);
    }

    @PostMapping(value = "/load")
    public Account load(@RequestBody final Account account) {
        userRespository.save(account);
        return userRespository.findByUsername(account.getUsername());
    }

}
