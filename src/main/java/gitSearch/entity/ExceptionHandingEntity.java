package gitSearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionHandingEntity {
    @JsonProperty("status")
    private String statusCode;
    @JsonProperty("message")
    private String statusMessage;

    public ExceptionHandingEntity(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public ExceptionHandingEntity() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
