package itmo.rbdplab2.client;

public class BadRequestException extends Exception{
    private final String message;
    public BadRequestException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
