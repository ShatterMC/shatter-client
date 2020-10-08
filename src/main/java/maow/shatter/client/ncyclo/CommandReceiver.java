package maow.shatter.client.ncyclo;

import maow.ncycloapi.Receiver;
import maow.shatter.api.Command;
import maow.shatter.api.CommandRegistry;
import maow.shatter.client.util.LogUtil;
import maow.shatter.client.util.ParsedCommand;
import maow.shatter.client.util.StringParseUtil;
import org.apache.logging.log4j.Level;

public class CommandReceiver implements Receiver {
    @Override
    public void onReceive(String data) {
        ParsedCommand command = StringParseUtil.parseCommand(data);
        if (command != null) {
            LogUtil.log(Level.INFO, "Received command: " + command.toString());
            Command realCommand = CommandRegistry.getInstance().get(command.getName());
            if (realCommand != null) {
                realCommand.run(command.getArgs());
            }
        }
    }
}
