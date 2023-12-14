package de.thb.guessingaverage.services;

import de.thb.guessingaverage.entities.NumberEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NumberEntryCalculationService {

    public float calculateAverageNumber(List<NumberEntry> numberEntryList) {
        if (numberEntryList == null || numberEntryList.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (NumberEntry entry : numberEntryList) {
            sum += entry.getNumber();
        }
        return (float) sum / numberEntryList.size();
    }

    public float calculateMedianNumber(List<NumberEntry> numberEntryList) {
        if (numberEntryList == null || numberEntryList.isEmpty()) {
            return 0;
        }

        List<NumberEntry> copiedNumberEntries = new ArrayList<>(numberEntryList);
        Collections.sort(copiedNumberEntries);

        if (copiedNumberEntries.size() % 2 == 0) {
            int rightMiddleIndex = copiedNumberEntries.size() / 2;

            NumberEntry rightMiddleNumberEntry = copiedNumberEntries.get(rightMiddleIndex);
            NumberEntry leftMiddleNumberEntry = copiedNumberEntries.get(rightMiddleIndex - 1);

            // calculate the average of the middle numbers
            return (rightMiddleNumberEntry.getNumber() + leftMiddleNumberEntry.getNumber()) / 2.0f;
        } else {
            NumberEntry middleNumberEntry = copiedNumberEntries.get(copiedNumberEntries.size() / 2);
            return middleNumberEntry.getNumber();
        }
    }

    public float getMaxNumber(List<NumberEntry> numberEntryList) {
        if (numberEntryList == null || numberEntryList.isEmpty()) {
            return 0;
        }

        NumberEntry maxNumberEntry = Collections.max(numberEntryList);
        return maxNumberEntry.getNumber();
    }

    public float getMinNumber(List<NumberEntry> numberEntryList) {
        if (numberEntryList == null || numberEntryList.isEmpty()) {
            return 0;
        }

        NumberEntry minNumberEntry = Collections.min(numberEntryList);
        return minNumberEntry.getNumber();
    }
}
