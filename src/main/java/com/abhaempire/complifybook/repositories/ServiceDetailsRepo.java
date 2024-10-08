package com.abhaempire.complifybook.repositories;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.models.ServiceDetails;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceDetailsRepo extends JpaRepository<ServiceDetails, Integer> {
    List<ServiceDetails> findByServiceAndStatusNot(Service service, StatusTypeEnum status);

    Optional<ServiceDetails> findByServiceAndTabNameAndStatusNot(
            Service service, String tabName, StatusTypeEnum status);

    Optional<ServiceDetails> findByServiceAndIdAndStatusNot(Service service, Integer detailsId, StatusTypeEnum status);

    Optional<ServiceDetails> findByServiceAndTabNameAndStatusNotAndIdNot(
            Service service, String tabName, StatusTypeEnum status, Integer detailsId);
}
