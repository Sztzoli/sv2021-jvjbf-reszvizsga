package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieCommand {

    @NotBlank(message = "movie title cannot be empty")
    private String title;

    private LocalDateTime date;

    @Min(value = 20, message = "minimum 20 space")
    private int maxReservation;
}
