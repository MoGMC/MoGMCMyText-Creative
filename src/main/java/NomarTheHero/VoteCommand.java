package NomarTheHero;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {

	private MonkeyPlugin plugin;

	public VoteCommand(MonkeyPlugin instance) {
		plugin = instance;

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (args.length != 2) {
			return true;
		}

		final String name = args[0];

		final Player player = Bukkit.getServer().getPlayer(name);

		if (player == null) {
			Bukkit.getServer().getLogger().info("Could not give tempperm time to player " + name);
			return true;

		}

		UUID uuid = player.getUniqueId();

		String subcmd = args[1].toLowerCase();

		if (subcmd.equals("wetime")) {

			// if player already has voted and is in the 30 min range
			if (MonkeyPlugin.WEvotes.containsKey(uuid)) {

				// gets the existing VT var
				WEVoteTime existing = MonkeyPlugin.WEvotes.get(uuid);

				// cancels the existing VT var so it doesn't run
				existing.cancel();

				// makes new VT
				WEVoteTime newVT = new WEVoteTime(uuid, System.currentTimeMillis(), 36000L + existing.getTicksLeft());

				// removes old VT
				MonkeyPlugin.WEvotes.remove(uuid);

				// adds VT to list
				MonkeyPlugin.WEvotes.put(uuid, newVT);

				// schedules the VT
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, newVT, 36000L + existing.getTicksLeft());

				// sends confirmation
				player.sendMessage(ChatColor.GOLD + "30 minutes have been added onto your remaining WorldEdit time!");

				return true;
			}

			/*
			 * ANNOUNCE TO LE SERVER THAT SUCH AND SUCH HAS VOTED BLA BLA YEY
			 * P.S. make sure to change the 600L to whatever time you want (in
			 * server ticks)
			 */

			// makes new WEVoteTime var
			WEVoteTime weTime = new WEVoteTime(uuid, System.currentTimeMillis(), 36000L);

			// puts in in le list
			MonkeyPlugin.WEvotes.put(uuid, weTime);

			// 36000 ticks in 30 minutes

			// makes le WEVoteTime var run in 30 minutes
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, weTime, 36000L);

			// allows the player to use WE
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "perm player " + name + " set worldedit.*");

			return true;

		} else if (subcmd.equals("votestat")) {

			String we = ChatColor.GOLD + "WE voting stats: " + ChatColor.YELLOW;

			if (MonkeyPlugin.WEvotes.containsKey(uuid)) {

				WEVoteTime wev = MonkeyPlugin.WEvotes.get(uuid);

				we += ("WE time left: (" + wev.getTicksLeft() + "ticks) (" + (wev.getTicksLeft() / 1200.0) + "minutes)");

			} else {

				we += "Has not voted.";

			}

			sender.sendMessage(we);

		}

		return true;
	}
}
