package com.abhaempire.complifybook.utils;

import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.models.Category;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.models.SubCategory;
import jakarta.validation.Valid;
import java.util.Objects;
import org.springframework.validation.BindingResult;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

public class RequestValidator {
    public static void validateCreateCategory(BindingResult result) {
        if (result.hasErrors()){
            throw buildException(AbhaException.MANDATORY_FIELD_MISSING);
        }
    }

    public static void validateUpdateCategory(Category category, BindingResult result) {
        if (result.hasErrors()){
            throw buildException(AbhaException.MANDATORY_FIELD_MISSING);
        }
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

    public static void validateCreateSubCategory(BindingResult result) {
        if (result.hasErrors()){
            throw buildException(AbhaException.MANDATORY_FIELD_MISSING);
        }
    }

    public static void validateUpdateSubCategory(SubCategory subCategory, BindingResult result) {
        if (result.hasErrors()){
            throw buildException(AbhaException.MANDATORY_FIELD_MISSING);
        }
        if (Objects.isNull(subCategory.getId()) || subCategory.getId() == 0){
            throw buildException(AbhaException.INVALID_ID, subCategory.getId());
        }
        if (Objects.isNull(subCategory.getStatus())){
            throw buildException(AbhaException.INVALID_STATUS);
        }
    }

    public static void validateSaveService(BindingResult result) {
        if (result.hasErrors()){
            throw buildException(AbhaException.MANDATORY_FIELD_MISSING);
        }
    }

    public static void validateUpdateService(BindingResult result, Service serviceRequest) {
        if (result.hasErrors()){
            throw buildException(AbhaException.MANDATORY_FIELD_MISSING);
        }
        validateId(serviceRequest.getId());
        if (Objects.isNull(serviceRequest.getStatus())){
            throw buildException(AbhaException.INVALID_STATUS);
        }
    }
}
