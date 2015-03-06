package NomarTheHero;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinEvent implements Listener {

	MonkeyPlugin plugin;
	private int recordPlayers;

	public JoinEvent() {
		plugin = MonkeyPlugin.getPlugin();
		recordPlayers = plugin.getConfig().getInt("recordplayers");

	}

	private String gold = ChatColor.GOLD.toString();
	private String boldWhite = ChatColor.WHITE.toString() + ChatColor.BOLD.toString();
	private String grey = ChatColor.GRAY.toString();
	private String boldGreen = ChatColor.GREEN.toString() + ChatColor.BOLD.toString();

	String prefix = gold + "*" + grey + "-" + gold + "*" + grey + "-" + gold + "*" + grey + "-" + gold + "*" + grey + "-" + gold + "*" + grey + "-" + gold + "*" + grey + "-" + gold + "*" + grey + "-";

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();

		SoundsCommand.enableSound(p.getName());

		SoundsCommand.playSound(Sound.NOTE_PLING);

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {

				p.sendMessage(prefix + prefix + prefix + gold + "*");
				p.sendMessage("");
				p.sendMessage(boldWhite + " Welcome to MonkeyCraft Creative!");
				p.sendMessage(boldWhite + " Survival IP: Life.MonkeyGamesMC.com");
				p.sendMessage("");
				p.sendMessage(boldGreen + " To start building type /plotme auto");
				p.sendMessage(boldGreen + " Donate: /shop");
				p.sendMessage(boldGreen + " Member: /member");
				p.sendMessage(boldGreen + " Info, ranks, minigames etc: /faq");
				p.sendMessage(boldGreen + " Vote for 30 minutes WorldEdit: /vote");
				p.sendMessage("");
				p.sendMessage(boldGreen + " To stay updated visit the homepage regulary!");
				p.sendMessage("");
				p.sendMessage(prefix + prefix + prefix + gold + "*");

			}
		}, 40L);

		if (Bukkit.getOnlinePlayers().length > recordPlayers) {

			recordPlayers = Bukkit.getOnlinePlayers().length;

			plugin.getConfig().set("recordplayers", recordPlayers);

			plugin.saveConfig();

		}

	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		SoundsCommand.playSound(Sound.NOTE_BASS);
		SoundsCommand.disableSound(e.getPlayer().getName());

		ChatWatcher.removePlayer(e.getPlayer().getName());

	}

}
