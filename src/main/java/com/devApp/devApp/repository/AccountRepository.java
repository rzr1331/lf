package com.devApp.devApp.repository;

import com.devApp.devApp.model.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountId(String accountId);

    Account findByUserName(String userName);

}
