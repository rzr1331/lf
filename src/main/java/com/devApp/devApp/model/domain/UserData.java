package com.devApp.devApp.model.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity(name = "userData")
@Table(name = "userData", indexes = {
    @Index(name = "index_userDataId", columnList = "userDataId")
})
public class UserData {

    @Id
    @Column(name = "userDataId", nullable = false, unique = true)
    private String userDataId;

    @Column(name = "accountId", unique = true)
    private String accountId;

    @Column(name = "urlData")
    private String urlData;

    public UserData() {
    }

    public UserData(String userDataId, String accountId, String urlData) {
        this.userDataId = userDataId;
        this.accountId = accountId;
        this.urlData = urlData;
    }

    public String getUserDataId() {
        return userDataId;
    }

    public void setUserDataId(String userDataId) {
        this.userDataId = userDataId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUrlData() {
        return urlData;
    }

    public void setUrlData(String urlData) {
        this.urlData = urlData;
    }

    public static interface UserDataIdStep {
        AccountIdStep withUserDataId(String userDataId);
    }

    public static interface AccountIdStep {
        UrlDataStep withAccountId(String accountId);
    }

    public static interface UrlDataStep {
        BuildStep withUrlData(String urlData);
    }

    public static interface BuildStep {
        UserData build();
    }

    public static class Builder implements UserDataIdStep, AccountIdStep, UrlDataStep, BuildStep {
        private String userDataId;
        private String accountId;
        private String urlData;

        private Builder() {
        }

        public static UserDataIdStep userData() {
            return new Builder();
        }

        @Override
        public AccountIdStep withUserDataId(String userDataId) {
            this.userDataId = userDataId;
            return this;
        }

        @Override
        public UrlDataStep withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        @Override
        public BuildStep withUrlData(String urlData) {
            this.urlData = urlData;
            return this;
        }

        @Override
        public UserData build() {
            return new UserData(
                this.userDataId,
                this.accountId,
                this.urlData
            );
        }
    }
}
