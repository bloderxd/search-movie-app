package movie.bloder.domain;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.observers.TestObserver;
import movie.bloder.domain.interactor.SearchInteractor;
import movie.bloder.repository.models.Movie;
import movie.bloder.repository.repository.Repository;
import movie.bloder.repository.repository.RepositoryFactory;
import movie.bloder.repository.repository.resources.SearchRepository;
import movie.bloder.repository.response_god.search.SEARCH_EXCEPTION;
import movie.bloder.repository.response_god.search.SearchException;

@RunWith(JUnit4.class)
public class SearchInteractorTest {

    @Rule public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private SearchInteractor interactor = new SearchInteractor();

    @Before public void setup() {
        interactor.provideRepository(Repository.getAsTest());
    }

    @Test public void testCompleteSearch() {
        searchWith(
                response -> response,
                error -> error
        ).assertComplete();
    }

    @Test public void testSuccessResponse() {
        searchWith(
                response -> response,
                error -> error
        ).assertValue(value -> !value.isEmpty());
    }

    @Test public void testErrorResponse() {
        RepositoryFactory repository = Mockito.mock(RepositoryFactory.class);
        interactor.provideRepository(repository);
        SearchRepository searchRepository = Mockito.mock(SearchRepository.class);
        Mockito.when(repository.forSearch()).thenReturn(searchRepository);
        Mockito.when(searchRepository.search(Mockito.any())).thenReturn(Single.create(emitter -> emitter.onError(new SearchException(SEARCH_EXCEPTION.UNKNOWN_ERROR))));
        searchWith(
                response -> response,
                error -> error
        ).assertError(error -> error instanceof SearchException);
    }

    private TestObserver<List<Movie>> searchWith(Function<List<Movie>, ?> onSuccess, Function<Throwable, ?> onError) {
        return interactor.search("")
                .onSuccess(onSuccess)
                .onError(onError)
                .bind()
                .test();
    }
}
