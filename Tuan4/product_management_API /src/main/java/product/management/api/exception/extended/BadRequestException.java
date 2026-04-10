package product.management.api.exception.extended;

public class BadRequestException extends AppException {
    public BadRequestException(String message) {
        super(400, message);
    }
}
