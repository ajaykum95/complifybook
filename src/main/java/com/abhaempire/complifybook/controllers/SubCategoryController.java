package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
