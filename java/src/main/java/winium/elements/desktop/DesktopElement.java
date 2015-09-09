package winium.elements.desktop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.Response;
import winium.elements.desktop.extensions.WebElementExtensions;

import java.util.Map;

public abstract class DesktopElement extends RemoteWebElement {
    private static RemoteWebDriver getRemoteWebDriver(WebElement element) {
        if (!(element instanceof RemoteWebElement))
            throw new ClassCastException("Specified cast is not valid. Please use RemoteWebElement as parameter.");
        RemoteWebElement remoteWebElement = (RemoteWebElement)element;
        return (RemoteWebDriver)remoteWebElement.getWrappedDriver();
    }

    protected DesktopElement(WebElement element) {
        this.setParent(getRemoteWebDriver(element));
        this.setId(WebElementExtensions.getId(element));
    }

    protected RemoteWebElement createRemoteWebElementFromResponse(Response response) {
        Object value = response.getValue();
        if (!(value instanceof Map<?, ?>)) {
            return null;
        }
        Map<String, Object> elementDictionary = (Map<String, Object>)value;
        RemoteWebElement result = new RemoteWebElement();
        result.setParent((RemoteWebDriver)this.getWrappedDriver());
        result.setId((String)elementDictionary.get("ELEMENT"));
        return result;
    }
}
