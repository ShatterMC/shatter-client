package maow.shatter.client.util;

import maow.shatter.client.Shatter;

import java.util.ArrayList;
import java.util.List;

public class StringParseUtil {
    // Typical 'begin' data syntax:
    // ShatterBegin{log=boolean}
    public static void parseBegin(String data) {
        if (parseDataName(data).toLowerCase().equals("shatterbegin")) {
            List<Argument> args = parseDataArgs(data);
            if (args.size() > 0) {
                LogUtil.setShouldLog(Boolean.parseBoolean(args.get(0).getValue()));
                Shatter.startAwaitingCommands();
            }
        }
    }

    // Typical 'command' data syntax:
    // ShatterCommand{command=commandName,args=[arg1,arg2]}
    public static ParsedCommand parseCommand(String data) {
        if (parseDataName(data).toLowerCase().equals("shattercommand")) {
            List<Argument> args = parseDataArgs(data);
            if (args.size() > 1) {
                String commandName = args.get(0).getValue();
                String[] commandArgs = getArgsFromString(args.get(1).getValue());
                return new ParsedCommand(commandName, commandArgs);
            }
        }
        return null;
    }

    private static String parseDataName(String data) {
        return data.substring(0, data.indexOf('{'));
    }

    // Over-complicated method to process the args of all types of 'data syntaxes'
    private static List<Argument> parseDataArgs(String data) {
        List<Argument> arguments = new ArrayList<>();
        String isolatedArgs = data.substring(data.indexOf('{') + 1, data.indexOf('}'));
        String[] args = isolatedArgs.split("(\\s*[,]\\s*)+(?![^\\[]*])");
        for (String arg : args) {
            String key = arg.substring(0, arg.indexOf('='));
            String value = arg.substring(arg.indexOf('=') + 1);
            arguments.add(new Argument(key, value));
        }
        return arguments;
    }

    private static String[] getArgsFromString(String s) {
        return s.substring(1, s.indexOf(']')).split("\\s*,\\s*");
    }
}
