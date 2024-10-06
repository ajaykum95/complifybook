package com.abhaempire.complifybook.services;

import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.models.TagService;
import java.util.List;

public interface TagServicesService {
    List<TagService> findByServiceAndStatusNot(Service service, StatusTypeEnum status);
}
