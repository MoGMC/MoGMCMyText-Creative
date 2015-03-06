package NomarTheHero;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoundsCommand implements CommandExecutor {

	private static Set<String> soundEnabled;

	public SoundsCommand(Set<String> enabled) {
		soundEnabled = enabled;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("on")) {
				if (soundEnabled.contains(sender.getName())) {
					sender.sendMessage(ChatColor.GOLD + "Chat sounds are already enabled!");

				} else {
					soundEnabled.add(sender.getName());
					sender.sendMessage(ChatColor.GOLD + "Enabled chat sounds!");

				}
			} else if (args[0].equalsIgnoreCase("off")) {
				if (!soundEnabled.contains(sender.getName())) {
					sender.sendMessage(ChatColor.GOLD + "Chat sounds are already disabled!");

				} else {
					soundEnabled.remove(sender.getName());
					sender.sendMessage(ChatColor.GOLD + "Disabled chat sounds!");

				}
			} else {
				sender.sendMessage(ChatColor.GOLD + "Please specify if you want to turn sounds on/off!");

			}

			return true;

		}

		if (soundEnabled.contains(sender.getName())) {
			soundEnabled.remove(sender.getName());
			sender.sendMessage(ChatColor.GOLD + "Disabled chat sounds!");
			return true;

		}

		soundEnabled.add(sender.getName());
		sender.sendMessage(ChatColor.GOLD + "Enabled chat sounds!");

		return true;
	}

	public static boolean soundEnabled(String name) {
		return soundEnabled.contains(name);

	}

	public static void enableSound(String name) {
		soundEnabled.add(name);

	}

	public static void disableSound(String name) {
		if (soundEnabled.contains(name)) {
			soundEnabled.remove(name);

		}
	}

	public static void playSound(Sound s) {
		// loops thru players, if they have their sound on, play the sound.
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (soundEnabled(player.getName())) {
				player.playSound(player.getLocation(), s, 1, 1);

			}
		}
	}

}
