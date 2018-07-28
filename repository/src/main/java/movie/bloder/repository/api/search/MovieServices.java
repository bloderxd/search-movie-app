package movie.bloder.repository.api.search;

import io.reactivex.Single;
import movie.bloder.repository.payloads.MovieSearchResponsePayload;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieServices {

    @GET() Single<Response<MovieSearchResponsePayload>> search(@Query("s") String search, @Query("apikey") String apiKey);
}
