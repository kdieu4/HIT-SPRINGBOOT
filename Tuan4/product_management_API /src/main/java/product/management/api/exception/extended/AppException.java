package product.management.api.exception.extended;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private final int errorCode;

    public AppException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
