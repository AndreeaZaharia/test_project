package dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
    private Object body;
    private String message;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
