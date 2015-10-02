package winium.elements.desktop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import winium.elements.desktop.extensions.WebElementExtensions;

import static org.mockito.Mockito.*;

public class TestsListBox {
    @Test
    public void testScrollToListBoxItem() {
        MockedDriver mockedDriver = MockedDriver.getDriverMocked();

        Response webElementResponse = Responses.getWebElementResponse();
        when(mockedDriver.execute(eq("scrollToListBoxItem"), anyMapOf(String.class, Object.class)))
                .thenReturn(webElementResponse);

        RemoteWebElement webElement = mock(RemoteWebElement.class);
        when(webElement.getWrappedDriver()).thenReturn(mockedDriver);
        when(webElement.getId()).thenReturn("listBoxElementId");

        ListBox listBox = WebElementExtensions.toListBox(webElement);

        Assert.assertNotNull(listBox.scrollTo(By.id("listBoxItemElementId")));
    }
}
