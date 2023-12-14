package de.thb.guessingaverage.repositories;

import de.thb.guessingaverage.entities.NumberEntry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NumberEntryRepository extends CrudRepository<NumberEntry, Long> {
    List<NumberEntry> findAll();

    NumberEntry findTopByOrderByIdDesc();
}
