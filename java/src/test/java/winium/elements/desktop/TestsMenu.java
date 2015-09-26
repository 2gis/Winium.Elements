package winium.elements.desktop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import winium.elements.desktop.extensions.WebElementExtensions;

import static org.mockito.Mockito.*;

public class TestsMenu {
    @Test
    public void testGetMenuItem() {
        MockedDriver mockedDriver = MockedDriver.getDriverMocked();

        Response webElementResponse = Responses.getWebElementResponse();
        when(mockedDriver.execute(eq("findMenuItem"), anyMapOf(String.class, Object.class)))
                .thenReturn(webElementResponse);

        RemoteWebElement webElement = mock(RemoteWebElement.class);
        when(webElement.getWrappedDriver()).thenReturn(mockedDriver);
        when(webElement.getId()).thenReturn("menutId");

        Menu menu = WebElementExtensions.toMenu(webElement);

        Assert.assertNotNull(menu.findItem("Level1$Level2"));
    }

    @Test
    public void testSelectMenuItem() {
        MockedDriver mockedDriver = MockedDriver.getDriverMocked();

        Response webElementResponse = Responses.getWebElementResponse();
        when(mockedDriver.execute(eq("selectMenuItem"), anyMapOf(String.class, Object.class)))
                .thenReturn(webElementResponse);

        RemoteWebElement webElement = mock(RemoteWebElement.class);
        when(webElement.getWrappedDriver()).thenReturn(mockedDriver);
        when(webElement.getId()).thenReturn("menutId");

        Menu menu = WebElementExtensions.toMenu(webElement);

        Assert.assertNotNull(menu.selectItem("Level1$Level2"));
    }
}
