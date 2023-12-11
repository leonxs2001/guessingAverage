package de.thb.guessingaverage.controller;

import de.thb.guessingaverage.services.NumberEntryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class NumberController {
    private final NumberEntryService numberEntryService;

    @GetMapping("/")
    public String controlNumber(Model model){
        model.addAttribute("minNumber", numberEntryService.getTotalMinNumber());
        model.addAttribute("maxNumber", numberEntryService.getTotalMaxNumber());
        model.addAttribute("medianNumber", numberEntryService.calculateTotalMedianNumber());

        return "guessing-average.html";
    }
}
