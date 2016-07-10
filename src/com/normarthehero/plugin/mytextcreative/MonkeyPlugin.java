package com.normarthehero.plugin.mytextcreative;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mkremins.fanciful.FancyMessage;

public class MonkeyPlugin extends JavaPlugin implements Listener {

		ChatWatcher chatWatcher;

		@Override
		public void onEnable() {

			saveDefaultConfig();

			SoundsCommand sounds = new SoundsCommand();

			chatWatcher = new ChatWatcher();

			// register event listeners
			getServer().getPluginManager().registerEvents(chatWatcher, this);
			getServer().getPluginManager().registerEvents(sounds, this);
			getServer().getPluginManager().registerEvents(this, this);

			// sets up commands
			getCommand("sounds").setExecutor(sounds);
			getCommand("join").setExecutor(new JoinCommand(getMessage(getConfig().getStringList("welcome"))));
			getCommand("shop").setExecutor(new ShopCommand());
			getCommand("website").setExecutor(new WebsiteCommand());
			getCommand("member").setExecutor(new MemberCommand());
			getCommand("faq").setExecutor(new FAQCommand());

			// save config
			this.saveDefaultConfig();

		}

		// takes config strings and turns into fancymessage
		public FancyMessage getMessage(List<String> strings) {

			FancyMessage message = new FancyMessage("");

			for (String string : strings) {

					if (string.startsWith("{")) {
						message.command(string.replaceFirst("\\{", ""));

					} else if (string.startsWith("[")) {
						message.tooltip(string.replaceFirst("\\[", ""));

					} else if (string.startsWith("@")) {
						message.link(string.replaceFirst("@", ""));

					} else {
						message.then(string);

					}

			}

			return message;

		}

		@EventHandler
		public void onJoin(PlayerJoinEvent e) {

			final Player p = e.getPlayer();

			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

					@Override
					public void run() {

						p.performCommand("join");

					}

			}, 25L);

		}

		@EventHandler
		public void onLeave(PlayerQuitEvent e) {
			chatWatcher.removePlayer(e.getPlayer().getName());

		}

}
