package maow.shatter.client.commands;

import maow.shatter.api.Command;
import maow.shatter.client.util.LogUtil;
import org.apache.logging.log4j.Level;

import java.util.Arrays;

public class PingCommand implements Command {
    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public void run(String[] args) {
        LogUtil.log(Level.INFO, "Pong! + " + Arrays.toString(args));
    }
}
