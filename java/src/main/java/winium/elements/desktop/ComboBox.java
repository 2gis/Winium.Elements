package winium.elements.desktop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.http.HttpMethod;

import winium.elements.desktop.internal.CommandInfoRepository;
import winium.elements.desktop.extensions.ByExtensions;

import java.util.HashMap;

public class ComboBox extends DesktopElement {
    private static final String collapseComboBox = "collapseComboBox";
    private static final String expandComboBox = "expandComboBox";
    private static final String findComboBoxSelectedItem = "findComboBoxSelectedItem";
    private static final String isComboBoxExpanded = "isComboBoxExpanded";
    private static final String scrollToComboBoxItem = "scrollToComboBoxItem";

    static {
        CommandInfoRepository.tryAddCommand(isComboBoxExpanded,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/expanded", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(expandComboBox,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/expand", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(collapseComboBox,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/collapse", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(findComboBoxSelectedItem,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/items/selected", HttpMethod.POST));
        CommandInfoRepository.tryAddCommand(scrollToComboBoxItem,
                new CommandInfo("/session/{sessionId}/element/{id}/combobox/scroll", HttpMethod.POST));
    }

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
        Response response = this.execute(isComboBoxExpanded, parameters);

        return Boolean.parseBoolean(response.getValue().toString());
    }

    public void collapse() {
        this.callComboBoxCommand(collapseComboBox);
    }

    public void expand() {
        this.callComboBoxCommand(expandComboBox);
    }

    public RemoteWebElement findSelected(int row, int column) {
        return this.createRemoteWebElementFromResponse(this.callComboBoxCommand(findComboBoxSelectedItem));
    }

    public RemoteWebElement scrollTo(By by) {
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", this.getId());
        parameters.put("using", ByExtensions.getStrategy(by));
        parameters.put("value", ByExtensions.getValue(by));
        Response response = this.execute(scrollToComboBoxItem, parameters);
        return this.createRemoteWebElementFromResponse(response);
    }
}
