package movie.bloder.repository.payloads;

import com.google.gson.annotations.SerializedName;

import movie.bloder.repository.models.Movie;

public class MoviePayload {

    @SerializedName("imdbID") private final String id;
    @SerializedName("Title") private final String title;
    @SerializedName("Year") private final String year;
    @SerializedName("Type") private final String type;
    @SerializedName("Poster") private final String image;

    public MoviePayload(String id, String title, String year, String type, String image) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.type = type;
        this.image = image;
    }

    Movie toModel() {
        return new Movie(id, title, year, image);
    }

    Boolean isMovie() {
        return type.equals("movie");
    }
}
