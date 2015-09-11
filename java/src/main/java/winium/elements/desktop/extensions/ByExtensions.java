package winium.elements.desktop.extensions;

import org.openqa.selenium.By;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ByExtensions {
    private ByExtensions() { }

    private static Pattern descriptionRegexp = Pattern.compile("\\.(.*): (.*)");

    public static Object getStrategy(By by) {
        Matcher m = descriptionRegexp.matcher(by.toString());
        if (!m.find())
            return null;
        return m.group(1).replaceAll("([A-Z])", " $1").split("\\[")[0].trim().toLowerCase();
    }

    public static Object getValue(By by) {
        Matcher m = descriptionRegexp.matcher(by.toString());
        if (!m.find())
            return null;
        return m.group(2);
    }
}
