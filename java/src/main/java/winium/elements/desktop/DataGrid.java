package winium.elements.desktop;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import java.util.HashMap;

public class DataGrid extends DesktopElement {
    public static final String FIND_DATA_GRID_CELL = "findDataGridCell";
    public static final String GET_DATA_GRID_COLUMN_COUNT = "getDataGridColumnCount";
    public static final String GET_DATA_GRID_ROW_COUNT = "getDataGridRowCount";
    public static final String SCROLL_TO_DATA_GRID_CELL = "scrollToDataGridCell";
    public static final String SELECT_DATA_GRID_CELL = "selectDataGridCell";

    private Response callDataGridCellCommand(String command, int row, int column) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("row", row);
        parameters.put("column", column);
        return this.execute(command, parameters);
    }

    public DataGrid(WebElement element) {
        super(element);
    }

    public int getColumnCount() {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        Response response = this.execute(GET_DATA_GRID_COLUMN_COUNT, parameters);
        return Integer.parseInt(response.getValue().toString());
    }

    public int getRowCount() {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        Response response = this.execute(GET_DATA_GRID_ROW_COUNT, parameters);
        return Integer.parseInt(response.getValue().toString());
    }

    public RemoteWebElement find(int row, int column) {
        return this.createRemoteWebElementFromResponse(this.callDataGridCellCommand(FIND_DATA_GRID_CELL, row, column));
    }

    public void scrollTo(int row, int column) {
        this.callDataGridCellCommand(SCROLL_TO_DATA_GRID_CELL, row, column);
    }

    public void select(int row, int column) {
        this.callDataGridCellCommand(SELECT_DATA_GRID_CELL, row, column);
    }
}
