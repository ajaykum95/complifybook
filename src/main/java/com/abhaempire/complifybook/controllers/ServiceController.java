package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.ServiceDetailResponse;
import com.abhaempire.complifybook.dtos.ServiceResponse;
import com.abhaempire.complifybook.models.enums.StatusTypeEnum;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/service")
public class ServiceController {

    @GetMapping()
    public String service(){
        return "private/services";
    }
    @GetMapping("/new")
    public String newService(){
        return "private/service-new";
    }
    @GetMapping("/fetchAll")
    @ResponseBody
    public List<ServiceResponse> fetchAllService(){
        System.out.println("fetching service data..");
        return Collections.singletonList(
                ServiceResponse.builder()
                        .id(1)
                        .subCategoryName("Sub-Category -1")
                        .serviceName("NBFC Registration")
                        .rating("4.6/5")
                        .status(StatusTypeEnum.ACTIVE)
                        .createdAt(LocalDate.now())
                        .build()
        );
    }
    @GetMapping("/{serviceId}/details")
    public String serviceDetails(){
        return "private/service-details";
    }
    @GetMapping("/{serviceId}/details/fetchAll")
    @ResponseBody
    public List<ServiceDetailResponse> serviceDetailsAll() {
        System.out.println("fetching service details........");
        return Collections.singletonList(
                ServiceDetailResponse.builder()
                        .id(1)
                        .tabName("Overview")
                        .status(StatusTypeEnum.ACTIVE)
                        .createdAt(LocalDate.now())
                        .build()
        );
    }
    @GetMapping("/{serviceId}/details/new")
    public String newServiceDetails(){
        return "private/service-details-new";
    }
}
