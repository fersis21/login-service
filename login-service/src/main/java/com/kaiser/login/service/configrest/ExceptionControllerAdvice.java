package com.kaiser.login.service.configrest;

import com.kaiser.login.service.login.api.CustomException;
import com.kaiser.login.service.login.api.ErrorResponse;
import com.kaiser.login.service.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Fernando Apaza
 * Date: 27/04/2026
 */
@ControllerAdvice
public class ExceptionControllerAdvice implements CommonResponse {

    private static final String UNCONTROLLED_ERROR = "UNCONTROLLED_ERROR";
    private static final String RUNTIME_ERROR = "RUNTIME_ERROR";
    private static final String CUSTOM_EXCEPTION = "CUSTOM_EXCEPTION";
    private static final String HTTP_MESSAGE_NOT_READABLE = "HTTP_MESSAGE_NOT_READABLE";
    private static final String ARGUMENT_NOT_VALID = "ARGUMENT_NOT_VALID";

    @Value("${fie-enable-default-message}")
    private boolean enableDefaultMessage;

    @Value("${fie-httpstatus500.out-message}")
    private String httpStatus500OutMessage;

    @Value("${fie-httpstatus400.out-message}")
    private String httpStatus400OutMessage;

    @Value("${fie-httpstatus410.out-message}")
    private String httpStatus410OutMessage;

    @Value("${fie-custom.service.code}")
    private String customServiceCode;

    @Autowired
    private Utils utils;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        //log.log(Level.SEVERE, UNCONTROLLED_ERROR, exception);
        return error(new ErrorResponse()
                .setCode("999")
                .setMessage(httpStatus500OutMessage)
                .setCause("An uncontrolled error has occurred - Message: [" + exception.getMessage() + "]")
        );
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeException(RuntimeException exception) {
        //log.log(Level.SEVERE, RUNTIME_ERROR, exception);
        if (exception.getCause() instanceof CustomException) {
            CustomException customException = (CustomException) exception.getCause();
            CustomException cause = utils.extractCustomException(customException.getMessage(), exception);
            String message = "";
            String messageCause = "";
            String code = cause != null ? "801" : "802";

            switch (customException.getHttpStatus()) {
                case GONE:
                    message = httpStatus410OutMessage;
                    break;
                case BAD_REQUEST:
                    message = cause != null ? cause.getMessage() : customException.getMessage();
                    if (message.isEmpty()) {
                        message = httpStatus400OutMessage;
                    }
                    break;
                default:
                    message = httpStatus500OutMessage;
                    messageCause = cause != null ? cause.getMessage() : customException.getMessage();
                    break;
            }

            return response(customException.getHttpStatus(),
                    new ErrorResponse()
                            .setCode(getCustomCode(customException, code))
                            .setMessage(message)
                            .setReference(customException.getUrlReference())
                            .setCause(messageCause)
            );
        } else {
            return error(new ErrorResponse()
                    .setCode("998")
                    .setMessage(httpStatus500OutMessage)
                    .setCause("A runtime error has occurred - Message: [" + exception.getMessage() + "]")
            );
        }
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customException(CustomException exception) {
        //log.log(Level.SEVERE, CUSTOM_EXCEPTION, exception);
        String message = "";
        if (exception.getHttpStatus() != null) {
            switch (exception.getHttpStatus()) {
                case GONE:
                    message = httpStatus410OutMessage;
                    break;
                case BAD_REQUEST:
                    message = exception.getMessage().isEmpty() ? httpStatus400OutMessage : exception.getMessage();
                    break;
                default:
                    message = httpStatus500OutMessage;
                    break;
            }
            ErrorResponse errorResponse = new ErrorResponse()
                    .setCode(getCustomCode(exception, "800"))
                    .setMessage(message)
                    .setCause(exception.getHttpStatus() != HttpStatus.BAD_REQUEST ? exception.getMessage() : null)
                    .setReference(exception.getUrlReference());
            return response(exception.getHttpStatus(), errorResponse);
        } else {
            return error(new ErrorResponse()
                    .setCode(getCustomCode(exception, "810"))
                    .setMessage(httpStatus500OutMessage)
                    .setCause(exception.getMessage())
                    .setReference(exception.getUrlReference())
            );
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpException(HttpMessageNotReadableException exception) {
        //log.log(Level.SEVERE, HTTP_MESSAGE_NOT_READABLE, exception);

        return badRequest(new ErrorResponse()
                .setCode("700")
                .setMessage(httpStatus400OutMessage)
                .setCause("Request body error")
        );
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleorgHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
        //log.log(Level.SEVERE, HTTP_MESSAGE_NOT_READABLE, exception);

        return badRequest(new ErrorResponse()
                .setCode("700")
                .setMessage(httpStatus400OutMessage)
                .setCause("Request body error - Message: [" + exception.getMessage() + "]")
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        //log.log(Level.SEVERE, ARGUMENT_NOT_VALID, exception);

        return badRequest(new ErrorResponse()
                .setCode("700")
                .setMessage(httpStatus400OutMessage)
                .setCause("Request body error - Message: [" + exception.getMessage() + "]")
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exception) {
        //log.log(Level.SEVERE, ARGUMENT_NOT_VALID, exception);
        List<String> errors = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(enableDefaultMessage ? "[" + fieldName + "] " + errorMessage : errorMessage);
            fields.add(fieldName);
        });
        String errorsString = String.join(", ", errors);

        ErrorResponse errorResponse = new ErrorResponse()
                .setCause(enableDefaultMessage ? errorsString : "Campos no válidos: " + String.join(", ", fields));

        if (enableDefaultMessage) {
            errorResponse.setCode("701").setMessage(httpStatus400OutMessage);
        } else {
            errorResponse.setCode("702").setMessage(errorsString);
        }

        return badRequest(errorResponse);
    }

    private String getCustomCode(CustomException exception, String defaultCode) {
        return exception.getCode() != null ? customServiceCode.concat(exception.getCode()) : defaultCode;
    }
}
