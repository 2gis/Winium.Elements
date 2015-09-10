package winium.elements.desktop.extensions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;
import winium.elements.desktop.ComboBox;
import winium.elements.desktop.DataGrid;
import winium.elements.desktop.ListBox;
import winium.elements.desktop.Menu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WebElementExtensions {
    private WebElementExtensions() { }

    public static DataGrid toDataGrid(WebElement element) {
        return new DataGrid(element);
    }

    public static ListBox toListBox(WebElement element) {
        return new ListBox(element);
    }

    public static ComboBox toComboBox(WebElement element) {
        return new ComboBox(element);
    }

    public static Menu toMenu(WebElement element) {
        return new Menu(element);
    }

    public static Response execute(WebElement element, Object... parameters) {
        // What about access levels? Exceptions?
        for (Method methodInfo : element.getClass().getMethods()) {
            if ("execute".equals(methodInfo.getName())) {
                try {
                    return (Response)methodInfo.invoke(element, parameters);
                } catch (IllegalAccessException e) {
                    return null;
                } catch (InvocationTargetException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static String getId(WebElement element) {
        // What about access levels? Exceptions?
        try {
            Method methodInfo = element.getClass().getMethod("getId");
            return methodInfo.invoke(element).toString();
        } catch (NoSuchMethodException e) {
            return null;
        } catch (InvocationTargetException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
