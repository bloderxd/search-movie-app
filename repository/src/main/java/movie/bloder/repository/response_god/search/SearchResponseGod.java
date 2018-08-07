package movie.bloder.repository.response_god.search;

import java.util.List;
import java.util.function.Function;

import io.reactivex.SingleEmitter;
import movie.bloder.repository.models.Movie;
import movie.bloder.repository.payloads.MovieSearchResponsePayload;
import movie.bloder.repository.response_god.ResponseGod;
import retrofit2.Response;

public class SearchResponseGod implements ResponseGod {

    private SingleEmitter<List<Movie>> emmiter;
    private Response<MovieSearchResponsePayload> response;

    public SearchResponseGod(SingleEmitter<List<Movie>> emmiter, Response<MovieSearchResponsePayload> response) {
        this.emmiter = emmiter;
        this.response = response;
    }

    @Override public Function<Void, Void> on200() {
        if (response.body() == null || !response.body().hasResults()) {
            emmiter.onError(new SearchException(SEARCH_EXCEPTION.NOT_FOUND));
            return null;
        }
        emmiter.onSuccess(response.body().toModel());
        return null;
    }

    @Override public void onUnknown(int code) {
        emmiter.onError(new SearchException(SEARCH_EXCEPTION.UNKNOWN_ERROR));
    }
}
