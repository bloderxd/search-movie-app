package movie.bloder.repository.repository.production.search;


import java.util.List;

import io.reactivex.Single;
import movie.bloder.repository.BuildConfig;
import movie.bloder.repository.api.search.SearchApi;
import movie.bloder.repository.models.Movie;
import movie.bloder.repository.repository.resources.SearchRepository;
import movie.bloder.repository.response_god.search.SearchResponseGod;

public class SearchProductionRepository implements SearchRepository {

    @Override public Single<List<Movie>> search(String search) {
        return new SearchApi().getService()
                .search(search, BuildConfig.API_KEY)
                .flatMap(response -> Single.create(emitter -> new SearchResponseGod(emitter, response).handle(response.code())));
    }
}
