package com.normarthehero.plugin.mytextcreative;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatWatcher implements Listener {

	private final float IPERCENT = .5f;

	private static HashMap<String, String> playerChat = new HashMap<String, String>();
	private static HashMap<String, Long> chatCooldown = new HashMap<String, Long>();

	private String noSpamStarter = ChatColor.BLUE + "[" + ChatColor.LIGHT_PURPLE + "NoSpam" + ChatColor.BLUE + "] " + ChatColor.GOLD;

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {

		if (e.getPlayer().hasPermission("mogmc.spam")) {
			SoundsCommand.playSound(Sound.ITEM_PICKUP);
			return;

		}

		final String message = e.getMessage();

		String pName = e.getPlayer().getName();

		final long time = System.currentTimeMillis();

		if (chatCooldown.containsKey(pName)) {

			int waitTime = 1000;

			final int onlinePlayers = Bukkit.getOnlinePlayers().length;

			if (onlinePlayers > 40) {
				waitTime = 2000;

			} else if (onlinePlayers > 20) {
				waitTime = 1500;

			}

			if (time - chatCooldown.get(pName) < waitTime) {
				e.getPlayer().sendMessage(noSpamStarter + "Please wait at least " + ChatColor.AQUA + waitTime / 1000.0 + ChatColor.GOLD + " second(s) between messages!");
				e.setCancelled(true);
				return;

			}

		}

		chatCooldown.put(pName, time);

		if (playerChat.containsKey(pName)) {
			if (playerChat.get(pName).equalsIgnoreCase(message)) {
				e.getPlayer().sendMessage(noSpamStarter + "Please do not say the same message twice!");
				e.setCancelled(true);

				return;

			}

		}

		playerChat.put(pName, message);

		int caps = getCaps(message);

		if (message.length() > 6 && caps > 0) {
			if ((float) caps / message.length() > IPERCENT) {
				e.setMessage(message.toLowerCase());
				e.getPlayer().sendMessage(noSpamStarter + "Please do not use too many caps!");

			}

		}

		SoundsCommand.playSound(Sound.ITEM_PICKUP);

	}

	private int getCaps(String message) {
		int caps = 0;

		// counts how many caps
		for (int i = 0; i < message.length(); i++) {
			if (Character.isUpperCase(message.charAt(i))) {
				caps++;

			}
		}

		return caps;

	}

	@EventHandler
	public void commandEvent(PlayerCommandPreprocessEvent e) {

		final String[] msg = e.getMessage().split(" ");

		// if the person does "/msg bob", it won't execute.

		if (msg.length > 2) {

			final String cmd = msg[0];

			if (cmd.equalsIgnoreCase("/msg") || cmd.equalsIgnoreCase("/m") || cmd.equalsIgnoreCase("/tell") || cmd.equalsIgnoreCase("/t") || cmd.equalsIgnoreCase("/r")) {

				@SuppressWarnings("deprecation")
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
		chatCooldown.remove(name);
		playerChat.remove(name);

	}

}
