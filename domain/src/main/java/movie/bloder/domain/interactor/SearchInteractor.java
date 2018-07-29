package movie.bloder.domain.interactor;

import java.util.List;

import movie.bloder.domain.SingleUseCase;
import movie.bloder.domain.binding.SingleUseCaseBinding;
import movie.bloder.repository.models.Movie;

public class SearchInteractor extends SingleUseCase {

    public SingleUseCaseBinding<List<Movie>> search(String search) {
        return run(repository.forSearch().search(search));
    }
}
