package com.devApp.devApp.model.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity(name = "account")
@Table(name = "account", indexes = {
    @Index(name = "index_accountId", columnList = "accountId")
})
public class Account {

    @Id
    @Column(name = "accountId", nullable = false)
    private String accountId;

    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    // temp-storing token in db, usually it is stored in redis like caches.
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    public Account() {
    }

    public Account(String accountId, String userName, String password, String token) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
        this.token = token;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static interface AccountIdStep {
        UserNameStep withAccountId(String accountId);
    }

    public static interface UserNameStep {
        PasswordStep withUserName(String userName);
    }

    public static interface PasswordStep {
        TokenStep withPassword(String password);
    }

    public static interface TokenStep {
        BuildStep withToken(String token);
    }

    public static interface BuildStep {
        Account build();
    }

    public static class Builder implements AccountIdStep, UserNameStep, PasswordStep, TokenStep, BuildStep {
        private String accountId;
        private String userName;
        private String password;
        private String token;

        private Builder() {
        }

        public static AccountIdStep account() {
            return new Builder();
        }

        @Override
        public UserNameStep withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        @Override
        public PasswordStep withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        @Override
        public TokenStep withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public BuildStep withToken(String token) {
            this.token = token;
            return this;
        }

        @Override
        public Account build() {
            return new Account(
                this.accountId,
                this.userName,
                this.password,
                this.token
            );
        }
    }
}
