package movie.bloder.repository.repository.resources;


import java.util.List;

import io.reactivex.Single;
import movie.bloder.repository.models.Movie;

public interface SearchRepository {

    Single<List<Movie>> search(String search);
}
