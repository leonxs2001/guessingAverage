package de.thb.guessingaverage.services;

import de.thb.guessingaverage.repositories.NumberEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return numberEntryCalculationService.getMaxNumber(numberEntryRepository.findAll());
    }

}
