package com.abhaempire.complifybook.services.impl;

import com.abhaempire.complifybook.dtos.PublicServiceResponse;
import com.abhaempire.complifybook.dtos.ServiceDetailResponse;
import com.abhaempire.complifybook.dtos.ServiceResponse;
import com.abhaempire.complifybook.enums.AbhaException;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.ServiceDetails;
import com.abhaempire.complifybook.models.SubCategory;
import com.abhaempire.complifybook.models.TagService;
import com.abhaempire.complifybook.repositories.ServiceDetailsRepo;
import com.abhaempire.complifybook.repositories.ServiceRepo;
import com.abhaempire.complifybook.services.ServiceService;
import com.abhaempire.complifybook.services.SubCategoryService;
import com.abhaempire.complifybook.services.TagServicesService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import com.abhaempire.complifybook.utils.UserDetailsUtil;
import com.abhaempire.complifybook.utils.Utils;
import java.util.List;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.abhaempire.complifybook.utils.ExceptionUtil.buildException;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepo serviceRepo;
    private final SubCategoryService subCategoryService;
    private final TagServicesService tagServicesService;
    private final ServiceDetailsRepo serviceDetailsRepo;

    @Autowired
    public ServiceServiceImpl(ServiceRepo serviceRepo, SubCategoryService subCategoryService,
                              TagServicesService tagServicesService,
                              ServiceDetailsRepo serviceDetailsRepo) {
        this.serviceRepo = serviceRepo;
        this.subCategoryService = subCategoryService;
        this.tagServicesService = tagServicesService;
        this.serviceDetailsRepo = serviceDetailsRepo;
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

    @Override
    public void deleteService(Integer serviceId) {
        com.abhaempire.complifybook.models.Service service = fetchServiceById(serviceId);
        service.setStatus(StatusTypeEnum.DELETED);
        service.setUpdatedBy(Utils.getUserId(UserDetailsUtil.getLoggedInUser()));
        serviceRepo.save(service);
    }

    @Override
    public List<ServiceDetailResponse> fetchAllServiceDetailsByService(Integer serviceId) {
        com.abhaempire.complifybook.models.Service service = fetchServiceById(serviceId);
        List<ServiceDetails> serviceDetailsList =
                serviceDetailsRepo.findByServiceAndStatusNot(service, StatusTypeEnum.DELETED);
        return ObjectMapper.mapToServiceDetailsResponse(serviceDetailsList);
    }

    @Override
    public ServiceDetails saveServiceDetails(ServiceDetails serviceDetails, Integer serviceId) {
        com.abhaempire.complifybook.models.Service service = fetchServiceById(serviceId);
        serviceDetailsRepo.findByServiceAndTabNameAndStatusNot(
                        service, serviceDetails.getTabName(), StatusTypeEnum.DELETED)
                .ifPresent(sd -> {
                    throw buildException(AbhaException.SERVICE_DETAILS_ALREADY_PRESENT);
                });
        ObjectMapper.mapToSaveServiceDetails(serviceDetails, service);
        return serviceDetailsRepo.save(serviceDetails);
    }

    @Override
    public ServiceDetails fetchServiceDetailsById(Integer serviceId, Integer detailsId) {
        com.abhaempire.complifybook.models.Service service = fetchServiceById(serviceId);
        return fetchServiceDetailsByServiceAndId(service, detailsId);
    }

    @Override
    public ServiceDetails updateServiceDetails(ServiceDetails serviceDetails, Integer serviceId) {
        com.abhaempire.complifybook.models.Service service = fetchServiceById(serviceId);
        serviceDetailsRepo.findByServiceAndTabNameAndStatusNotAndIdNot(
                        service, serviceDetails.getTabName(), StatusTypeEnum.DELETED, serviceDetails.getId())
                .ifPresent(sd -> {
                    throw buildException(AbhaException.SERVICE_DETAILS_TAB_NAME_PRESENT);
                });
        ObjectMapper.mapToUpdateServiceDetails(serviceDetails, service);
        return serviceDetailsRepo.save(serviceDetails);
    }

    @Override
    public void deleteServiceDetails(Integer serviceId, Integer detailsId) {
        com.abhaempire.complifybook.models.Service service = fetchServiceById(serviceId);
        ServiceDetails serviceDetails = fetchServiceDetailsByServiceAndId(service, detailsId);
        serviceDetails.setUpdatedBy(Utils.getUserId(UserDetailsUtil.getLoggedInUser()));
        serviceDetails.setStatus(StatusTypeEnum.DELETED);
        serviceDetailsRepo.save(serviceDetails);
    }

    private ServiceDetails fetchServiceDetailsByServiceAndId(
            com.abhaempire.complifybook.models.Service service, Integer detailsId) {
        return serviceDetailsRepo.findByServiceAndIdAndStatusNot(service, detailsId, StatusTypeEnum.DELETED)
                .orElseThrow(() -> buildException(AbhaException.SERVICE_DETAILS_NOT_FOUND));
    }
}
