package com.normarthehero.plugin.mytextcreative;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.monkeygamesmc.plugin.playerdata.PlayerDataPlugin;

import mkremins.fanciful.FancyMessage;

public class MonkeyPlugin extends JavaPlugin implements Listener {

		ChatWatcher chatWatcher;

		PlayerDataPlugin pData;

		@Override
		public void onEnable() {

			saveDefaultConfig();

			pData = Bukkit.getServer().getServicesManager().load(PlayerDataPlugin.class);

			SoundsCommand sounds = new SoundsCommand(pData);

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

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

			if (command.getName().equalsIgnoreCase("togglejoinmessage")) {

					if (!(sender instanceof Player)) {
						return true;

					}

					UUID uuid = ((Player) sender).getUniqueId();

					if (pData.getPlayerData(uuid).isSet("joinmessagedisabled")) {

						pData.unsetData(uuid, "joinmessagedisabled");
						sender.sendMessage(ChatColor.YELLOW + "Enabled join message!");

					} else {

						pData.setData(uuid, "joinmessagedisabled", "true");
						sender.sendMessage(ChatColor.YELLOW + "Disabled join message.");

					}

					return true;

			}

			return false;

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

			// load if they had sound enabled or not
			if (pData.getPlayerData(p.getUniqueId()).isSet("joinmessagedisabled")) {
					return;

			}

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
