package com.devApp.devApp.model.dto;

public class AccountDto {

    private String accountId;
    private String userName;
    private String password;

    public AccountDto(String accountId, String userName, String password) {
        this.accountId = accountId;
        this.userName = userName;
        this.password = password;
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

    public static interface AccountIdStep {
        UserNameStep withAccountId(String accountId);
    }

    public static interface UserNameStep {
        PasswordStep withUserName(String userName);
    }

    public static interface PasswordStep {
        BuildStep withPassword(String password);
    }

    public static interface BuildStep {
        AccountDto build();
    }

    public static class Builder implements AccountIdStep, UserNameStep, PasswordStep, BuildStep {
        private String accountId;
        private String userName;
        private String password;

        private Builder() {
        }

        public static AccountIdStep accountDto() {
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
        public BuildStep withPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public AccountDto build() {
            return new AccountDto(
                this.accountId,
                this.userName,
                this.password
            );
        }
    }
}
