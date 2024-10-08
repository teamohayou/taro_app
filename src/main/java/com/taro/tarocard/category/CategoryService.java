package com.taro.tarocard.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory () {
        return this.categoryRepository.findAll();
    }

    public List<Category> findCategoriesByName(String categoryname) {
        return categoryRepository.findByCategoryname(categoryname);
    }

}
