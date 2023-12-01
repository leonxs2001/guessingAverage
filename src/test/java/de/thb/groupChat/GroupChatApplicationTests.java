package de.thb.groupChat;

import de.thb.groupChat.entities.NumberEntry;
import de.thb.groupChat.service.NumberEntryCalculationService;
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
	void test_regular_average_calculation() {
		List<NumberEntry> numberEntries = new ArrayList<>();
		numberEntries.add(new NumberEntry(null, 1));
		numberEntries.add(new NumberEntry(null, 3));
		numberEntries.add(new NumberEntry(null, 10));

		Assertions.assertEquals(numberEntryCalculationService.calculateAverage(numberEntries), 7);
	}

	@Test
	public void test_average_calculation_with_no_entries(){
		List<NumberEntry> numberEntries = new ArrayList<>();
		Assertions.assertEquals(numberEntryCalculationService.calculateAverage(numberEntries), 0);
	}

	@Test
	public void test_average_calculation_with_null(){
		Assertions.assertEquals(numberEntryCalculationService.calculateAverage(null), 0);
	}

}
