package com.userservice.controller;

import com.userservice.entity.Login;
import com.userservice.entity.User;
import com.userservice.header.HeaderGenerator;
import com.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SignInController {
    @Autowired
    private UserService userService;

    @Autowired
    private HeaderGenerator headerGenerator;

    @PostMapping(value = "/login")
    public String doLogin(@RequestBody Login login, HttpServletRequest request) {
        if (login != null)
            try {
                return userService.doLogin(login);
//                return new ResponseEntity<User>(
//                        login,
//                        headerGenerator.getHeadersForSuccessPostMethod(request, login.getUserName(),
//                        HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                return "INTERNAL_SERVER_ERROR";
//                        new ResponseEntity<Login>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return "BAD_REQUEST";
//                new ResponseEntity<Login>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/logout")
    public String doLogout(@RequestBody Login login, HttpServletRequest request) {
        if (login != null)
            try {
                return userService.doLogout(login);
//                return new ResponseEntity<User>(
//                        login,
//                        headerGenerator.getHeadersForSuccessPostMethod(request, login.getUserName(),
//                        HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                return "INTERNAL_SERVER_ERROR";
//                        new ResponseEntity<Login>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return "BAD_REQUEST";
    }
//
}
