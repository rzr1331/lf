package com.devApp.devApp.model.dto;

import java.util.Map;

public class UrlDataRequestDto {

    private String url;
    private Map meta;

    public UrlDataRequestDto(String url, Map meta) {
        this.url = url;
        this.meta = meta;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getMeta() {
        return meta;
    }

    public void setMeta(Map meta) {
        this.meta = meta;
    }

    public static interface UrlStep {
        MetaStep withUrl(String url);
    }

    public static interface MetaStep {
        BuildStep withMeta(Map meta);
    }

    public static interface BuildStep {
        UrlDataRequestDto build();
    }

    public static class Builder implements UrlStep, MetaStep, BuildStep {
        private String url;
        private Map meta;

        private Builder() {
        }

        public static UrlStep urlDataDto() {
            return new Builder();
        }

        @Override
        public MetaStep withUrl(String url) {
            this.url = url;
            return this;
        }

        @Override
        public BuildStep withMeta(Map meta) {
            this.meta = meta;
            return this;
        }

        @Override
        public UrlDataRequestDto build() {
            return new UrlDataRequestDto(
                this.url,
                this.meta
            );
        }
    }
}
