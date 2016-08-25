package com.normarthehero.plugin.mytextcreative;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.monkeygamesmc.plugin.playerdata.PlayerDataPlugin;

public class SoundsCommand implements CommandExecutor, Listener {

		// since chat happens really frequently, will probably be better to keep a
		// local copy of who to send the sounds to.

		// note: a set only adds an object via add() if it's not present.

		private Set<UUID> soundDisabled;

		PlayerDataPlugin pData;

		public SoundsCommand(PlayerDataPlugin pData) {

			this.pData = pData;

			soundDisabled = new HashSet<UUID>();

		}

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

			if (!(sender instanceof Player)) {
					return false;

			}

			UUID uuid = ((Player) sender).getUniqueId();

			boolean enabled = soundEnabled(uuid);

			// if no args, toggle sound
			if (args.length < 1) {

					if (soundEnabled(uuid)) {

						disableSound(uuid);
						sender.sendMessage(ChatColor.GOLD + "Toggled sounds off!");

					} else {

						enableSound(uuid);
						sender.sendMessage(ChatColor.GOLD + "Toggled sounds on!");

					}

					return true;

			}

			if (args[0].equalsIgnoreCase("on")) {

					if (enabled) {
						sender.sendMessage(ChatColor.GOLD + "Chat sounds are already enabled!");

					} else {
						enableSound(uuid);
						sender.sendMessage(ChatColor.GOLD + "Enabled chat sounds!");

					}

			} else if (args[0].equalsIgnoreCase("off")) {

					if (!enabled) {
						sender.sendMessage(ChatColor.GOLD + "Chat sounds are already disabled!");

					} else {
						disableSound(uuid);
						sender.sendMessage(ChatColor.GOLD + "Disabled chat sounds!");

					}

			} else {
					sender.sendMessage(ChatColor.RED + "Please specify if you want to turn sounds on or off.");

			}

			return true;

		}

		public void playSound(Sound s) {

			// loops thru players, if they have their sound on, play the sound.
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {

					if (soundEnabled(player.getUniqueId())) {

						player.playSound(player.getLocation(), s, 1, 1);

					}

			}

		}

		@EventHandler(priority = EventPriority.HIGHEST)
		public void onChat(AsyncPlayerChatEvent e) {

			if (e.isCancelled()) {
					return;

			}

			playSound(Sound.ENTITY_ITEM_PICKUP);

		}

		@EventHandler(priority = EventPriority.HIGHEST)
		public void onJoin(PlayerJoinEvent e) {

			// load if they had sound enabled or not
			if (pData.getPlayerData(e.getPlayer().getUniqueId()).isSet("sounddisabled")) {
					soundDisabled.add(e.getPlayer().getUniqueId());

			}

			playSound(Sound.BLOCK_NOTE_PLING);

		}

		@EventHandler(priority = EventPriority.HIGHEST)
		public void onLeave(PlayerQuitEvent e) {

			soundDisabled.remove(e.getPlayer().getUniqueId());

			playSound(Sound.BLOCK_NOTE_BASS);

		}

		@EventHandler(priority = EventPriority.HIGHEST)
		public void commandEvent(PlayerCommandPreprocessEvent e) {

			final String[] msg = e.getMessage().split(" ");

			// if the person does "/msg bob", it won't execute.

			if (msg.length > 2) {

					final String cmd = msg[0];

					if (cmd.equalsIgnoreCase("/msg") || cmd.equalsIgnoreCase("/m") || cmd.equalsIgnoreCase("/tell") || cmd.equalsIgnoreCase("/t") || cmd.equalsIgnoreCase("/r")) {

						@SuppressWarnings("deprecation")
						Player p = Bukkit.getPlayer(msg[1]);

						if (p != null) {
								if (soundEnabled(p.getUniqueId())) {
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);

								}

						}

					}

			}

		}

		public boolean soundEnabled(UUID uuid) {
			return !soundDisabled.contains(uuid);

		}

		// only for updating

		public void enableSound(UUID uuid) {
			soundDisabled.remove(uuid);
			pData.unsetData(uuid, "sounddisabled");

		}

		public void disableSound(UUID uuid) {
			soundDisabled.add(uuid);
			pData.setData(uuid, "sounddisabled", "true");

		}

}
