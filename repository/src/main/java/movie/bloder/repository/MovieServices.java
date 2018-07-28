package movie.bloder.repository;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieServices {

    @GET() Single<Response<MovieSearchResponsePayload>> search(@Query("s") String search, @Query("apikey") String apiKey);
}
