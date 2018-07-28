package movie.bloder.repository.repository.fake.search;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import movie.bloder.repository.models.Movie;
import movie.bloder.repository.repository.resources.SearchRepository;

public class SearchFakeRepository implements SearchRepository {

    @Override public Single<List<Movie>> search(String search) {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("1", "Juliana the movie", "2000", ""));
        movies.add(new Movie("2", "Mariana o retorno", "2002", ""));
        movies.add(new Movie("3", "Bloder", "1997", ""));
        return Single.just(movies);
    }
}
