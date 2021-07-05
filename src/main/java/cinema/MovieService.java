package cinema;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private List<Movie> movies = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong();

    private final ModelMapper mapper;

    public MovieService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<MovieDTO> getFilms(Optional<String> title) {
        List<Movie> moviesList = movies.stream()
                .filter(m -> title.isEmpty() || m.getTitle().equalsIgnoreCase(title.get()))
                .collect(Collectors.toList());
        Type targetListType = new TypeToken<List<MovieDTO>>() {
        }.getType();
        return mapper.map(moviesList, targetListType);
    }

    public MovieDTO findFilmById(Long id) {
        return mapper.map(findById(id), MovieDTO.class);
    }


    public MovieDTO addFilm(CreateMovieCommand command) {
        Movie movie =
                new Movie(idGenerator.incrementAndGet(), command.getTitle(),
                        command.getDate(), command.getMaxReservation());
        movies.add(movie);
        return mapper.map(movie, MovieDTO.class);
    }


    public MovieDTO reserveSpaces(Long id, CreateReservationCommand command) {
        Movie movie = findById(id);
        movie.reserveSpaces(command.getNumberOfReserveSpace());
        return mapper.map(movie, MovieDTO.class);
    }

    public MovieDTO updateDate(Long id, UpdateDateCommand command) {
        Movie movie = findById(id);
        movie.setDate(command.getDate());
        return mapper.map(movie, MovieDTO.class);
    }

    public void deleteAll() {
        movies.clear();
        idGenerator = new AtomicLong();
    }

    private Movie findById(Long id) {
        return movies.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new MovieNotFoundException("not found film by id: " + id));
    }
}
