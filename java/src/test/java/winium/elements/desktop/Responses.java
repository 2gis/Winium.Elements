package winium.elements.desktop;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import static org.mockito.Mockito.*;

public class Responses {
    private Responses() { }

    public static Response getTrueResponse() {
        Response result = new Response();
        result.setValue(true);
        return result;
    }

    public static Response getIntResponse(int value) {
        Response result = new Response();
        result.setValue(value);
        return result;
    }

    public static Response getWebElementResponse() {
        Response response = mock(Response.class);
        when(response.getValue()).thenReturn(mock(RemoteWebElement.class));
        return response;
    }
}
