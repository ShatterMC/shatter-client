package maow.shatter.client;

import maow.ncycloapi.Client;
import maow.shatter.client.ncyclo.BeginReceiver;
import maow.shatter.client.ncyclo.CommandReceiver;
import maow.shatter.client.util.LogUtil;
import maow.shatter.client.util.StringParseUtil;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;

public class Shatter implements ModInitializer {
	private static final Client NCYCLO_CLIENT = Client.getInstance();

	@Override
	public void onInitialize() {
		new Thread(() -> {
			NCYCLO_CLIENT.addReceiver(new BeginReceiver());
			NCYCLO_CLIENT.start();
		}, "ncyclo");
	}

	public static void startAwaitingCommands() {
		LogUtil.log(Level.INFO, "Shatter initialized, log enabled.");
		NCYCLO_CLIENT.addReceiver(new CommandReceiver());
	}
}
