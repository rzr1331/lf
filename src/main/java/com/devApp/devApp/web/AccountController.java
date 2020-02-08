package com.devApp.devApp.web;

import com.devApp.devApp.model.dto.LoginRequestDto;
import com.devApp.devApp.model.dto.LoginResponseDto;
import com.devApp.devApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = POST, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public LoginResponseDto loginAccount(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        return accountService.loginUser(loginRequestDto);
    }
}
