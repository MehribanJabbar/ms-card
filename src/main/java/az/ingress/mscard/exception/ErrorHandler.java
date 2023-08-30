package az.ingress.mscard.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception){
        log.error("Exception", exception);
        return new ErrorResponse(ExceptionMessage.UNEXPECTED_ERROR.getCode(), ExceptionMessage.UNEXPECTED_ERROR.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException exception){
        log.error("NotFoundException", exception);
        return new ErrorResponse(ExceptionMessage.CARD_NOT_FOUND.getCode(), ExceptionMessage.CARD_NOT_FOUND.getMessage());
    }
}
