package winium.elements.desktop;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class WiniumDriver extends RemoteWebDriver {
    private static HashMap<String, CommandInfo> commands;

    static {
        commands = new HashMap<String, CommandInfo>();

        commands.put(ComboBox.IS_COMBO_BOX_EXPANDED,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/expanded", HttpMethod.POST));
        commands.put(ComboBox.EXPAND_COMBO_BOX,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/expand", HttpMethod.POST));
        commands.put(ComboBox.COLLAPSE_COMBO_BOX,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/collapse", HttpMethod.POST));
        commands.put(ComboBox.FIND_COMBO_BOX_SELECTED_ITEM,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/items/selected", HttpMethod.POST));
        commands.put(ComboBox.SCROLL_TO_COMBO_BOX_ITEM,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/scroll", HttpMethod.POST));

        commands.put(DataGrid.FIND_DATA_GRID_CELL,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/cell/{row}/{column}", HttpMethod.POST));
        commands.put(DataGrid.GET_DATA_GRID_COLUMN_COUNT,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/column/count", HttpMethod.POST));
        commands.put(DataGrid.GET_DATA_GRID_ROW_COUNT,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/row/count", HttpMethod.POST));
        commands.put(DataGrid.SCROLL_TO_DATA_GRID_CELL,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/scroll/{row}/{column}", HttpMethod.POST));
        commands.put(DataGrid.SELECT_DATA_GRID_CELL,
                new CommandInfo("/session/{sessionId}/element/{id}/datagrid/select/{row}/{column}", HttpMethod.POST));

        commands.put(ListBox.SCROLL_TO_LIST_BOX_ITEM,
                new CommandInfo("/session/{sessionId}/element/{id}/listbox/scroll", HttpMethod.POST));

        commands.put(Menu.FIND_MENU_ITEM,
                new CommandInfo("/session/{sessionId}/element/{id}/menu/item/{path}", HttpMethod.POST));
        commands.put(Menu.SELECT_MENU_ITEM,
                new CommandInfo("/session/{sessionId}/element/{id}/menu/select/{path}", HttpMethod.POST));
    }

    public WiniumDriver(String url, Capabilities desiredCapabilities) throws MalformedURLException {
        super(new HttpCommandExecutor(commands, new URL(url)), desiredCapabilities);
    }
}
