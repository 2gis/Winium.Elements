package winium.elements.desktop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import winium.elements.desktop.extensions.WebElementExtensions;

import static org.mockito.Mockito.*;

public class TestsComboBox {
    @Test
    public void testIsComboBoxExpanded() {
        MockedDriver mockedDriver = MockedDriver.getDriverMocked();

        when(mockedDriver.execute(eq("isComboBoxExpanded"), anyMapOf(String.class, Object.class)))
                .thenReturn(Responses.getTrueResponse());

        RemoteWebElement webElement = mock(RemoteWebElement.class);
        when(webElement.getWrappedDriver()).thenReturn(mockedDriver);
        when(webElement.getId()).thenReturn("comboBoxElementId");

        ComboBox comboBox = WebElementExtensions.toComboBox(webElement);

        Assert.assertTrue(comboBox.isExpanded());
    }

    @Test
    public void testScrollToComboBoxItem() {
        MockedDriver mockedDriver = MockedDriver.getDriverMocked();

        Response webElementResponse = Responses.getWebElementResponse();
        when(mockedDriver.execute(eq("scrollToComboBoxItem"), anyMapOf(String.class, Object.class)))
                .thenReturn(webElementResponse);

        RemoteWebElement webElement = mock(RemoteWebElement.class);
        when(webElement.getWrappedDriver()).thenReturn(mockedDriver);
        when(webElement.getId()).thenReturn("comboBoxElementId");

        ComboBox comboBox = WebElementExtensions.toComboBox(webElement);

        Assert.assertNotNull(comboBox.scrollTo(By.id("comboBoxItemElementId")));
    }
}
