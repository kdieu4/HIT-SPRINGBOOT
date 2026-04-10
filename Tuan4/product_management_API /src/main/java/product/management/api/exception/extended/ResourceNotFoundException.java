package product.management.api.exception.extended;

public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(404, String.format("%s khong tim thay voi %s: '%s'", resource, field, value));
    }
}
