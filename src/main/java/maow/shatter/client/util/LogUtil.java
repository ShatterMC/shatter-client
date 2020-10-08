package maow.shatter.client.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    private static boolean shouldLog = false;
    private static final Logger logger = LogManager.getLogger("shatter");

    public static void setShouldLog(boolean shouldLog) {
        LogUtil.shouldLog = shouldLog;
    }

    public static void log(Level level, String msg) {
        if (shouldLog) {
            logger.log(level, msg);
        }
    }
}
