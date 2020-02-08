package com.devApp.devApp.service;

import com.devApp.devApp.model.domain.Account;
import com.devApp.devApp.model.dto.LoginRequestDto;
import com.devApp.devApp.model.dto.LoginResponseDto;
import com.devApp.devApp.repository.AccountRepository;
import com.devApp.devApp.util.UniqueIdGenerator;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javax.naming.AuthenticationException;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UniqueIdGenerator uniqueIdGenerator;

    public void createTempLogins() {

        //if (accountRepository.findByUserName("avichal") != null) {
        //    return;
        //}

        Account avichal = Account.Builder.account()
            .withAccountId(uniqueIdGenerator.getUniqueId())
            .withUserName("avichal")
            .withPassword("avichal")
            .withToken(uniqueIdGenerator.getUniqueId())
            .build();

        Account abhijit = Account.Builder.account()
            .withAccountId(uniqueIdGenerator.getUniqueId())
            .withUserName("abhijit")
            .withPassword("abhijit")
            .withToken(uniqueIdGenerator.getUniqueId())
            .build();

        Account prabhat = Account.Builder.account()
            .withAccountId(uniqueIdGenerator.getUniqueId())
            .withUserName("prabhat")
            .withPassword("prabhat")
            .withToken(uniqueIdGenerator.getUniqueId())
            .build();


        accountRepository.save(avichal);
        accountRepository.save(abhijit);
        accountRepository.save(prabhat);
    }

    public LoginResponseDto loginAccount(LoginRequestDto loginRequestDto) throws AuthenticationException{
        Account account = accountRepository.findByUserName(loginRequestDto.getUserName());
        if (ObjectUtils.isEmpty(account)) {
            logger.error("Invalid userName");
            throw new AuthenticationException("Invalid login credentials.");
        }

        // I know this is a very basic way to do this, hashing, oAuth2 many things can be implemented.
        // Will add more layers on topk of it once the complete project is completed.
        if (!account.getPassword().equals(loginRequestDto.getPassword())) {
            logger.error("Invalid password for user : {}", loginRequestDto.getUserName());
            throw new AuthenticationException("Invalid login credentials.");
        }

        logger.info("Succesfully logged in user : {}", loginRequestDto.getUserName());

        Account updatedAccount =  updateTokenForAccountId(account.getAccountId(), uniqueIdGenerator.getUniqueId());

        LoginResponseDto loginResponseDto = LoginResponseDto.Builder.loginResponseDto()
            .withUserName(updatedAccount.getUserName())
            .withAccountId(updatedAccount.getAccountId())
            .withToken(updatedAccount.getToken())
            .build();
        return loginResponseDto;
    }

    public String getAccountIdByToken(@NotNull String token) throws Exception{
        String accountId = accountRepository.getAccountIdByToken(token);
        if (ObjectUtils.isEmpty(accountId)) {
            throw new Exception("Failed to authenticate user.");
        }
        return accountId;
    }

    @Transactional
    private Account updateTokenForAccountId(@NotNull String accountId, @NotNull String token) {
        Account account = accountRepository.findByAccountId(accountId);
        account.setToken(token);
        return accountRepository.save(account);
    }

    @Transactional
    public Boolean logOutAccount(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (ObjectUtils.isEmpty(account)) {
            return false;
        }
        account.setToken(null);
        accountRepository.save(account);
        return true;
    }
}
