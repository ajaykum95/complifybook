package com.abhaempire.complifybook.services;

import com.abhaempire.complifybook.dtos.CategoryResponse;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Category;
import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);

    List<CategoryResponse> fetchAllCategory();

    Category updateCategory(Category category);

    Category fetchCategoryById(Integer categoryId);

    void deleteCategory(Integer categoryId);

    List<CategoryResponse> fetchAllActiveCategory();

    Category findByIdAndStatus(Integer categoryId, StatusTypeEnum status);
}
