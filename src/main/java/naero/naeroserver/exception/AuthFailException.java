package naero.naeroserver.exception;

public class AuthFailException extends RuntimeException {
    public AuthFailException(String message) {
        super(message);
    }
}