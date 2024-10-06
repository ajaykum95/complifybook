package com.abhaempire.complifybook.repositories;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.models.TagService;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagServiceRepo extends JpaRepository<TagService, Integer> {
    List<TagService> findByServiceAndStatusNot(Service service, StatusTypeEnum status);
}
