package com.devApp.devApp.model.dto;

public class LoginRequestDto {

    private String userName;

    private String password;

    public LoginRequestDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
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

    public static interface UserNameStep {
        PasswordStep withUserName(String userName);
    }

    public static interface PasswordStep {
        BuildStep withPassword(String password);
    }

    public static interface BuildStep {
        LoginRequestDto build();
    }

    public static class Builder implements UserNameStep, PasswordStep, BuildStep {
        private String userName;
        private String password;

        private Builder() {
        }

        public static UserNameStep loginDto() {
            return new Builder();
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
        public LoginRequestDto build() {
            return new LoginRequestDto(
                this.userName,
                this.password
            );
        }
    }
}
