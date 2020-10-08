package maow.shatter.client;

import maow.ncycloapi.Client;
import maow.shatter.api.CommandRegistry;
import maow.shatter.client.commands.PingCommand;
import maow.shatter.client.ncyclo.BeginReceiver;
import maow.shatter.client.ncyclo.CommandReceiver;
import maow.shatter.client.util.LogUtil;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;

public class Shatter implements ModInitializer {
	private static final Client NCYCLO_CLIENT = Client.getInstance();

	private static final BeginReceiver BEGIN_RECEIVER = new BeginReceiver();
	private static final CommandReceiver COMMAND_RECEIVER = new CommandReceiver();

	@Override
	public void onInitialize() {
		PingCommand ping = new PingCommand();
		CommandRegistry.getInstance().register(ping.getName(), ping);

		new Thread(() -> {
			NCYCLO_CLIENT.addReceiver(BEGIN_RECEIVER);
			NCYCLO_CLIENT.start();
		}, "ncyclo").start();
	}

	public static void startAwaitingCommands() {
		LogUtil.log(Level.INFO, "Shatter initialized, log enabled.");
		NCYCLO_CLIENT.removeReceiver(BEGIN_RECEIVER);
		NCYCLO_CLIENT.addReceiver(COMMAND_RECEIVER);
	}
}
