package java16.exceptions.handler;

import java16.exceptions.ForbidenException;
import java16.exceptions.NoVacancyException;
import java16.exceptions.NotFoundException;
import java16.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    // 404 - Объект табылган жок
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerNotFoundException(NotFoundException e) {
        return ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }

    // 404 - Колдонуучу табылган жок
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerUserNotFoundException(UsernameNotFoundException e) {
        return ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }

    // 403 - Уруксат жок
    @ExceptionHandler(ForbidenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleForbidenException(ForbidenException e) {
        return ExceptionResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .message(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }

    // 500 - Ички сервер катасы
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleRuntimeException(RuntimeException e) {
        return ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Серверде ката чыкты: " + e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }

    // 409 - Вакансия жок (15 кызматкерден көп)
    @ExceptionHandler(NoVacancyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handlerNoVacancyException(NoVacancyException e) {
        return ExceptionResponse.builder()
                .status(HttpStatus.CONFLICT)
                .message("Ашыкча кызматкер ишке алынбайт: " + e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }
}
