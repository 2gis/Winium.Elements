package winium.elements.desktop;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.SessionId;

import java.net.MalformedURLException;
import java.util.Map;

import static org.mockito.Mockito.*;

public class MockedDriver extends WiniumDriver {
    public MockedDriver(String url, Capabilities desiredCapabilities) throws MalformedURLException {
        super(url, desiredCapabilities);
    }

    @Override
    public Response execute(String driverCommand, Map<String, ?> parameters) {
        return super.execute(driverCommand, parameters);
    }

    public static MockedDriver getDriverMocked() {
        MockedDriver driver = mock(MockedDriver.class);

        when(driver.execute(eq("newSession"), anyMapOf(String.class, Object.class)))
                .thenReturn(new Response(new SessionId("AvesomeSession")));

        return driver;
    }
}
