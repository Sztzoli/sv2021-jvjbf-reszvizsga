package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;
    private String title;
    private LocalDateTime date;
    private int maxReservation;
    private int freeSpaces;

    public Movie(Long id, String title, LocalDateTime date, int maxReservation) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.maxReservation = maxReservation;
        this.freeSpaces = maxReservation;
    }

    public void reserveSpaces(int value) {
        if (freeSpaces - value < 0) {
            throw new IllegalStateException("not enough available spaces");
        }
        freeSpaces -= value;
    }
}
