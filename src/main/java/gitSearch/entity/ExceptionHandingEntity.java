package gitSearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionHandingEntity {
    @JsonProperty("status")
    private String statusCode;
    @JsonProperty("message")
    private String statusMessage;

    public ExceptionHandingEntity() {
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
