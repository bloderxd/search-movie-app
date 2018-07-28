package movie.bloder.repository;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import movie.bloder.repository.payloads.MoviePayload;
import movie.bloder.repository.payloads.MovieSearchResponsePayload;

@RunWith(JUnit4.class)
public class MovieModelTest {

    @Test public void testMoviePayloadTransformation() {
        List<MoviePayload> moviePayloads = new ArrayList<MoviePayload>(){{
            add(new MoviePayload("1", "", "", "movie", ""));
        }};
        MovieSearchResponsePayload response = new MovieSearchResponsePayload(moviePayloads, 1);
        Assert.assertEquals("1", response.toModel().get(0).id);
    }
}
