package com.abhaempire.complifybook.repositories;

import com.abhaempire.complifybook.dtos.ServiceResponse;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.models.SubCategory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepo extends JpaRepository<Service, Integer> {
    List<Service> findBySubCategoryAndStatus(SubCategory subCategory, StatusTypeEnum status);

    List<Service> findByStatusNot(StatusTypeEnum status);

    Optional<Service> findBySlugAndStatusNot(String slug, StatusTypeEnum status);

    Optional<Service> findByIdAndStatusNot(Integer serviceId, StatusTypeEnum status);

    @Query(value = "SELECT s.* FROM tbl_service s "
            + "LEFT JOIN tbl_tag_service ts ON s.id = ts.tag_service_id "
            + "WHERE ts.service_id = :serviceId AND ts.status = 'ACTIVE'",
            nativeQuery = true)
    List<Service> findTaggedServicesByService(@Param("serviceId") Integer serviceId);

    Optional<Service> findBySlugAndStatusNotAndIdNot(String slug, StatusTypeEnum status, Integer serviceId);

    Service findBySlugAndStatus(String slug, StatusTypeEnum status);
}
