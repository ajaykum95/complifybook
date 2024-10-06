package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.TagService;
import com.abhaempire.complifybook.repositories.TagServiceRepo;
import com.abhaempire.complifybook.services.TagServicesService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TagServicesServiceImpl implements TagServicesService {

    private final TagServiceRepo tagServiceRepo;

    public TagServicesServiceImpl(TagServiceRepo tagServiceRepo) {
        this.tagServiceRepo = tagServiceRepo;
    }

    @Override
    public List<TagService> findByServiceAndStatusNot(
            com.abhaempire.complifybook.models.Service service, StatusTypeEnum status) {
        return tagServiceRepo.findByServiceAndStatusNot(service, status);
    }
}
