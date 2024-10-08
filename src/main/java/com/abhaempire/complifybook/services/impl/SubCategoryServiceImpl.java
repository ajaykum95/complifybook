package com.abhaempire.complifybook.services.impl;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

import com.abhaempire.complifybook.configs.security.UserDetailsImpl;
import com.abhaempire.complifybook.dtos.SubCategoryResponse;
import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Category;
import com.abhaempire.complifybook.models.SubCategory;
import com.abhaempire.complifybook.repositories.SubCategoryRepo;
import com.abhaempire.complifybook.services.CategoryService;
import com.abhaempire.complifybook.services.SubCategoryService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import com.abhaempire.complifybook.utils.UserDetailsUtil;
import com.abhaempire.complifybook.utils.Utils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepo subCategoryRepo;
    private final CategoryService categoryService;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepo subCategoryRepo, CategoryService categoryService) {
        this.subCategoryRepo = subCategoryRepo;
        this.categoryService = categoryService;
    }

    @Override
    public SubCategory saveSubCategory(SubCategory subCategory) {
        subCategoryRepo.findByCategoryAndSlugAndStatusNot(
                        subCategory.getCategory(), subCategory.getSlug(), StatusTypeEnum.DELETED)
                .ifPresent(sCategory -> {
                    throw buildException(AbhaException.SUB_CATEGORY_ALREADY_PRESENT);
                });

        ObjectMapper.mapToSaveSubCategory(subCategory);
        return subCategoryRepo.save(subCategory);
    }

    @Override
    public List<SubCategoryResponse> fetchAllSubCategory() {
        List<SubCategory> subCategoryList = subCategoryRepo.findByStatusNot(StatusTypeEnum.DELETED);
        return ObjectMapper.mapToSubCategoryResponse(subCategoryList);
    }

    @Override
    public SubCategory fetchSubCategoryById(Integer subCategoryId) {
        return subCategoryRepo.findByIdAndStatusNot(subCategoryId, StatusTypeEnum.DELETED)
                .orElseThrow(() -> buildException(AbhaException.SUB_CATEGORY_NOT_FOUND));
    }

    @Override
    public SubCategory updateSubCategory(SubCategory subCategory) {
        fetchSubCategoryById(subCategory.getId());
        subCategoryRepo.findByCategoryAndSlugAndIdNot(
                        subCategory.getCategory(), subCategory.getSlug(), subCategory.getId())
                .ifPresent(sCat -> {
                    throw buildException(AbhaException.SUB_CATEGORY_ALREADY_PRESENT);
                });
        UserDetailsImpl loggedInUser = UserDetailsUtil.getLoggedInUser();
        subCategory.setUpdatedBy(Utils.getUserId(loggedInUser));
        return subCategoryRepo.save(subCategory);
    }

    @Override
    public void deleteSubCategory(Integer subCategoryId) {
        SubCategory subCategory = fetchSubCategoryById(subCategoryId);
        subCategory.setStatus(StatusTypeEnum.DELETED);
        subCategory.setUpdatedBy(Utils.getUserId(UserDetailsUtil.getLoggedInUser()));
        subCategoryRepo.save(subCategory);
    }

    @Override
    public List<SubCategoryResponse> fetchAllActiveSubCategory() {
        List<SubCategory> subCategoryList = subCategoryRepo.findByStatus(StatusTypeEnum.ACTIVE);
        return ObjectMapper.mapToSubCategoryResponse(subCategoryList);
    }

    @Override
    public List<SubCategoryResponse> fetchAllActiveSubCategoryByCategoryId(Integer categoryId) {
        Category category = categoryService.findByIdAndStatus(categoryId, StatusTypeEnum.ACTIVE);
        List<SubCategory> subCategoryList = subCategoryRepo.findByCategoryAndStatus(
                category, StatusTypeEnum.ACTIVE);
        return ObjectMapper.mapToSubCategoryResponse(subCategoryList);
    }

    @Override
    public SubCategory findByIdAndStatus(Integer subCategoryId, StatusTypeEnum status) {
        return subCategoryRepo.findByIdAndStatus(subCategoryId, status)
                .orElseThrow(() -> buildException(AbhaException.SUB_CATEGORY_NOT_FOUND));
    }
}
