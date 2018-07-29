package movie.bloder.repository.response_god.search;

public class SearchException extends Exception {

    public final SEARCH_EXCEPTION exception;

    public SearchException(SEARCH_EXCEPTION exception) {
        this.exception = exception;
    }
}
