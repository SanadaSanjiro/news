package com.springbox.news.errorhandling;

import com.springbox.news.exception.EntityNotFoundException;
import com.springbox.news.exception.ForbiddenException;
import com.springbox.news.web.model.ErrorResponse;
import com.springbox.news.web.model.ValidationErrorResponse;
import com.springbox.news.web.model.Violation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Отвечает за обработку возникающих исключений
 */
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    /**
     * Обрабатывает ConstraintViolationException, выбрасываемое при передаче в запросе параметров, не
     * соответствующих требованиям
     * @param e ConstraintViolationException
     * @return HttpStatus.BAD_REQUEST
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Violation(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    /**
     * Обрабатывает MethodArgumentNotValidException, выбрасываемое при передаче в запросе параметров, не
     * соответствующих требованиям
     * @param e MethodArgumentNotValidException
     * @return ValidationErrorResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    /**
     * Обрабатывает EntityNotFoundException, выбрасываемое если запрошенная сущность не найдена
     * @param e EntityNotFoundException
     * @return ErrorResponse
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse onEntityNotFoundException(
            EntityNotFoundException e
    ) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Обрабатывает MissingRequestHeaderException, выбрасываемое при отсутствии в запросе необходимого хедера.
     * Скорее всего, это будет пропущенный X-User-Id в запросах на изменение/удаление новостей или комментариев
     * @param e MissingRequestHeaderException
     * @return ErrorResponse
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse onMissingRequestHeaderException(
            MissingRequestHeaderException e
    ) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Обрабатывает AccessDeniedException, выбрасываемое при отсутствии прав на изменение объекта
     * @param e AccessDeniedException
     * @return ErrorResponse
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse onForbiddenException(
            ForbiddenException e
    ) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Обрабатывает непредвиденные исключения
     * @return ErrorResponse
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse onOtherException(Exception e) {
        e.printStackTrace();
        return new ErrorResponse("Ошибка сервера! Свяжитесь с поддержкой.");
    }
}