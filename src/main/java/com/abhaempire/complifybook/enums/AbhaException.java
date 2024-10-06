package com.abhaempire.complifybook.enums;


import com.abhaempire.complifybook.exception.BusinessExceptionDefintion;

public enum AbhaException implements BusinessExceptionDefintion {
  AUTHORITY_NOT_FOUND(1000, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
          "Authority not found, Please contact us!"),

  INVALID_VERIFICATION_LINK(1001, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
          "Invalid email verification link!"),
  CATEGORY_ALREADY_PRESENT(1002, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
          "Category already present!"),
  MANDATORY_FIELD_MISSING(1003, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
          "Please fill all mandatory fields!"),
  INVALID_ID(1004, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
          "Invalid id : %s !"),
  INVALID_STATUS(1005, ErrorCategory.ERROR, ExceptionTypes.VALIDATION_ERROR,
          "Invalid status!"),
  CATEGORY_NOT_FOUND(1006, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
          "Category not found by id : %s !"),
  SUB_CATEGORY_ALREADY_PRESENT(1007, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
          "Sub-Category already present !"),
  SUB_CATEGORY_NOT_FOUND(1008, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
          "Sub-Category not found !"),
  SERVICE_ALREADY_PRESENT(1009, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
          "Service already present!"),
  SERVICE_NOT_FOUND(1010, ErrorCategory.ERROR, ExceptionTypes.BAD_REQUEST_ERROR,
          "Service not found by id : %s");

  private final int businessErrorCode;
  private final ErrorCategory errorCategory;
  private final ExceptionTypes exceptionType;
  private final String errorMessage;

  AbhaException(int businessErrorCode, ErrorCategory errorCategory,
                 ExceptionTypes exceptionType, String errorMessage) {
    this.businessErrorCode = businessErrorCode;
    this.errorCategory = errorCategory;
    this.exceptionType = exceptionType;
    this.errorMessage = errorMessage;
  }

  @Override
  public int getBusinessErrorCode() {
    return this.businessErrorCode;
  }

  @Override
  public ErrorCategory getErrorCategory() {
    return this.errorCategory;
  }

  @Override
  public ExceptionTypes getExceptionType() {
    return this.exceptionType;
  }

  @Override
  public String getErrorMessage() {
    return this.errorMessage;
  }
}
