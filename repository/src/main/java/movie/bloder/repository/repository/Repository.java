package movie.bloder.repository.repository;


import movie.bloder.repository.repository.fake.FakeRepository;
import movie.bloder.repository.repository.production.ProductionRepository;

public class Repository {

    public static RepositoryFactory getProduction() {
        return new ProductionRepository();
    }

    public static RepositoryFactory getAsTest() {
        return new FakeRepository();
    }
}
