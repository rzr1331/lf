package com.devApp.devApp.repository;

import com.devApp.devApp.model.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountId(String accountId);

    Account findByUserName(String userName);

    @Query(value = "SELECT accountId from account where token = ?1", nativeQuery = true)
    String getAccountIdByToken(String token);

}
