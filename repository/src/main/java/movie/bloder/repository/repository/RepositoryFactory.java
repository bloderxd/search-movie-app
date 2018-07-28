package movie.bloder.repository.repository;


import movie.bloder.repository.repository.resources.SearchRepository;

public interface RepositoryFactory {

    SearchRepository forSearch();
}
