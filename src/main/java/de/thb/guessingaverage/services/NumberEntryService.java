package de.thb.guessingaverage.services;

import de.thb.guessingaverage.controller.form.NumberEntryFormModel;
import de.thb.guessingaverage.repositories.NumberEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import de.thb.guessingaverage.entities.NumberEntry;
import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class NumberEntryService {
    private final NumberEntryCalculationService numberEntryCalculationService;
    private final NumberEntryRepository numberEntryRepository;

    public float calculateTotalAverageNumber(){
        return numberEntryCalculationService.calculateAverageNumber(numberEntryRepository.findAll());
    }

    public float calculateTotalMedianNumber(){
        return numberEntryCalculationService.calculateMedianNumber(numberEntryRepository.findAll());
    }

    public float getTotalMaxNumber(){
        return numberEntryCalculationService.getMaxNumber(numberEntryRepository.findAll());
    }

    public float getTotalMinNumber(){
        return numberEntryCalculationService.getMinNumber(numberEntryRepository.findAll());
    }

    public void addNumber(NumberEntryFormModel form){
        numberEntryRepository.save(new NumberEntry(LocalDateTime.now(), form.getNumber()));
    }
}
