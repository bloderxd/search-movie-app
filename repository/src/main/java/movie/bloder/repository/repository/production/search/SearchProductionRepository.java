package movie.bloder.repository.repository.production.search;


import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import movie.bloder.repository.BuildConfig;
import movie.bloder.repository.api.search.SearchApi;
import movie.bloder.repository.models.Movie;
import movie.bloder.repository.payloads.MovieSearchResponsePayload;
import movie.bloder.repository.repository.resources.SearchRepository;
import retrofit2.Response;

public class SearchProductionRepository implements SearchRepository {

    @Override public Single<List<Movie>> search(String search) {
        return new SearchApi().getService()
                .search(search, BuildConfig.API_KEY)
                .flatMap(x -> Single.create(emmiter -> ));
    }
}
