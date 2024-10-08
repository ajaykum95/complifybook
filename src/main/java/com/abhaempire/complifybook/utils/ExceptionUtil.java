package com.abhaempire.complifybook.utils;

import com.abhaempire.complifybook.exception.AbhaBaseRunTimeException;
import com.abhaempire.complifybook.exception.BaseResponseError;
import com.abhaempire.complifybook.exception.BusinessExceptionDefintion;

public final class ExceptionUtil {
    private ExceptionUtil() {
    }
    public static AbhaBaseRunTimeException buildException(BusinessExceptionDefintion exception,
                                                          Object... msgParams) {
        var error = BaseResponseError.builder()
                .businessErrorCode(exception.getBusinessErrorCode())
                .exType(exception.getExceptionType())
                .errorMessage(String.format(exception.getErrorMessage(), msgParams))
                .errorCategory(exception.getErrorCategory())
                .build();
        return AbhaBaseRunTimeException.builder()
                .addError(error)
                .build();
    }
}
