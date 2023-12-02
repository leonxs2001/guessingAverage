package de.thb.groupChat.service;

import de.thb.groupChat.entities.NumberEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NumberEntryCalculationService {
    public float calculateAverage(List<NumberEntry> numberEntryList){
        return 0;
    }

    public float calculateMedian(List<NumberEntry> numberEntryList){
        if(numberEntryList == null || numberEntryList.size() == 0) {
            return 0;
        }

        List<NumberEntry> copiedNumberEntries = new ArrayList<>(numberEntryList);
        Collections.sort(copiedNumberEntries);

        if(copiedNumberEntries.size() % 2 == 0){
            int rightMiddleIndex = copiedNumberEntries.size() / 2;

            NumberEntry rightMiddleNumberEntry = copiedNumberEntries.get(rightMiddleIndex);
            NumberEntry leftMiddleNumberEntry = copiedNumberEntries.get(rightMiddleIndex - 1);

            float middleNumberEntryAverage = (rightMiddleNumberEntry.getNumber() + leftMiddleNumberEntry.getNumber()) / 2.0f;
            return middleNumberEntryAverage;
        }else{
            NumberEntry middleNumberEntry = copiedNumberEntries.get(copiedNumberEntries.size() / 2);
            return middleNumberEntry.getNumber();
        }
    }

    public float getMaxNumber(List<NumberEntry> numberEntryList){
        if(numberEntryList == null || numberEntryList.size() == 0) {
            return 0;
        }

        NumberEntry maxNumberEntry = Collections.max(numberEntryList);
        return maxNumberEntry.getNumber();
    }

    public float getMinNumber(List<NumberEntry> numberEntryList){
        if(numberEntryList == null || numberEntryList.size() == 0) {
            return 0;
        }

        NumberEntry minNumberEntry = Collections.min(numberEntryList);
        return minNumberEntry.getNumber();
    }
}
