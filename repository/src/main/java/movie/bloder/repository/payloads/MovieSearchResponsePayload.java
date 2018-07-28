package movie.bloder.repository.payloads;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.stream.Collectors;

import movie.bloder.repository.models.Movie;

public class MovieSearchResponsePayload {

    @SerializedName("Search") private final List<MoviePayload> searchResult;
    @SerializedName("totalResults") private final int total;

    public MovieSearchResponsePayload(List<MoviePayload> searchResult, int total) {
        this.searchResult = searchResult;
        this.total = total;
    }

    public List<Movie> toModel() {
        return searchResult.stream()
                .filter(movie -> movie.isMovie())
                .map(MoviePayload::toModel)
                .collect(Collectors.toList());
    }
}
