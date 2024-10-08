package com.taro.tarocard.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    public final CategoryService categoryService;

    @GetMapping("/view/categories")
    public String showCategories (Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "view_page";
    }
}
