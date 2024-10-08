package com.abhaempire.complifybook.utils;

import com.abhaempire.complifybook.dtos.CallBackEnquiry;
import com.abhaempire.complifybook.dtos.SubscriptionRequest;
import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.models.Category;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.models.ServiceDetails;
import com.abhaempire.complifybook.models.SubCategory;
import com.abhaempire.complifybook.utils.validation.EmailValidator;
import com.abhaempire.complifybook.utils.validation.MobileValidator;
import jakarta.validation.Valid;
import java.util.Objects;
import org.springframework.validation.BindingResult;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

public class RequestValidator {

    public static void validateUpdateCategory(Category category, BindingResult result) {
        validateRequest(result);
        if (Objects.isNull(category.getId()) || category.getId() == 0){
            throw buildException(AbhaException.INVALID_ID, category.getId());
        }
        if (Objects.isNull(category.getStatus())){
            throw buildException(AbhaException.INVALID_STATUS);
        }
    }

    public static void validateId(Integer id) {
        if (Objects.isNull(id) || id == 0){
            throw buildException(AbhaException.INVALID_ID, id);
        }
    }

    public static void validateUpdateSubCategory(SubCategory subCategory, BindingResult result) {
        validateRequest(result);
        if (Objects.isNull(subCategory.getId()) || subCategory.getId() == 0){
            throw buildException(AbhaException.INVALID_ID, subCategory.getId());
        }
        if (Objects.isNull(subCategory.getStatus())){
            throw buildException(AbhaException.INVALID_STATUS);
        }
    }

    public static void validateUpdateService(BindingResult result, Service serviceRequest) {
        validateRequest(result);
        validateId(serviceRequest.getId());
        if (Objects.isNull(serviceRequest.getStatus())){
            throw buildException(AbhaException.INVALID_STATUS);
        }
    }

    public static void validateRequest(BindingResult result) {
        if (result.hasErrors()){
            throw buildException(AbhaException.MANDATORY_FIELD_MISSING);
        }
    }

    public static void validateSaveServiceDetails(BindingResult result, Integer serviceId) {
        validateRequest(result);
        validateId(serviceId);
    }

  public static void validateSubscriptionRequest(SubscriptionRequest subscriptionRequest) {
        if (Objects.isNull(subscriptionRequest)){
            throw buildException(AbhaException.SUBSCRIPTION_REQUEST_MISSING);
        }
        if (!EmailValidator.isValidEmail(subscriptionRequest.getEmail())){
            throw buildException(AbhaException.INVALID_EMAIL);
        }
  }

    public static void validateIds(Integer ...ids) {
        for (Integer id : ids){
            validateId(id);
        }
    }

    public static void validateUpdateServiceDetails(
            BindingResult result, ServiceDetails serviceDetails, Integer serviceId) {
        validateRequest(result);
        validateId(serviceDetails.getId());
        validateId(serviceId);
        if (Objects.isNull(serviceDetails.getStatus())){
            throw buildException(AbhaException.INVALID_STATUS);
        }
    }

    public static void validateCallBackRequest(CallBackEnquiry callBackEnquiry) {
        if (Objects.isNull(callBackEnquiry)){
            throw buildException(AbhaException.CALL_BACK_ENQUIRY_REQUEST_MISSING);
        }
        if (!MobileValidator.isValidMobile(callBackEnquiry.getMobile())){
            throw buildException(AbhaException.INVALID_MOBILE_NUMBER);
        }
        if (Objects.isNull(callBackEnquiry.getOtp())){
            throw buildException(AbhaException.OTP_MISSING);
        }
        if (Objects.nonNull(callBackEnquiry.getEmail())
                && !EmailValidator.isValidEmail(callBackEnquiry.getEmail())){
            throw buildException(AbhaException.INVALID_EMAIL);
        }
    }
}
