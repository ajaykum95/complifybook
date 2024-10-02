package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.CategoryResponse;
import com.abhaempire.complifybook.models.enums.StatusTypeEnum;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {

    @GetMapping()
    public String allCategory(){
        return "private/category";
    }
    @GetMapping("/new")
    public String newCategory(){
        return "private/category-new";
    }
    @GetMapping("/fetchAll")
    @ResponseBody
    public List<CategoryResponse> fetchAllCategory(){
        System.out.println("Inside fetch all category..........");
        return Collections.singletonList(
                CategoryResponse.builder()
                        .id(1)
                        .name("Business Setup")
                        .status(StatusTypeEnum.ACTIVE)
                        .createdAt(LocalDate.now())
                        .build()
        );
    }
}
