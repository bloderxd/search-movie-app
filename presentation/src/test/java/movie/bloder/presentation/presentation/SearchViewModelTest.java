package movie.bloder.presentation.presentation;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import java.util.List;

import io.reactivex.Single;
import movie.bloder.domain.interactor.SearchInteractor;
import movie.bloder.presentation.presentation.state.search.SearchState;
import movie.bloder.presentation.presentation.view_model.search.SearchViewModel;
import movie.bloder.repository.models.Movie;
import movie.bloder.repository.repository.Repository;
import movie.bloder.repository.repository.RepositoryFactory;
import movie.bloder.repository.repository.resources.SearchRepository;
import movie.bloder.repository.response_god.search.SEARCH_EXCEPTION;
import movie.bloder.repository.response_god.search.SearchException;

@RunWith(JUnit4.class)
public class SearchViewModelTest {

    @Rule public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
    private SearchInteractor interactor = new SearchInteractor();
    private RepositoryFactory repository = Repository.getAsTest();
    private SearchViewModel viewModel = new SearchViewModel(interactor);

    @Before public void setup() {
        interactor.provideRepository(repository);
    }

    @Test public void testSuccessOnSearch() {
        viewModel.search("");
        Assert.assertTrue(viewModel.state().getValue() instanceof SearchState.OnSuccess);
    }

    @Test public void testOnNotFoundSearch() {
        mockBackEndWith(Single.create(emitter -> emitter.onError(new SearchException(SEARCH_EXCEPTION.NOT_FOUND))));
        viewModel.search("");
        Assert.assertTrue(viewModel.state().getValue() instanceof SearchState.OnNotFound);
    }

    @Test public void testUnknownErrorOnSearch() {
        mockBackEndWith(Single.create(emitter -> emitter.onError(new SearchException(SEARCH_EXCEPTION.UNKNOWN_ERROR))));
        viewModel.search("");
        Assert.assertTrue(viewModel.state().getValue() instanceof SearchState.OnUnknownError);
    }

    private void mockBackEndWith(Single<List<Movie>> mockedSingle) {
        RepositoryFactory mockedRepository = Mockito.mock(RepositoryFactory.class);
        SearchRepository mockedSearchRepository = Mockito.mock(SearchRepository.class);
        interactor.provideRepository(mockedRepository);
        Mockito.when(mockedRepository.forSearch()).thenReturn(mockedSearchRepository);
        Mockito.when(mockedSearchRepository.search(Mockito.any())).thenReturn(mockedSingle);
    }
}
