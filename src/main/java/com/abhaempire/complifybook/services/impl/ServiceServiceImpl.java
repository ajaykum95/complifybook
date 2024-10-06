package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.dtos.PublicServiceResponse;
import com.abhaempire.complifybook.dtos.ServiceResponse;
import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.SubCategory;
import com.abhaempire.complifybook.models.TagService;
import com.abhaempire.complifybook.repositories.ServiceRepo;
import com.abhaempire.complifybook.services.ServiceService;
import com.abhaempire.complifybook.services.SubCategoryService;
import com.abhaempire.complifybook.services.TagServicesService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepo serviceRepo;
    private final SubCategoryService subCategoryService;
    private final TagServicesService tagServicesService;

    @Autowired
    public ServiceServiceImpl(ServiceRepo serviceRepo, SubCategoryService subCategoryService,
                              TagServicesService tagServicesService) {
        this.serviceRepo = serviceRepo;
        this.subCategoryService = subCategoryService;
        this.tagServicesService = tagServicesService;
    }

    @Override
    public List<ServiceResponse> fetchAllActiveServiceBySubcategory(Integer subCategoryId) {
        SubCategory subCategory = subCategoryService.findByIdAndStatus(
                subCategoryId, StatusTypeEnum.ACTIVE);
        List<com.abhaempire.complifybook.models.Service> services =
                serviceRepo.findBySubCategoryAndStatus(subCategory, StatusTypeEnum.ACTIVE);
        return ObjectMapper.mapToServiceResponse(services);
    }

    @Override
    public List<ServiceResponse> fetchAllServices() {
        List<com.abhaempire.complifybook.models.Service> serviceList =
                serviceRepo.findByStatusNot(StatusTypeEnum.DELETED);
        return ObjectMapper.mapToServiceResponse(serviceList);
    }

    @Override
    public com.abhaempire.complifybook.models.Service saveService(
            com.abhaempire.complifybook.models.Service service, List<Integer> tagService) {
        serviceRepo.findBySlugAndStatusNot(service.getSlug(), StatusTypeEnum.DELETED)
                .ifPresent(s -> {
                    throw buildException(AbhaException.SERVICE_ALREADY_PRESENT);
                });
        ObjectMapper.mapToSaveService(service, tagService);
        return serviceRepo.save(service);
    }

    @Override
    public com.abhaempire.complifybook.models.Service fetchServiceById(Integer serviceId) {
        return serviceRepo.findByIdAndStatusNot(serviceId, StatusTypeEnum.DELETED)
                .orElseThrow(() -> buildException(AbhaException.SERVICE_NOT_FOUND, serviceId));
    }

    @Override
    public List<ServiceResponse> fetchAllTaggedServices(Integer serviceId) {
        List<com.abhaempire.complifybook.models.Service> taggedServices =
                serviceRepo.findTaggedServicesByService(serviceId);
        return ObjectMapper.mapToServiceResponse(taggedServices);
    }

    @Override
    public com.abhaempire.complifybook.models.Service updateService(
            com.abhaempire.complifybook.models.Service service, List<Integer> tagService) {
        serviceRepo.findBySlugAndStatusNotAndIdNot(
                        service.getSlug(), StatusTypeEnum.DELETED, service.getId())
                .ifPresent(s -> {
                    throw buildException(AbhaException.SERVICE_ALREADY_PRESENT);
                });
        List<TagService> tagServiceList = tagServicesService.findByServiceAndStatusNot(
                service, StatusTypeEnum.DELETED);
        ObjectMapper.mapToUpdateService(service, tagServiceList, tagService);
        return serviceRepo.save(service);
    }

    @Override
    public com.abhaempire.complifybook.models.Service findBySlug(String slug) {
        return serviceRepo.findBySlugAndStatus(slug, StatusTypeEnum.ACTIVE);
    }
}
