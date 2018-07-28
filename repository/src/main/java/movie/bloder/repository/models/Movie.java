package movie.bloder.repository.models;

public class Movie {

    public final String id;
    public final String title;
    public final String year;
    public final String image;

    public Movie(String id, String title, String year, String image) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.image = image;
    }
}
