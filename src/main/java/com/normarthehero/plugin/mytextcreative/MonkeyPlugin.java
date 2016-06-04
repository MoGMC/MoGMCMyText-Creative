package com.normarthehero.plugin.mytextcreative;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MonkeyPlugin extends JavaPlugin implements Listener {

	ChatWatcher chatWatcher;

	@Override
	public void onEnable() {

		SoundsCommand sounds = new SoundsCommand();

		chatWatcher = new ChatWatcher();

		// register event listeners
		getServer().getPluginManager().registerEvents(chatWatcher, this);
		getServer().getPluginManager().registerEvents(sounds, this);
		getServer().getPluginManager().registerEvents(this, this);

		// sets up commands
		getCommand("sounds").setExecutor(sounds);
		getCommand("join").setExecutor(new JoinCommand());
		getCommand("shop").setExecutor(new ShopCommand());
		getCommand("website").setExecutor(new WebsiteCommand());
		getCommand("member").setExecutor(new MemberCommand());
		getCommand("faq").setExecutor(new FAQCommand());

		// save config
		this.saveDefaultConfig();

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
