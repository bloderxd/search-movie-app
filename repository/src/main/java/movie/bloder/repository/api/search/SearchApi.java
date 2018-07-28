package movie.bloder.repository.api.search;

import movie.bloder.repository.api.Api;

public class SearchApi extends Api<MovieServices> {

    @Override public MovieServices getService() {
        return getRetrofit().create(MovieServices.class);
    }
}
