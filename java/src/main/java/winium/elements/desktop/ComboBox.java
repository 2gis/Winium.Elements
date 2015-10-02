package winium.elements.desktop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;

import winium.elements.desktop.extensions.ByExtensions;

import java.util.HashMap;

public class ComboBox extends DesktopElement {
    static final String COLLAPSE_COMBO_BOX = "collapseComboBox";
    static final String EXPAND_COMBO_BOX = "expandComboBox";
    static final String FIND_COMBO_BOX_SELECTED_ITEM = "findComboBoxSelectedItem";
    static final String IS_COMBO_BOX_EXPANDED = "isComboBoxExpanded";
    static final String SCROLL_TO_COMBO_BOX_ITEM = "scrollToComboBoxItem";

    private Response callComboBoxCommand(String command)
    {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        return this.execute(command, parameters);
    }

    public ComboBox(WebElement element) {
        super(element);
    }

    public boolean isExpanded() {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        Response response = this.execute(IS_COMBO_BOX_EXPANDED, parameters);

        return Boolean.parseBoolean(response.getValue().toString());
    }

    public void collapse() {
        this.callComboBoxCommand(COLLAPSE_COMBO_BOX);
    }

    public void expand() {
        this.callComboBoxCommand(EXPAND_COMBO_BOX);
    }

    public RemoteWebElement findSelected() {
        return this.createRemoteWebElementFromResponse(this.callComboBoxCommand(FIND_COMBO_BOX_SELECTED_ITEM));
    }

    public RemoteWebElement scrollTo(By by) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("using", ByExtensions.getStrategy(by));
        parameters.put("value", ByExtensions.getValue(by));
        Response response = this.execute(SCROLL_TO_COMBO_BOX_ITEM, parameters);
        return this.createRemoteWebElementFromResponse(response);
    }
}
