package com.normarthehero.plugin.mytextcreative;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MonkeyPlugin extends JavaPlugin {

	private static MonkeyPlugin staticPlugin;

	private String goldLine = ChatColor.GOLD + "---------------------------------------------";
	private String red = ChatColor.RED.toString();

	public void onEnable() {

		// defines static plugin for access in other classes
		staticPlugin = this;

		// register event listeners
		getServer().getPluginManager().registerEvents(new ChatWatcher(), this);
		getServer().getPluginManager().registerEvents(new JoinEvent(), this);

		// list of people who have their sound enabled
		Set<String> soundEnabled = new HashSet<String>();

		// add everyone on the server to the sound list
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			soundEnabled.add(player.getName());

		}

		// sets up commands
		getCommand("sounds").setExecutor(new SoundsCommand(soundEnabled));
		getCommand("join").setExecutor(new JoinCommand());
		getCommand("shop").setExecutor(new ShopCommand());
		getCommand("website").setExecutor(new WebsiteCommand());

		// save config
		this.saveDefaultConfig();

	}

	public void onDisable() {

	}

	public void storeVotePlayer(UUID uuid, long timeLeft, String path) {
		getConfig().set(path + uuid, timeLeft);

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
				case "faq":
					p.sendMessage(goldLine);
					p.sendMessage(red + " Frequently Asked Questions:");
					p.sendMessage(red + " http://MonkeyGamesMC.com/help");
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
