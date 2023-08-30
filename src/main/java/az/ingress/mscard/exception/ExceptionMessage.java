package az.ingress.mscard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    UNEXPECTED_ERROR("UNEXPECTED_ERROR", "Unexpected error occurred"),
    CARD_NOT_FOUND("CARD_NOT_FOUND", "Card not found");

    private final String code;
    private final String message;

}
