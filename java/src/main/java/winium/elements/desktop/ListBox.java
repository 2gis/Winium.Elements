package winium.elements.desktop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.http.HttpMethod;
import winium.elements.desktop.extensions.ByExtensions;
import winium.elements.desktop.internal.CommandInfoRepository;

import java.util.HashMap;

public class ListBox extends DesktopElement {
    private static final String scrollToListBoxItem = "scrollToListBoxItem";

    static {
        CommandInfoRepository.tryAddCommand(scrollToListBoxItem,
                new CommandInfo("/session/{sessionId}/element/{id}/listbox/scroll", HttpMethod.POST));
    }

    public ListBox(WebElement element) {
        super(element);
    }

    public RemoteWebElement scrollTo(By by) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("using", ByExtensions.getStrategy(by));
        parameters.put("value", ByExtensions.getValue(by));
        return this.createRemoteWebElementFromResponse(this.execute(scrollToListBoxItem, parameters));
    }
}
