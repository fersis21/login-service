package com.kaiser.login.service.configrest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Fernando Apaza
 * Date: 27/04/2026
 */
public interface CommonResponse {
    default ResponseEntity notFound() {
        return response(HttpStatus.NOT_FOUND);
    }

    default <R> ResponseEntity<R> notFound(R r) {
        return response(HttpStatus.NOT_FOUND, r);
    }

    default ResponseEntity conflict() {
        return response(HttpStatus.CONFLICT);
    }

    default <R> ResponseEntity<R> conflict(R r) {
        return response(HttpStatus.CONFLICT, r);
    }

    default ResponseEntity badRequest() {
        return response(HttpStatus.BAD_REQUEST);
    }

    default <R> ResponseEntity<R> badRequest(R response) {
        return response(HttpStatus.BAD_REQUEST, response);
    }

    default <R> ResponseEntity<R> ok(R response) {
        return response(HttpStatus.OK, response);
    }

    default <R> ResponseEntity<R> error(R response) {
        return response(HttpStatus.INTERNAL_SERVER_ERROR, response);
    }

    default ResponseEntity response(HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).build();
    }

    default <R> ResponseEntity<R> response(HttpStatus httpStatus, R response) {
        return ResponseEntity.status(httpStatus).body(response);
    }
}
