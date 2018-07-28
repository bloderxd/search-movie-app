package movie.bloder.repository.repository.fake;

import movie.bloder.repository.repository.RepositoryFactory;
import movie.bloder.repository.repository.fake.search.SearchFakeRepository;
import movie.bloder.repository.repository.resources.SearchRepository;

public class FakeRepository implements RepositoryFactory {

    @Override public SearchRepository forSearch() {
        return new SearchFakeRepository();
    }
}
