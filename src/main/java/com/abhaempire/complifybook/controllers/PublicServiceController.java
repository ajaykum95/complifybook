package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.PublicServiceResponse;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.services.ServiceService;
import com.abhaempire.complifybook.utils.ObjectMapper;
import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service")
public class PublicServiceController {

    private final ServiceService serviceService;

    public PublicServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/{serviceSlug}")
    public String service(@PathVariable String serviceSlug, Model model){
        Service serviceResponse = serviceService.findBySlug(serviceSlug);
        if (Objects.isNull(serviceResponse)){
            return "public/404";
        }
        model.addAttribute("tagServices",
                serviceService.fetchAllTaggedServices(serviceResponse.getId()));
        model.addAttribute("service",
                ObjectMapper.mapToPublicServiceResponse(serviceResponse));
        return "public/service-details";
    }

    @GetMapping("/all")
    public String allService(){
        return "public/services";
    }
}
