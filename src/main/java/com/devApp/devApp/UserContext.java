package com.devApp.devApp;

public class UserContext {

    private String token;
    private String accountId;
    private String userName;

    public UserContext() {
    }

    public UserContext(String token, String accountId, String userName) {
        this.token = token;
        this.accountId = accountId;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
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

    public static interface TokenStep {
        AccountIdStep withToken(String token);
    }

    public static interface AccountIdStep {
        UserNameStep withAccountId(String accountId);
    }

    public static interface UserNameStep {
        BuildStep withUserName(String userName);
    }

    public static interface BuildStep {
        UserContext build();
    }

    public static class Builder implements TokenStep, AccountIdStep, UserNameStep, BuildStep {
        private String token;
        private String accountId;
        private String userName;

        private Builder() {
        }

        public static TokenStep userContext() {
            return new Builder();
        }

        @Override
        public AccountIdStep withToken(String token) {
            this.token = token;
            return this;
        }

        @Override
        public UserNameStep withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        @Override
        public BuildStep withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        @Override
        public UserContext build() {
            return new UserContext(
                this.token,
                this.accountId,
                this.userName
            );
        }
    }
}
