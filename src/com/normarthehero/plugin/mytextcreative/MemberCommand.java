package com.normarthehero.plugin.mytextcreative;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MemberCommand extends InfoCommand {

	FancyMessage member = new FancyMessage(" Want some nifty commands in addition to a [Member] tag?\n Click ").color(ChatColor.GREEN)
			.then("[here]").color(ChatColor.AQUA).tooltip("you're one click away from the journey of becoming a member!").link("http://monkeygamesmc.com/member")
			.then(" to become a member!").color(ChatColor.GREEN);

	@Override
	public void sendInfo(CommandSender sender) {
		member.send(sender);

	}

}
