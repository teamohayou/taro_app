package com.taro.tarocard.card;
import com.taro.tarocard.category.CategoryService;
import com.taro.tarocard.category.Category;
import com.taro.tarocard.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view/category")
public class CardController {
    private final CardService cardService;
    private final CategoryService categoryService;

    @GetMapping("/연애운")
    public String getCardById (Model model) {
        List<Category> categories = categoryService.findCategoriesByName("연애운");
        model.addAttribute("categories", categories);
        return "cardchoise_page";
    }


}
