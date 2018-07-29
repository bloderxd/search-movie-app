package movie.bloder.presentation.presentation.view_model.search;

import movie.bloder.domain.interactor.SearchInteractor;
import movie.bloder.presentation.presentation.state.search.SearchState;
import movie.bloder.presentation.presentation.view_model.AppViewModel;
import movie.bloder.repository.response_god.search.SEARCH_EXCEPTION;
import movie.bloder.repository.response_god.search.SearchException;

public class SearchViewModel extends AppViewModel<SearchState> {

    private SearchInteractor interactor;

    public SearchViewModel(SearchInteractor interactor) {
        this.interactor = interactor;
    }

    public void search(String query) {
        interactor.search(query)
                .onSuccess(response -> postState(new SearchState.OnSuccess(response)))
                .onError(error ->
                        error instanceof SearchException
                                ? handleSearchError((SearchException)error)
                                : postState(new SearchState.OnUnknownError())
                ).bind();
    }

    private AppViewModel<SearchState> handleSearchError(SearchException error) {
        if (error.exception.equals(SEARCH_EXCEPTION.NOT_FOUND)) return postState(new SearchState.OnNotFound());
        return postState(new SearchState.OnUnknownError());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        interactor.dispose();
    }
}
