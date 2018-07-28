package movie.bloder.repository.payloads;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchResponsePayload {

    @SerializedName("Search") private final List<MoviePayload> searchResult;
    @SerializedName("totalResults") private final int total;

    public MovieSearchResponsePayload(List<MoviePayload> searchResult, int total) {
        this.searchResult = searchResult;
        this.total = total;
    }

    public List<String> toModel() { return new ArrayList<>(); }
}