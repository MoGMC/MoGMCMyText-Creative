package com.normarthehero.plugin.mytextcreative;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class JoinCommand extends InfoCommand {

	private String boldWhite = ChatColor.WHITE.toString() + ChatColor.BOLD.toString();
	private String boldGreen = ChatColor.GREEN.toString() + ChatColor.BOLD.toString();

	FancyMessage plotme = new FancyMessage(" To start building, type or click ").style(ChatColor.BOLD).color(ChatColor.GREEN)
			.then("/plotme auto").command("/plotme auto").tooltip("gives you a plot").style(ChatColor.BOLD).color(ChatColor.AQUA)
			.then("!").style(ChatColor.BOLD).color(ChatColor.GREEN);

	FancyMessage donate = new FancyMessage(" Donate: ").style(ChatColor.BOLD).color(ChatColor.GREEN)
			.then("/shop").command("/shop").tooltip("lists info about how you can get ranks").style(ChatColor.BOLD).color(ChatColor.AQUA);

	FancyMessage member = new FancyMessage(" Member: ").style(ChatColor.BOLD).color(ChatColor.GREEN)
			.then("/member").command("/member").tooltip("become a member").style(ChatColor.BOLD).color(ChatColor.AQUA);

	FancyMessage faq = new FancyMessage(" Info, ranks, minigames etc: ").style(ChatColor.BOLD).color(ChatColor.GREEN)
			.then("/faq").link("http://monkeygamesmc.com/forum/m/20531573/viewthread/11615487").style(ChatColor.BOLD).color(ChatColor.AQUA).tooltip("frequently asked questions");

	FancyMessage vote = new FancyMessage(" Vote for 30 minutes WorldEdit: ").style(ChatColor.BOLD).color(ChatColor.GREEN)
			.then("/vote").command("/vote").style(ChatColor.BOLD).color(ChatColor.AQUA).tooltip("vote to get worldedit time!");

	@Override
	public void sendInfo(CommandSender sender) {
		sender.sendMessage("");
		sender.sendMessage(boldWhite + " Welcome to MonkeyCraft Creative!");
		sender.sendMessage(boldWhite + " Survival IP: life.monkeygamesmc.com");
		sender.sendMessage("");
		plotme.send(sender);
		donate.send(sender);
		member.send(sender);
		faq.send(sender);
		vote.send(sender);
		sender.sendMessage("");
		sender.sendMessage(boldGreen + " To stay updated, visit the homepage regulary!");
		sender.sendMessage("");

	}

}
