package movie.bloder.repository.repository.production;


import movie.bloder.repository.repository.RepositoryFactory;
import movie.bloder.repository.repository.production.search.SearchProductionRepository;
import movie.bloder.repository.repository.resources.SearchRepository;

public class ProductionRepository implements RepositoryFactory {

    @Override public SearchRepository forSearch() {
        return new SearchProductionRepository();
    }
}
