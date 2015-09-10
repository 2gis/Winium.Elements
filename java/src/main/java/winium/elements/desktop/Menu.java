package winium.elements.desktop;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;

public class Menu extends DesktopElement {
    public static final String FIND_MENU_ITEM = "findMenuItem";
    public static final String SELECT_MENU_ITEM = "selectMenuItem";

    private RemoteWebElement callMenuItemCommand(String command, String path) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("path", path);
        return this.createRemoteWebElementFromResponse(this.execute(command, parameters));

    }

    public Menu(WebElement element) {
        super(element);
    }

    public RemoteWebElement findItem(String path) {
        return this.callMenuItemCommand(FIND_MENU_ITEM, path);
    }

    public RemoteWebElement selectItem(String path) {
        return this.callMenuItemCommand(SELECT_MENU_ITEM, path);
    }
}
