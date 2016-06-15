package com.normarthehero.plugin.mytextcreative;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class FAQCommand extends InfoCommand {

	FancyMessage faq = new FancyMessage(" Have any questions?\n Click ").color(ChatColor.GREEN)
			.then("[here]").color(ChatColor.AQUA).link("http://monkeygamesmc.com/help").color(ChatColor.AQUA).tooltip("get help!")
			.then(" to read the rules, FAQs, and more.").color(ChatColor.GREEN);

	@Override
	public void sendInfo(CommandSender sender) {
		faq.send(sender);

	}

}
