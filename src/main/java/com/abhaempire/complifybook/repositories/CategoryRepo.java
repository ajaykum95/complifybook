package com.abhaempire.complifybook.repositories;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findBySlugAndStatusNot(String slug, StatusTypeEnum status);

    List<Category> findByStatusNot(StatusTypeEnum status);

    Optional<Category> findByIdAndStatusNot(Integer categoryId, StatusTypeEnum statusTypeEnum);

    Optional<Category> findBySlugAndStatusNotAndIdNot(String slug, StatusTypeEnum status, Integer categoryId);

    List<Category> findByStatus(StatusTypeEnum statusTypeEnum);

    Optional<Category> findByIdAndStatus(Integer categoryId, StatusTypeEnum status);
}
