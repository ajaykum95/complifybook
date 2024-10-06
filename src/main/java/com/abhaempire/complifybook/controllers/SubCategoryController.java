package com.abhaempire.complifybook.controllers;

import com.abhaempire.complifybook.dtos.Message;
import com.abhaempire.complifybook.dtos.SubCategoryResponse;
import com.abhaempire.complifybook.exception.AbhaBaseRunTimeException;
import com.abhaempire.complifybook.models.SubCategory;
import com.abhaempire.complifybook.services.CategoryService;
import com.abhaempire.complifybook.services.SubCategoryService;
import com.abhaempire.complifybook.utils.RequestValidator;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/sub-category")
public class SubCategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(CategoryService categoryService, SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }


    @GetMapping()
    public String allSubCategory() {
        return "private/sub-category";
    }

    @GetMapping("/new")
    public String newSubCategory(Model model) {
        model.addAttribute("categoryList",
                categoryService.fetchAllActiveCategory());
        model.addAttribute("subCategory", new SubCategory());
        return "private/sub-category-new";
    }

    @GetMapping("/delete/{subCategoryId}")
    public String deleteSubCategory(@PathVariable Integer subCategoryId, Model model){
        try {
            RequestValidator.validateId(subCategoryId);
            subCategoryService.deleteSubCategory(subCategoryId);
            return "redirect:/api/v1/sub-category";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "public/404";
        }
    }

    @GetMapping("/edit/{subCategoryId}")
    public String editSubCategory(@PathVariable Integer subCategoryId, Model model) {
        try {
            RequestValidator.validateId(subCategoryId);
            model.addAttribute("subCategory",
                    subCategoryService.fetchSubCategoryById(subCategoryId));
            model.addAttribute("categoryList",
                    categoryService.fetchAllActiveCategory());
            return "private/sub-category-edit";
        } catch (AbhaBaseRunTimeException e) {
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "public/404";
        }
    }

    @PostMapping("/update")
    public String updateSubCategory(@Valid @ModelAttribute SubCategory subCategory,
                                 BindingResult result, Model model){
        try{
            RequestValidator.validateUpdateSubCategory(subCategory, result);
            subCategoryService.updateSubCategory(subCategory);
            return "redirect:/api/v1/sub-category";
        }catch (AbhaBaseRunTimeException e){
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "private/sub-category-edit";
        }
    }

    @PostMapping("/new")
    public String addSubCategory(@Valid @ModelAttribute SubCategory subCategory,
                                 BindingResult result, Model model) {
        try {
            RequestValidator.validateCreateSubCategory(result);
            subCategoryService.saveSubCategory(subCategory);
            return "redirect:/api/v1/sub-category";
        } catch (AbhaBaseRunTimeException e) {
            model.addAttribute("formMessage",
                    new Message("alert-danger", e.getMessage()));
            return "private/sub-category-new";
        }
    }

    @GetMapping("/fetchAll")
    @ResponseBody
    public List<SubCategoryResponse> fetchAllSubCategory() {
        return subCategoryService.fetchAllSubCategory();
    }
}
