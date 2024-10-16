package com.taro.tarocard.card;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CardController {
    @Autowired
    private final CardService cardService;
    @Autowired
    private final RomanticCardRepository romanticCardRepository;

    @GetMapping
    public String getCategories(Model model) {
        List<Category> categories = cardService.getAllCategories();
        model.addAttribute("categories", categories);
        return "view_page"; // 변경할 HTML 템플릿 이름
    }


    @GetMapping("/romantic")
    public String romanticTaro (Model model) {
        List<RomanticCard> rcCardId = cardService.getRandomRomanticCard();
        model.addAttribute("rcCardId", rcCardId);
        return "cardchoise_page";
    }

    @GetMapping("/rccard-result/{rcCardId}")
    public String getCardResult(@PathVariable Integer rcCardId, Model model){
        Optional<RomanticCard> rcCard = romanticCardRepository.findAllById(rcCardId);
        if(rcCard.isPresent()){
            model.addAttribute("rcCard", rcCard.get());
            return "cardresult_page";
        }else{
            return "redirect:/main";
        }
    }



}
