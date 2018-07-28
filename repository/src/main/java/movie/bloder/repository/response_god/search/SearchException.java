package movie.bloder.repository.response_god.search;

class SearchException extends Exception {

    public final SEARCH_EXCEPTION exception;

    SearchException(SEARCH_EXCEPTION exception) {
        this.exception = exception;
    }
}

enum SEARCH_EXCEPTION {
    NOT_FOUND, UNKNOWN_ERROR
}
