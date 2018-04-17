package exception;

public class BusinessException extends Exception {
    private String message;

    public BusinessException() {

    }

    public BusinessException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
