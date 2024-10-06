package com.abhaempire.complifybook.exception;

import com.abhaempire.complifybook.enums.ErrorCategory;
import com.abhaempire.complifybook.enums.ExceptionTypes;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
public final class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception ex) {
        log.error(String.format("Error Message: %s, Root Casue:%s, Error Stack: %s",
            ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex), ExceptionUtils.getStackTrace(ex)));
        var httpStatusCode = getHttpStatusCode(ex);
        List<BaseResponseError> errors = buildErrors(ex);
//        var response = ResponseEntity.badRequest(errors);
        return new ResponseEntity<>(errors, httpStatusCode);
    }

    private List<BaseResponseError> buildErrors(Exception ex) {
        if (ex instanceof AbhaBaseRunTimeException aex) {
            return aex.getExceptionList();
        } else if (ex instanceof HttpMessageNotReadableException hmnRdEx) {
            return Collections.singletonList(BaseResponseError.builder().businessErrorCode(10000)
                .errorMessage(ex.getMessage())
                .errorCategory(ErrorCategory.ERROR)
                .exType(ExceptionTypes.BAD_REQUEST_ERROR)
                // .errorStack("null") // TODO: Have to set this properly ...
                .build());
        }
        // call default error builder
        return buildDefaultErrors(ex);
    }


    private List<BaseResponseError> buildDefaultErrors(Exception ex) {
        return Collections.singletonList(BaseResponseError.builder().businessErrorCode(0)
            .errorMessage(ex.getMessage())
            .errorCategory(ErrorCategory.ERROR)
            .exType(ExceptionTypes.INTERNAL_ERROR)
            // .errorStack("null") // TODO: Have to set this properly ...
            .build());
    }

    private HttpStatus getHttpStatusCode(Exception ex) {
        var exType = ex instanceof AbhaBaseRunTimeException ?
            ((AbhaBaseRunTimeException) ex).getExceptionList().get(0).exType() :
            ExceptionTypes.UNKNOWN_ERROR;
        return switch (exType) {
            case VALIDATION_ERROR, BAD_REQUEST_ERROR -> HttpStatus.BAD_REQUEST;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    

}
