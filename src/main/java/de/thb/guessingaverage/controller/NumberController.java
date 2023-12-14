package de.thb.guessingaverage.controller;

import de.thb.guessingaverage.controller.form.NumberEntryFormModel;
import de.thb.guessingaverage.services.NumberEntryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/")
    public String addNumber(@ModelAttribute NumberEntryFormModel form, Model model){
        numberEntryService.addNumber(form);

        model.addAttribute("minNumber", numberEntryService.getTotalMinNumber());
        model.addAttribute("maxNumber", numberEntryService.getTotalMaxNumber());
        model.addAttribute("medianNumber", numberEntryService.calculateTotalMedianNumber());
        model.addAttribute("averageNumber", numberEntryService.calculateTotalAverageNumber());
        model.addAttribute("number", form.getNumber());

        return "guessing-average.html";
    }

}
