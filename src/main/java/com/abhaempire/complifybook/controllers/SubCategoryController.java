package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.SubCategoryResponse;
import com.abhaempire.complifybook.models.enums.StatusTypeEnum;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/sub-category")
public class SubCategoryController {

    @GetMapping()
    public String allSubCategory(){
        return "private/sub-category";
    }
    @GetMapping("/new")
    public String newSubCategory(){
        return "private/sub-category-new";
    }
    @GetMapping("/fetchAll")
    @ResponseBody
    public List<SubCategoryResponse> fetchAllSubCategory(){
        System.out.println("Inside fetch all sub-category..........");
        return Collections.singletonList(
                SubCategoryResponse.builder()
                        .id(1)
                        .iconName("<i class=\"bi bi-briefcase\"></i>")
                        .categoryName("Business Setup")
                        .subCategoryName("Start Business In India")
                        .status(StatusTypeEnum.ACTIVE)
                        .createdAt(LocalDate.now())
                        .build()
        );
    }
}
