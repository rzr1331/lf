package com.devApp.devApp.web;

import com.devApp.devApp.model.dto.UrlDataRequestDto;
import com.devApp.devApp.model.dto.UrlDataResponseDto;
import com.devApp.devApp.service.AccountService;
import com.devApp.devApp.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user/data")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/url/submit", method = POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity loginAccount(@RequestBody UrlDataRequestDto urlDataRequestDto,
        @RequestHeader("X-LF-TOKEN") String token) throws Exception {
        // TODO : Implement RequestContextInterceptor to update userContext;
        String accountId = accountService.getAccountIdByToken(token);
        userDataService.submitUserUrlData(urlDataRequestDto, accountId);
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/url", method = GET)
    public ResponseEntity getUserUrlData(@RequestHeader("X-LF-TOKEN") String token) throws Exception {
        // TODO : Implement RequestContextInterceptor to update userContext;
        String accountId = accountService.getAccountIdByToken(token);
        UrlDataResponseDto urlDataResponseDto = userDataService.getUserUrlData(accountId);
        return new ResponseEntity(urlDataResponseDto, HttpStatus.OK);
    }
}