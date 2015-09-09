package winium.elements.desktop.extensions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class WebElementExtensions {
    private WebElementExtensions() { }

    public static Response execute(WebElement element, Object... parameters) {
        // TODO: Rewrite this
        Method methodInfo = null;
        try {
            methodInfo = element.getClass().getMethod("execute", new Class<?>[]{});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            return (Response)methodInfo.invoke(element, parameters);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getId(WebElement element) {
        return null;
    }
}
