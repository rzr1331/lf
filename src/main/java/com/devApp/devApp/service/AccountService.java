package com.devApp.devApp.service;

import com.devApp.devApp.model.domain.Account;
import com.devApp.devApp.model.dto.LoginRequestDto;
import com.devApp.devApp.model.dto.LoginResponseDto;
import com.devApp.devApp.repository.AccountRepository;
import com.devApp.devApp.util.UniqueIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UniqueIdGenerator uniqueIdGenerator;

    public void createTempLogins() {

        if (accountRepository.findByUserName("avichal") != null) {
            return;
        }

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

    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) throws Exception{
        Account account = accountRepository.findByUserName(loginRequestDto.getUserName());
        if (ObjectUtils.isEmpty(account)) {
            throw new Exception("Invalid login credentials.");
        }

        // I know this is a very basic way to do this, hashing, oAuth2 many things can be implemented.
        // Will add more layers on topk of it once the complete project is completed.
        if (!account.getPassword().equals(loginRequestDto.getPassword())) {
            throw new Exception("Invalid login credentials.");
        }

        LoginResponseDto loginResponseDto = LoginResponseDto.Builder.loginResponseDto()
            .withUserName(account.getUserName())
            .withAccountId(account.getAccountId())
            .withToken(account.getToken())
            .build();
        return loginResponseDto;
    }

}
