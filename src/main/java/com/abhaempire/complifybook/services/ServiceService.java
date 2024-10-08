package com.abhaempire.complifybook.services;

import com.abhaempire.complifybook.dtos.PublicServiceResponse;
import com.abhaempire.complifybook.dtos.ServiceDetailResponse;
import com.abhaempire.complifybook.dtos.ServiceResponse;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.models.ServiceDetails;
import java.util.List;

public interface ServiceService {
    List<ServiceResponse> fetchAllActiveServiceBySubcategory(Integer subCategoryId);

    List<ServiceResponse> fetchAllServices();

    Service saveService(Service service, List<Integer> tagService);

    Service fetchServiceById(Integer serviceId);

    List<ServiceResponse> fetchAllTaggedServices(Integer serviceId);

    Service updateService(Service service, List<Integer> tagService);

    Service findBySlug(String slug);

    void deleteService(Integer serviceId);

    List<ServiceDetailResponse> fetchAllServiceDetailsByService(Integer serviceId);

    ServiceDetails saveServiceDetails(ServiceDetails serviceDetails, Integer serviceId);

    ServiceDetails fetchServiceDetailsById(Integer serviceId, Integer detailsId);

    ServiceDetails updateServiceDetails(ServiceDetails serviceDetails, Integer serviceId);

    void deleteServiceDetails(Integer serviceId, Integer detailsId);
}
