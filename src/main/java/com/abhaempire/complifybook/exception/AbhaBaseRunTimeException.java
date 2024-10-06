package com.abhaempire.complifybook.exception;

import com.abhaempire.complifybook.enums.ExceptionTypes;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class AbhaBaseRunTimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ExceptionTypes exType;
    private boolean isBusinessError;
    private int businessErrorCode;
    private List<BaseResponseError> exceptionList;

    public AbhaBaseRunTimeException(final ExceptionTypes exType, final Throwable baseEx, final boolean isBusinessError, final int businessErrorCode, final String errorMsg, final List<BaseResponseError> exceptionList) {
        super(errorMsg, baseEx);
        this.exType = exType;
        this.isBusinessError = isBusinessError;
        this.businessErrorCode = businessErrorCode;
        this.exceptionList = exceptionList;
    }

    public AbhaBaseRunTimeException(final Throwable baseEx, final String errorMsg, ExceptionTypes exType, boolean isBusinessError, int businessErrorCode) {
        super(errorMsg, baseEx);
        this.exType = exType;
        this.isBusinessError = isBusinessError;
        this.businessErrorCode = businessErrorCode;
        exceptionList = new ArrayList<>(5);
    }

    public AbhaBaseRunTimeException(final Throwable baseEx, final String errorMsg, List<BaseResponseError> exceptionList) {
        super(errorMsg, baseEx);
        this.exceptionList = exceptionList;
    }

    public AbhaBaseRunTimeException(final Throwable baseEx, final String errorMsg) {
        super(errorMsg, baseEx);
        exceptionList = new ArrayList<>(5);
    }

    /**
     * Creates new {@link AbhaBaseRunTimeException} object based on {@link BusinessExceptionDefintion}
     *
     * @param exceptionDefintion Pre defined business exception
     * @param msgParams params for creating error message
     */
    public AbhaBaseRunTimeException(BusinessExceptionDefintion exceptionDefintion, String... msgParams) {
        final BaseResponseError error = BaseResponseError.builder()
            .businessErrorCode(exceptionDefintion.getBusinessErrorCode())
            .exType(exceptionDefintion.getExceptionType())
            .errorMessage(String.format(exceptionDefintion.getErrorMessage(), (Object) msgParams))
            .errorCategory(exceptionDefintion.getErrorCategory())
            .build();
        this.exType = exceptionDefintion.getExceptionType();
        this.businessErrorCode = exceptionDefintion.getBusinessErrorCode();
        this.exceptionList = new ArrayList<>();
        this.exceptionList.add(error);
    }


    /**
     * Add exception in existing exception list.
     * @param ex Exception which needs to be add.
     */
    public void addError(BaseResponseError ex) {
        this.exceptionList.add(ex);
    }

    /**
     * Add all exceptions in existing exception list.
     * @param exs List of exceptions which needs to be add.
     */
    public void addAllError(List<BaseResponseError> exs) {
        this.exceptionList.addAll(exs);
    }

    public static AryaBaseRunTimeExceptionBuilder builder() {
        return new AryaBaseRunTimeExceptionBuilder();
    }


    /**
     * Builder class of AryaBaseRunTimeException.
     * Note - Can not use {@link lombok.Builder} because base class {@link RuntimeException} don't have builder pattern enabled.
     **/
    public static class AryaBaseRunTimeExceptionBuilder {
        private ExceptionTypes exType;
        private Throwable baseEx;
        private boolean isBusinessError;
        private int businessErrorCode;
        private String errorMsg;
        private List<BaseResponseError> exceptionList;

        public AryaBaseRunTimeExceptionBuilder() {
            this.exceptionList = new ArrayList<>(5);
        }

        /**
         * Add exception in existing exception list.
         * @param ex Exception which needs to be add.
         */
        public AryaBaseRunTimeExceptionBuilder addError(BaseResponseError ex) {
            this.errorMsg = ex.errorMessage();
            this.businessErrorCode = ex.businessErrorCode();
            this.exType = ex.exType();
            this.exceptionList.add(ex);
            return this;
        }

        /**
         * Add all exceptions in existing exception list.
         * @param exs List of exceptions which needs to be add.
         */
        public AryaBaseRunTimeExceptionBuilder addAllError(List<BaseResponseError> exs) {
            this.exceptionList.addAll(exs);
            return this;
        }

        public AryaBaseRunTimeExceptionBuilder setExceptionType(ExceptionTypes exType) {
            this.exType = exType;
            return this;
        }

        public AryaBaseRunTimeExceptionBuilder setBaseException(Throwable baseEx) {
            this.baseEx = baseEx;
            return this;
        }

        public AryaBaseRunTimeExceptionBuilder setBusinessError(boolean isBusinessError) {
            this.isBusinessError = isBusinessError;
            return this;
        }

        public AryaBaseRunTimeExceptionBuilder setBusinessErrorCode(int businessErrorCode) {
            this.businessErrorCode = businessErrorCode;
            return this;
        }

        public AryaBaseRunTimeExceptionBuilder setErrorMessage(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }

        public AbhaBaseRunTimeException build() {
            return new AbhaBaseRunTimeException(exType, baseEx, isBusinessError, businessErrorCode, errorMsg, this.exceptionList);
        }
    }
}
