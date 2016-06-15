package com.normarthehero.plugin.mytextcreative;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class InfoCommand implements CommandExecutor {

	private static final String goldLine = ChatColor.GOLD + "---------------------------------------------";

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {

		sender.sendMessage(goldLine);
		sendInfo(sender);
		sender.sendMessage(goldLine);

		return true;

	}

	public abstract void sendInfo(CommandSender sender);

}
