package movie.bloder.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import io.reactivex.observers.TestObserver;
import movie.bloder.repository.models.Movie;
import movie.bloder.repository.repository.Repository;
import movie.bloder.repository.repository.RepositoryFactory;

@RunWith(JUnit4.class)
public class RepositoryTest {

    private final RepositoryFactory repository = Repository.getAsTest();

    @Test public void testSearchRepositoryReturnsFillData() {
        TestObserver<List<Movie>> testObserver = repository.forSearch().search("").test();
        testObserver.assertValue(movies -> movies.stream().filter(movie -> movie.title.equals("Juliana the movie")).count() == 1);
    }
}
