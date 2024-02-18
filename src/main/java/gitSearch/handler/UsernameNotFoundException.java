package gitSearch.handler;

import org.springframework.http.HttpStatusCode;

public class UsernameNotFoundException extends RuntimeException {

    private String statusCode;

    public UsernameNotFoundException(HttpStatusCode statusCode, String statusText) {
        super(statusText);
        this.statusCode = String.valueOf(statusCode.value());
    }

    public String getStatusCode() {
        return statusCode;
    }
}
