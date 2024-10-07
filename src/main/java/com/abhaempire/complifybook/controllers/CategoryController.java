package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.CategoryResponse;
import com.abhaempire.complifybook.dtos.Message;
import com.abhaempire.complifybook.exception.AbhaBaseRunTimeException;
import com.abhaempire.complifybook.models.Category;
import com.abhaempire.complifybook.services.CategoryService;
import com.abhaempire.complifybook.utils.RequestValidator;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String allCategory(){
        return "private/category";
    }
    @GetMapping("/new")
    public String newCategory(Model model){
        model.addAttribute("category", new Category());
        return "private/category-new";
    }
    @GetMapping("/delete/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId, Model model){
        try {
            RequestValidator.validateId(categoryId);
            categoryService.deleteCategory(categoryId);
            return "redirect:/api/v1/category";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "public/404";
        }
    }
    @GetMapping("/edit/{categoryId}")
    public String editCategory(@PathVariable Integer categoryId, Model model){
        try {
            RequestValidator.validateId(categoryId);
            model.addAttribute("category",
                    categoryService.fetchCategoryById(categoryId));
            return "private/category-edit";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "public/404";
        }
    }
    @PostMapping("/new")
    public String addCategory(@Valid @ModelAttribute Category category,
                              BindingResult result, Model model){
        try{
            RequestValidator.validateRequest(result);
            categoryService.saveCategory(category);
            return "redirect:/api/v1/category";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "private/category-new";
        }
    }
    @PostMapping("/update")
    public String updateCategory(@Valid @ModelAttribute Category category,
                              BindingResult result, Model model){
        try{
            RequestValidator.validateUpdateCategory(category, result);
            categoryService.updateCategory(category);
            return "redirect:/api/v1/category";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "private/category-edit";
        }
    }
    @GetMapping("/fetchAll")
    @ResponseBody
    public List<CategoryResponse> fetchAllCategory(){
        return categoryService.fetchAllCategory();
    }
}
