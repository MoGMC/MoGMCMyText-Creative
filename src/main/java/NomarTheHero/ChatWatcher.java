package NomarTheHero;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatWatcher implements Listener {

	private final float IPERCENT = .5f;

	private static Map<String, String> playerChat = new HashMap<String, String>();
	private static Map<String, Long> chatCooldown = new HashMap<String, Long>();

	private String noSpamStarter = ChatColor.BLUE + "[" + ChatColor.LIGHT_PURPLE + "NoSpam" + ChatColor.BLUE + "] " + ChatColor.GOLD;

	@EventHandler(priority = EventPriority.NORMAL)
	public void onChat(AsyncPlayerChatEvent e) {

		final String message = e.getMessage();
		final long time = System.currentTimeMillis();

		Player p = e.getPlayer();

		String pName = p.getName();

		if (chatCooldown.containsKey(pName)) {

			int waitTime = 1000;

			final int onlinePlayers = Bukkit.getOnlinePlayers().length;

			if (onlinePlayers > 25) {
				waitTime = 2000;

			} else if (onlinePlayers > 15) {
				waitTime = 1500;

			}

			if (time - chatCooldown.get(pName) < waitTime) {
				p.sendMessage(noSpamStarter + "Please wait at least " + ChatColor.AQUA + waitTime / 1000.0 + ChatColor.GOLD + " second(s) between messages!");
				e.setCancelled(true);
				return;

			}

		}

		chatCooldown.put(pName, time);

		if (playerChat.containsKey(pName)) {
			if (playerChat.get(pName).equalsIgnoreCase(message)) {
				p.sendMessage(noSpamStarter + "Please do not say the same message twice!");
				e.setCancelled(true);
				return;

			}
		}

		playerChat.put(pName, message);

		int caps = 0;

		for (int i = 0; i < message.length(); i++) {
			if (Character.isUpperCase(message.charAt(i))) {
				caps++;

			}
		}

		if (message.length() > 6 && caps > 0) {
			if ((float) caps / message.length() >= IPERCENT) {
				e.setMessage(message.toLowerCase());
				p.sendMessage(noSpamStarter + "Please do not use too many caps!");

			}
		}

		SoundsCommand.playSound(Sound.ITEM_PICKUP);

	}

	@EventHandler
	public void commandEvent(PlayerCommandPreprocessEvent e) {

		final String[] msg = e.getMessage().split(" ");

		if (msg.length > 1) {

			final String cmd = msg[0];

			if (cmd.equalsIgnoreCase("/msg") || cmd.equalsIgnoreCase("/m") || cmd.equalsIgnoreCase("/tell") || cmd.equalsIgnoreCase("/t")) {

				Player p = Bukkit.getPlayer(msg[1]);

				if (p != null) {
					if (SoundsCommand.soundEnabled(p.getName())) {
						p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);

					}

				}

			}

		}
	}

	public static void removePlayer(String name) {
		if (chatCooldown.containsKey(name)) {
			chatCooldown.remove(name);

		}

		if (playerChat.containsKey(name)) {
			playerChat.remove(name);

		}

	}

}
