package cinema;

public class MovieNotFoundException extends RuntimeException {

    private String message;

    public MovieNotFoundException(String message) {
        this.message = message;
    }
}
