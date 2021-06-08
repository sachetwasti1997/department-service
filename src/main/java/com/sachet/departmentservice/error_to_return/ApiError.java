package com.sachet.departmentservice.error_to_return;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private final Long timeStamp = new Date().getTime();
    private Integer responseStatus;
    private String message;
    private String url;
    private Map<String, String> validationErrors;

    public ApiError(Integer responseStatus, String message, String url) {
        this.responseStatus = responseStatus;
        this.message = message;
        this.url = url;
    }

    public ApiError(Integer responseStatus, String message) {
        this.responseStatus = responseStatus;
        this.message = message;
    }

    public ApiError() {
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
