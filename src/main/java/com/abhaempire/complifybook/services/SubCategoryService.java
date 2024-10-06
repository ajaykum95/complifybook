package com.abhaempire.complifybook.services;

import com.abhaempire.complifybook.dtos.SubCategoryResponse;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.SubCategory;
import java.util.List;

public interface SubCategoryService {
    SubCategory saveSubCategory(SubCategory subCategory);

    List<SubCategoryResponse> fetchAllSubCategory();

    SubCategory fetchSubCategoryById(Integer subCategoryId);

    SubCategory updateSubCategory(SubCategory subCategory);

    void deleteSubCategory(Integer subCategoryId);

    List<SubCategoryResponse> fetchAllActiveSubCategory();

    List<SubCategoryResponse> fetchAllActiveSubCategoryByCategoryId(Integer categoryId);

    SubCategory findByIdAndStatus(Integer subCategoryId, StatusTypeEnum status);
}
