package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.Message;
import com.abhaempire.complifybook.dtos.ServiceDetailResponse;
import com.abhaempire.complifybook.dtos.ServiceResponse;
import com.abhaempire.complifybook.dtos.SubCategoryResponse;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.exception.AbhaBaseRunTimeException;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.services.CategoryService;
import com.abhaempire.complifybook.services.ServiceService;
import com.abhaempire.complifybook.services.SubCategoryService;
import com.abhaempire.complifybook.utils.RequestValidator;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/service")
public class ServiceController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final ServiceService serviceService;

    @Autowired
    public ServiceController(
            CategoryService categoryService, SubCategoryService subCategoryService,
            ServiceService serviceService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.serviceService = serviceService;
    }

    @GetMapping()
    public String service(){
        return "private/services";
    }
    @GetMapping("/new")
    public String newService(Model model){
        model.addAttribute("categoryList",
                categoryService.fetchAllActiveCategory());
        model.addAttribute("service", new Service());
        model.addAttribute("subCategoryList",
                subCategoryService.fetchAllActiveSubCategory());
        return "private/service-new";
    }
    @GetMapping("/edit/{serviceId}")
    public String editService(@PathVariable Integer serviceId, Model model){
        try {
            RequestValidator.validateId(serviceId);
            model.addAttribute("categoryList",
                    categoryService.fetchAllActiveCategory());
            Service service = serviceService.fetchServiceById(serviceId);
            model.addAttribute("service", service);
            model.addAttribute("subCategoryList",
                    subCategoryService.fetchAllActiveSubCategory());
            model.addAttribute("tagServices",
                    serviceService.fetchAllTaggedServices(service.getId()));
            return "private/service-edit";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "public/404";
        }
    }
    @PostMapping("/new")
    public String saveService(
            @Valid @ModelAttribute Service service, BindingResult result,
            @RequestParam(required = false) List<Integer> tagService, Model model){
        try{
            RequestValidator.validateSaveService(result);
            serviceService.saveService(service, tagService);
            return "redirect:/api/v1/service";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "private/service-new";
        }
    }
    @PostMapping("/update")
    public String updateService(
            @Valid @ModelAttribute Service service, BindingResult result,
            @RequestParam(required = false) List<Integer> tagService, Model model){
        try{
            RequestValidator.validateUpdateService(result, service);
            serviceService.updateService(service, tagService);
            return "redirect:/api/v1/service";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "redirect:/api/v1/service/edit/" + service.getId();
        }
    }
    @GetMapping("/subCategories/{categoryId}")
    @ResponseBody
    public List<SubCategoryResponse> fetchSubCategoryByCategory(@PathVariable Integer categoryId){
        RequestValidator.validateId(categoryId);
        return subCategoryService.fetchAllActiveSubCategoryByCategoryId(categoryId);
    }
    @GetMapping("/tagService/{subCategoryId}")
    @ResponseBody
    public List<ServiceResponse> fetchServicesBySubCategory(@PathVariable Integer subCategoryId){
        RequestValidator.validateId(subCategoryId);
        return serviceService.fetchAllActiveServiceBySubcategory(subCategoryId);
    }
    @GetMapping("/fetchAll")
    @ResponseBody
    public List<ServiceResponse> fetchAllService(){
        return serviceService.fetchAllServices();
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
