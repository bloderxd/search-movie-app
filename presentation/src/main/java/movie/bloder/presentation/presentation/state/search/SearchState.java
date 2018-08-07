package movie.bloder.presentation.presentation.state.search;

import java.util.List;

import movie.bloder.presentation.presentation.state.State;
import movie.bloder.repository.models.Movie;

public class SearchState implements State {

    public static class OnSuccess extends SearchState {

        public final List<Movie> movies;

        public OnSuccess(List<Movie> movies) {
            this.movies = movies;
        }
    }

    public static class OnNotFound extends SearchState {}
    public static class OnUnknownError extends SearchState {}
    public static class OnActivityCreated extends SearchState {}
}
