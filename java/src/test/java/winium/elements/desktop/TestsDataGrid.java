package winium.elements.desktop;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import winium.elements.desktop.extensions.WebElementExtensions;

import static org.mockito.Mockito.*;

public class TestsDataGrid {
    @Test
    public void testFindDataGridCell() {
        MockedDriver mockedDriver = MockedDriver.getDriverMocked();

        Response webElementResponse = Responses.getWebElementResponse();
        when(mockedDriver.execute(eq("findDataGridCell"), anyMapOf(String.class, Object.class)))
                .thenReturn(webElementResponse);

        RemoteWebElement webElement = mock(RemoteWebElement.class);
        when(webElement.getWrappedDriver()).thenReturn(mockedDriver);
        when(webElement.getId()).thenReturn("dGridElementId");

        DataGrid dataGrid = WebElementExtensions.toDataGrid(webElement);

        Assert.assertNotNull(dataGrid.find(1, 1));
    }

    @Test
    public void testGetDataGridColumnCount() {
        MockedDriver mockedDriver = MockedDriver.getDriverMocked();

        when(mockedDriver.execute(eq("getDataGridColumnCount"), anyMapOf(String.class, Object.class)))
                .thenReturn(Responses.getIntResponse(2));

        RemoteWebElement webElement = mock(RemoteWebElement.class);
        when(webElement.getWrappedDriver()).thenReturn(mockedDriver);
        when(webElement.getId()).thenReturn("dGridElementId");

        DataGrid dataGrid = WebElementExtensions.toDataGrid(webElement);

        Assert.assertEquals(dataGrid.getColumnCount(), 2);
    }

    @Test
    public void testGetDataGridRowCount() {
        MockedDriver mockedDriver = MockedDriver.getDriverMocked();

        when(mockedDriver.execute(eq("getDataGridRowCount"), anyMapOf(String.class, Object.class)))
                .thenReturn(Responses.getIntResponse(5));

        RemoteWebElement webElement = mock(RemoteWebElement.class);
        when(webElement.getWrappedDriver()).thenReturn(mockedDriver);
        when(webElement.getId()).thenReturn("dGridElementId");

        DataGrid dataGrid = WebElementExtensions.toDataGrid(webElement);

        Assert.assertEquals(dataGrid.getRowCount(), 5);
    }
}
