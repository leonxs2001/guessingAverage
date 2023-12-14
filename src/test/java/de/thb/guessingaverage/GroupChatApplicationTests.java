package de.thb.guessingaverage;

import de.thb.guessingaverage.controller.form.NumberEntryFormModel;
import de.thb.guessingaverage.services.NumberEntryCalculationService;
import de.thb.guessingaverage.services.NumberEntryService;
import de.thb.guessingaverage.repositories.NumberEntryRepository;
import de.thb.guessingaverage.entities.NumberEntry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;





@SpringBootTest
class NumberEntryCalculationServiceTests {

    @Autowired
    private NumberEntryCalculationService numberEntryCalculationService;

    @Test
    void test_average_calculation_with_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();
        numberEntries.add(new NumberEntry(null, 1));
        numberEntries.add(new NumberEntry(null, 3));
        numberEntries.add(new NumberEntry(null, 10));

        Assertions.assertEquals(7, numberEntryCalculationService.calculateAverageNumber(numberEntries), "Given average is not the real average.");
    }

    @Test
    void test_average_calculation_without_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();
        Assertions.assertEquals(0, numberEntryCalculationService.calculateAverageNumber(numberEntries), "Given average should be 0 in case of no given entries.");
    }

    @Test
    void test_average_calculation_with_null() {
        Assertions.assertEquals(0, numberEntryCalculationService.calculateAverageNumber(null), "Given average should be 0 in case of null as given entries.");
    }

    @Test
    void test_median_calculation_with_odd_number_of_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();
        numberEntries.add(new NumberEntry(null, 1));
        numberEntries.add(new NumberEntry(null, 3));
        numberEntries.add(new NumberEntry(null, 10));

        Assertions.assertEquals(3, numberEntryCalculationService.calculateMedianNumber(numberEntries), "Given median is not the real median for an odd number of entries.");
    }

    @Test
    void test_median_calculation_with_even_number_of_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();
        numberEntries.add(new NumberEntry(null, 1));
        numberEntries.add(new NumberEntry(null, 3));
        numberEntries.add(new NumberEntry(null, 4));
        numberEntries.add(new NumberEntry(null, 10));

        Assertions.assertEquals(3.5, numberEntryCalculationService.calculateMedianNumber(numberEntries), "Given median is not the real median for an even number of entries. Should be the average of the 2 middle numbers.");
    }

    @Test
    void test_median_calculation_with_no_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();
        Assertions.assertEquals(0, numberEntryCalculationService.calculateMedianNumber(numberEntries), "Given median should be 0 in case of no given entries.");
    }

    @Test
    void test_median_calculation_with_null() {
        Assertions.assertEquals(0, numberEntryCalculationService.calculateMedianNumber(null), "Given median should be 0 in case of null as given entries.");
    }

    @Test
    void test_max_number_with_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();
        numberEntries.add(new NumberEntry(null, 1));
        numberEntries.add(new NumberEntry(null, 3));
        numberEntries.add(new NumberEntry(null, 10));

        Assertions.assertEquals(10, numberEntryCalculationService.getMaxNumber(numberEntries), "Given max value is not the real max value.");
    }

    @Test
    void test_max_number_with_no_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();

        Assertions.assertEquals(0, numberEntryCalculationService.getMaxNumber(numberEntries), "Given max value should be 0 in case of no given entries.");
    }

    @Test
    void test_max_number_with_null() {
        Assertions.assertEquals(0, numberEntryCalculationService.getMaxNumber(null), "Given max value should be 0 in case of null as given entries.");
    }

    @Test
    void test_min_number_with_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();
        numberEntries.add(new NumberEntry(null, 1));
        numberEntries.add(new NumberEntry(null, 3));
        numberEntries.add(new NumberEntry(null, 10));

        Assertions.assertEquals(1, numberEntryCalculationService.getMinNumber(numberEntries), "Given min value is not the real min value.");
    }

    @Test
    void test_min_number_with_no_entries() {
        List<NumberEntry> numberEntries = new ArrayList<>();

        Assertions.assertEquals(0, numberEntryCalculationService.getMinNumber(numberEntries), "Given min value should be 0 in case of no given entries.");
    }

    @Test
    void test_min_number_with_null() {
        Assertions.assertEquals(0, numberEntryCalculationService.getMinNumber(null), "Given min value should be 0 in case of null as given entries.");
    }
}

@SpringBootTest
class NumberEntryServiceTests {

    @Autowired
    private NumberEntryService numberEntryService;

    @Autowired
    private NumberEntryRepository numberEntryRepository;

    @Test
    void test_add_number() {
        NumberEntryFormModel form = new NumberEntryFormModel();
        form.setNumber(1);
        numberEntryService.addNumber(form);
        form.setNumber(2);
        numberEntryService.addNumber(form);
        Assertions.assertEquals(2, numberEntryRepository.findTopByOrderByIdDesc().getNumber(), "Number should be added to the database.");
    }

    @AfterEach
    public void tearDown() {
        numberEntryRepository.delete(numberEntryRepository.findTopByOrderByIdDesc());
        numberEntryRepository.delete(numberEntryRepository.findTopByOrderByIdDesc());
    }
}