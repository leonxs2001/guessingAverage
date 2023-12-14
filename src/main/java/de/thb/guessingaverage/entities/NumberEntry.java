package de.thb.guessingaverage.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class NumberEntry implements Comparable<NumberEntry>{
    @Id
//    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTime;

    private int number;

    public NumberEntry(LocalDateTime dateTime, int number){
        setNumber(number);
        setDateTime(dateTime);
    }

    @Override
    public int compareTo(NumberEntry numberEntry) {
        return Integer.compare(number, numberEntry.getNumber());
    }
}
