package de.thb.groupChat.service;

import de.thb.groupChat.entities.NumberEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberEntryCalculationService {
    public float calculateAverage(List<NumberEntry> numberEntryList){
        return 0;
    }

    public float calculateMedian(List<NumberEntry> numberEntryList){return 0;}

    public float getMaxNumber(List<NumberEntry> numberEntryList){return 0;}

    public float getMinNumber(List<NumberEntry> numberEntryList){return 0;}
}
