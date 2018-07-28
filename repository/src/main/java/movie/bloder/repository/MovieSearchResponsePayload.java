package movie.bloder.repository;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchResponsePayload {

    @SerializedName("Search") private final List<String> searchResult;
    @SerializedName("totalResults") private final int total;

    public MovieSearchResponsePayload(List<String> searchResult, int total) {
        this.searchResult = searchResult;
        this.total = total;
    }

    public List<String> toModel() { return new ArrayList<>(); }
}
