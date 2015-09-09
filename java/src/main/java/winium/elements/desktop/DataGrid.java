package winium.elements.desktop;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.http.HttpMethod;
import winium.elements.desktop.internal.CommandInfoRepository;

import java.util.HashMap;

public class DataGrid extends DesktopElement {
    private static final String findDataGridCell = "findDataGridCell";
    private static final String getDataGridColumnCount = "getDataGridColumnCount";
    private static final String getDataGridRowCount = "getDataGridRowCount";
    private static final String scrollToDataGridCell = "scrollToDataGridCell";
    private static final String selectDataGridCell = "selectDataGridCell";

    private Response callDataGridCellCommand(String command, int row, int column) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("row", row);
        parameters.put("column", column);
        return this.execute(command, parameters);
    }

    static {
        CommandInfoRepository.tryAddCommand(findDataGridCell,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/cell/{row}/{column}", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(getDataGridColumnCount,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/column/count", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(getDataGridRowCount,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/row/count", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(scrollToDataGridCell,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/scroll/{row}/{column}", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(selectDataGridCell,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/select/{row}/{column}", HttpMethod.POST));
    }

    public DataGrid(WebElement element) {
        super(element);
    }

    public int getColumnCount() {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        Response response = this.execute(getDataGridColumnCount, parameters);
        return Integer.parseInt(response.getValue().toString());
    }

    public int getRowCount() {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        Response response = this.execute(getDataGridRowCount, parameters);
        return Integer.parseInt(response.getValue().toString());
    }

    public RemoteWebElement find(int row, int column) {
        return this.createRemoteWebElementFromResponse(this.callDataGridCellCommand(findDataGridCell, row, column));
    }

    public void scrollTo(int row, int column) {
        this.callDataGridCellCommand(scrollToDataGridCell, row, column);
    }

    public void select(int row, int column) {
        this.callDataGridCellCommand(selectDataGridCell, row, column);
    }
}
