package winium.elements.desktop.internal;

import org.openqa.selenium.remote.CommandInfo;

import java.util.HashMap;

public class CommandInfoRepository {
    private CommandInfoRepository() { }

    private static HashMap<String, CommandInfo> commands;

    static {
        commands = new HashMap<String, CommandInfo>();
    }

    public static boolean tryAddCommand(String commandName, CommandInfo commandInfo) {
        commands.put(commandName, commandInfo);
        return true;
    }
}
