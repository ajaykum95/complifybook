package com.abhaempire.complifybook.repositories;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Category;
import com.abhaempire.complifybook.models.SubCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer> {
    List<SubCategory> findByStatusNot(StatusTypeEnum status);

    Optional<SubCategory> findByCategoryAndSlugAndStatusNot(Category category, String slug, StatusTypeEnum status);

    Optional<SubCategory> findByIdAndStatusNot(Integer subCategoryId, StatusTypeEnum status);

    Optional<SubCategory> findByCategoryAndSlugAndIdNot(Category category, String slug, Integer subCategoryId);

    List<SubCategory> findByStatus(StatusTypeEnum statusTypeEnum);

    List<SubCategory> findByCategoryAndStatus(Category category, StatusTypeEnum status);

    Optional<SubCategory> findByIdAndStatus(Integer subCategoryId, StatusTypeEnum status);
}
