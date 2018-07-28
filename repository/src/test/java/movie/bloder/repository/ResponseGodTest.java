package movie.bloder.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;

import movie.bloder.repository.response_god.ResponseGod;

@RunWith(JUnit4.class)
public class ResponseGodTest {

    @Mock private ResponseGod responseGod = Mockito.mock(ResponseGod.class);

    @Test public void testOnUnknownError() {
        mockResponseGodWith(401);
        Mockito.doNothing().when(responseGod).onUnknown(401);
        responseGod.handle(401);
        Mockito.verify(responseGod, Mockito.times(1)).onUnknown(401);
    }

    @Test public void testOn200Response() {
        mockResponseGodWith(200);
        Mockito.doReturn(null).when(responseGod).on200();
        responseGod.handle(200);
        Mockito.verify(responseGod, Mockito.times(1)).on200();
    }

    private void mockResponseGodWith(int code) {
        Mockito.doCallRealMethod().when(responseGod).handle(code);
    }
}
