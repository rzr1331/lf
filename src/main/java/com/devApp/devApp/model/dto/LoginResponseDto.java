package com.devApp.devApp.model.dto;

public class LoginResponseDto {

    private String userName;

    private String accountId;

    private String token;

    public LoginResponseDto(String userName, String accountId, String token) {
        this.userName = userName;
        this.accountId = accountId;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static interface UserNameStep {
        AccountIdStep withUserName(String userName);
    }

    public static interface AccountIdStep {
        TokenStep withAccountId(String accountId);
    }

    public static interface TokenStep {
        BuildStep withToken(String token);
    }

    public static interface BuildStep {
        LoginResponseDto build();
    }

    public static class Builder implements UserNameStep, AccountIdStep, TokenStep, BuildStep {
        private String userName;
        private String accountId;
        private String token;

        private Builder() {
        }

        public static UserNameStep loginResponseDto() {
            return new Builder();
        }

        @Override
        public AccountIdStep withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        @Override
        public TokenStep withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        @Override
        public BuildStep withToken(String token) {
            this.token = token;
            return this;
        }

        @Override
        public LoginResponseDto build() {
            return new LoginResponseDto(
                this.userName,
                this.accountId,
                this.token
            );
        }
    }
}
