package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.configs.security.UserDetailsImpl;
import com.abhaempire.complifybook.dtos.CategoryResponse;
import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Category;
import com.abhaempire.complifybook.repositories.CategoryRepo;
import com.abhaempire.complifybook.services.CategoryService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import com.abhaempire.complifybook.utils.UserDetailsUtil;
import com.abhaempire.complifybook.utils.Utils;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category saveCategory(Category category) {
        categoryRepo.findBySlugAndStatusNot(category.getSlug(), StatusTypeEnum.DELETED)
                .ifPresent(cat -> {throw buildException(AbhaException.CATEGORY_ALREADY_PRESENT);});
        ObjectMapper.mapToSaveCategory(category);
        return categoryRepo.save(category);
    }

    @Override
    public List<CategoryResponse> fetchAllCategory() {
        List<Category> categories = categoryRepo.findByStatusNot(StatusTypeEnum.DELETED);
        return ObjectMapper.mapToCategoryResponse(categories);
    }

    @Override
    public Category updateCategory(Category category) {
        fetchCategoryById(category.getId());
        categoryRepo.findBySlugAndStatusNotAndIdNot(
                category.getSlug(), StatusTypeEnum.DELETED, category.getId())
                .ifPresent(cat -> {throw buildException(AbhaException.CATEGORY_ALREADY_PRESENT);});
        UserDetailsImpl loggedInUser = UserDetailsUtil.getLoggedInUser();
        category.setUpdatedBy(Utils.getUserId(loggedInUser));
        return categoryRepo.save(category);
    }

    @Override
    public Category fetchCategoryById(Integer categoryId) {
        return categoryRepo.findByIdAndStatusNot(categoryId, StatusTypeEnum.DELETED)
                .orElseThrow(() -> buildException(AbhaException.CATEGORY_NOT_FOUND, categoryId));
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = fetchCategoryById(categoryId);
        if(!StatusTypeEnum.DELETED.equals(category.getStatus())){
            UserDetailsImpl loggedInUser = UserDetailsUtil.getLoggedInUser();
            category.setStatus(StatusTypeEnum.DELETED);
            category.setUpdatedBy(Utils.getUserId(loggedInUser));
            categoryRepo.save(category);
        }
    }

    @Override
    public List<CategoryResponse> fetchAllActiveCategory() {
        List<Category> categories = categoryRepo.findByStatus(StatusTypeEnum.ACTIVE);
        return ObjectMapper.mapToCategoryResponse(categories);
    }

    @Override
    public Category findByIdAndStatus(Integer categoryId, StatusTypeEnum status) {
        return categoryRepo.findByIdAndStatus(categoryId, status)
                .orElseThrow(() -> buildException(AbhaException.CATEGORY_NOT_FOUND));
    }
}
