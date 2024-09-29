package com.abhaempire.complifybook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
