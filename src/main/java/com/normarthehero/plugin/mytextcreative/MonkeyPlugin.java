package com.normarthehero.plugin.mytextcreative;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MonkeyPlugin extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		// register event listeners
		getServer().getPluginManager().registerEvents(new ChatWatcher(), this);
		getServer().getPluginManager().registerEvents(this, this);

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
		getCommand("member").setExecutor(new MemberCommand());
		getCommand("faq").setExecutor(new FAQCommand());

		// save config
		this.saveDefaultConfig();

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		final Player p = e.getPlayer();

		SoundsCommand.playSound(Sound.BLOCK_NOTE_PLING);

		SoundsCommand.enableSound(p.getName());

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {

				p.performCommand("join");

			}

		}, 25L);

	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		SoundsCommand.disableSound(e.getPlayer().getName());
		SoundsCommand.playSound(Sound.BLOCK_NOTE_BASS);
		ChatWatcher.removePlayer(e.getPlayer().getName());

	}

}
