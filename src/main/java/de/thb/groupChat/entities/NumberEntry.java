package de.thb.groupChat.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class NumberEntry implements Comparable<NumberEntry>{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime dateTime;

    private int number;

    @Override
    public int compareTo(NumberEntry numberEntry) {
        return Integer.compare(number, numberEntry.getNumber());
    }
}
