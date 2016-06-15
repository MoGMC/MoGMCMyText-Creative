package com.normarthehero.plugin.mytextcreative;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatWatcher implements Listener {

		private final float IPERCENT = .5f;

		private static HashMap<String, String> playerChat = new HashMap<String, String>();
		private static HashMap<String, Long> chatCooldown = new HashMap<String, Long>();

		private String noSpamStarter = ChatColor.BLUE + "[" + ChatColor.LIGHT_PURPLE + "NoSpam" + ChatColor.BLUE + "] " + ChatColor.GOLD;

		@EventHandler
		public void onChat(AsyncPlayerChatEvent e) {

			// ignore chat rules if person has permission
			if (e.getPlayer().hasPermission("mogmc.spam")) {
					return;

			}

			final String message = e.getMessage();

			String pName = e.getPlayer().getName();

			final long time = System.currentTimeMillis();

			if (chatCooldown.containsKey(pName)) {

					int waitTime = 1000;

					final int onlinePlayers = Bukkit.getOnlinePlayers().size();

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

		public void removePlayer(String name) {
			chatCooldown.remove(name);
			playerChat.remove(name);

		}

}
