package com.devApp.devApp.repository;

import com.devApp.devApp.model.domain.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByAccountId(String accountId);
}
