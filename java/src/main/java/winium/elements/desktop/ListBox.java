package winium.elements.desktop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import winium.elements.desktop.extensions.ByExtensions;

import java.util.HashMap;

public class ListBox extends DesktopElement {
    static final String SCROLL_TO_LIST_BOX_ITEM = "scrollToListBoxItem";

    public ListBox(WebElement element) {
        super(element);
    }

    public RemoteWebElement scrollTo(By by) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("using", ByExtensions.getStrategy(by));
        parameters.put("value", ByExtensions.getValue(by));
        return this.createRemoteWebElementFromResponse(this.execute(SCROLL_TO_LIST_BOX_ITEM, parameters));
    }
}
