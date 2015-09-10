package winium.elements.desktop;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.http.HttpMethod;
import winium.elements.desktop.internal.CommandInfoRepository;

import java.util.HashMap;

public class Menu extends DesktopElement {
    private static final String findMenuItem = "findMenuItem";
    private static final String selectMenuItem = "selectMenuItem";

    static {
        CommandInfoRepository.tryAddCommand(findMenuItem,
                new CommandInfo("/session/{sessionId}/element/{id}/menu/item/{path}", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(selectMenuItem,
                new CommandInfo("/session/{sessionId}/element/{id}/menu/select/{path}", HttpMethod.POST));
    }

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
        return this.callMenuItemCommand(findMenuItem, path);
    }

    public RemoteWebElement selectItem(String path) {
        return this.callMenuItemCommand(selectMenuItem, path);
    }
}
