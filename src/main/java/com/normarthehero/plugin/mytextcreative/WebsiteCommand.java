package com.normarthehero.plugin.mytextcreative;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class WebsiteCommand extends InfoCommand {

	private static final FancyMessage message00 = new FancyMessage(" Click ").color(ChatColor.GREEN)
			.then("[here]").tooltip("the official MoGMC website").link("http://www.monkeygamesmc.com/").color(ChatColor.AQUA)
			.then(" to visit our website!").color(ChatColor.GREEN);

	@Override
	public void sendInfo(CommandSender sender) {
		message00.send(sender);

	}

}