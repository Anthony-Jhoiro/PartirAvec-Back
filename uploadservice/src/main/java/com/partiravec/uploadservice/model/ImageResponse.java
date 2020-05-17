package com.partiravec.uploadservice.model;

public class ImageResponse {
    String url;
    String error;

    public ImageResponse() {
    }

    public String getUrl() {
        return url;
    }

    public String getError() {
        return error;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setError(String error) {
        this.error = error;
    }
}
