package pl.alex.exception;

public record ApiError(
        String code,
        String message
) {
}