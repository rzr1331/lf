package com.devApp.devApp.model.dto;

import java.util.Map;

public class UrlDataResponseDto {

    private String url;
    private String accountId;
    private String userDataId;

    public UrlDataResponseDto(String url, String accountId, String userDataId) {
        this.url = url;
        this.accountId = accountId;
        this.userDataId = userDataId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }

    public static interface UrlStep {
        AccountIdStep withUrl(String url);
    }

    public static interface AccountIdStep {
        UserDataIdStep withAccountId(String accountId);
    }

    public static interface UserDataIdStep {
        BuildStep withUserDataId(String userDataId);
    }

    public static interface BuildStep {
        UrlDataResponseDto build();
    }

    public static class Builder implements UrlStep, AccountIdStep, UserDataIdStep, BuildStep {
        private String url;
        private String accountId;
        private String userDataId;

        private Builder() {
        }

        public static UrlStep urlDataResponseDto() {
            return new Builder();
        }

        @Override
        public AccountIdStep withUrl(String url) {
            this.url = url;
            return this;
        }

        @Override
        public UserDataIdStep withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        @Override
        public BuildStep withUserDataId(String userDataId) {
            this.userDataId = userDataId;
            return this;
        }

        @Override
        public UrlDataResponseDto build() {
            return new UrlDataResponseDto(
                this.url,
                this.accountId,
                this.userDataId
            );
        }
    }
}
