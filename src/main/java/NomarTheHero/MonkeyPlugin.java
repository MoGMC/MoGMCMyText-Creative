package NomarTheHero;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MonkeyPlugin extends JavaPlugin implements Listener {

	// http://pastebin.com/nGW57tnm

	private BarMessages barMessages = new BarMessages();

	public static HashMap<String, WEVoteTime> WEvotes = new HashMap<String, WEVoteTime>();

	private static MonkeyPlugin staticPlugin;

	private String goldLine = ChatColor.GOLD + "---------------------------------------------";
	private String red = ChatColor.RED.toString();

	public void onEnable() {

		// defines static plugin for access in other classes
		staticPlugin = this;

		// register event listeners
		getServer().getPluginManager().registerEvents(new ChatWatcher(), this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);
		getServer().getPluginManager().registerEvents(new BuildEvent(), this);

		// list of people who have their sound enabled
		Set<String> soundEnabled = new HashSet<String>();

		// add everyone on the server to the sound list
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			soundEnabled.add(player.getName());

		}

		// sets up commands
		getCommand("tempperm").setExecutor(new VoteCommand(this));
		getCommand("sounds").setExecutor(new SoundsCommand(soundEnabled));

		// save config
		this.saveDefaultConfig();

		// sets up bar messages
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, barMessages, 0L, 6000L);

		getVotePlayers();

	}

	public void onDisable() {
		for (String pName : WEvotes.keySet()) {
			storeVotePlayer(pName, WEvotes.get(pName).getTicksLeft(), "wevote.");

		}

		this.saveConfig();

	}

	public void storeVotePlayer(String name, long timeLeft, String path) {
		getConfig().set(path + name, timeLeft);

	}

	// restores previous time that players had before a shutdown
	public void getVotePlayers() {

		/* fetch the players from "wevote" section of the config */
		Set<String> wepeoples = getConfig().getConfigurationSection("wevote").getKeys(false);

		// loops through the list
		for (String people : wepeoples) {

			long timeLeft = getConfig().getLong("wevote." + people);

			getConfig().set("wevote." + people, null);

			WEVoteTime TV = new WEVoteTime(people, System.currentTimeMillis(), timeLeft);

			WEvotes.put(people, TV);

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, TV, timeLeft);

		}

		// saves config
		this.saveConfig();

	}

	public boolean onCommand(CommandSender p, Command cmd, String label, String[] args) {

		if (p.hasPermission("MyText.use")) {
			if (args.length == 0) {
				switch (cmd.getName().toLowerCase()) {
				case "ranks":
					p.sendMessage(goldLine);
					p.sendMessage(red + " Info about all ranks can be found here:");
					p.sendMessage(red + " http://monkeygamesmc.com/help");
					p.sendMessage(goldLine);
					return true;
				case "shop":
					p.sendMessage(goldLine);
					p.sendMessage(red + " Vip: €7,99 - Vip+: €14,99 - Hero: €24,99 - Legendary - €39,99");
					p.sendMessage(red + " To see donator ranks and benefits go here:");
					p.sendMessage(red + " http://monkeygamesmc.com/shop");
					p.sendMessage(goldLine);
					return true;
				case "website":
					p.sendMessage(goldLine);
					p.sendMessage(red + " Website:");
					p.sendMessage(red + " http://MonkeyGamesMC.com");
					p.sendMessage(goldLine);
					return true;
				case "faq":
					p.sendMessage(goldLine);
					p.sendMessage(red + " Frequently Asked Questions:");
					p.sendMessage(red + " http://MonkeyGamesMC.com/help");
					p.sendMessage(goldLine);
					return true;
				case "contest":
					p.sendMessage(goldLine);
					p.sendMessage(red + " Info about build contest:");
					p.sendMessage(red + " http://monkeygamesmc.com/forum/m/20531573/viewthread/11143786/-read-on-building-contests");
					p.sendMessage(goldLine);
					return true;
				case "member":
					p.sendMessage(goldLine);
					p.sendMessage(red + " To get Member visit this page:");
					p.sendMessage(red + " http://MonkeyGamesMC.com/Member");
					p.sendMessage(goldLine);
					return true;

				}
			}
		} else {
			p.sendMessage(ChatColor.RED + "You do not have permission to use that command.");

		}
		return false;

	}

	public static MonkeyPlugin getPlugin() {
		return staticPlugin;

	}

}
