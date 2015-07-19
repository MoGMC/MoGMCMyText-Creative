package com.normarthehero.plugin.mytextcreative;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class JoinCommand extends InfoCommand {

	private String boldWhite = ChatColor.WHITE.toString() + ChatColor.BOLD.toString();
	private String boldGreen = ChatColor.GREEN.toString() + ChatColor.BOLD.toString();

	FancyMessage plotme = new FancyMessage(">> ").style(ChatColor.BOLD).color(ChatColor.GOLD)
			.then("To start building, type or click ").style(ChatColor.BOLD).color(ChatColor.DARK_AQUA)
			.then("/plotme auto").command("/plotme auto").tooltip("gives you a plot").style(ChatColor.BOLD).color(ChatColor.AQUA)
			.then("!").style(ChatColor.BOLD).color(ChatColor.DARK_AQUA);

	FancyMessage donate = new FancyMessage(">> ").style(ChatColor.BOLD).color(ChatColor.GOLD)
			.then(" Donate: ").style(ChatColor.BOLD).color(ChatColor.GREEN)
			.then("/shop").command("/shop").tooltip("lists info about how you can get ranks").style(ChatColor.BOLD).color(ChatColor.AQUA);

	FancyMessage member = new FancyMessage(">> ").style(ChatColor.BOLD).color(ChatColor.GOLD)
			.then(" Member: ").style(ChatColor.BOLD).color(ChatColor.GREEN)
			.then("/member").command("/member").tooltip("become a member").style(ChatColor.BOLD).color(ChatColor.AQUA);

	FancyMessage faq = new FancyMessage(">> ").style(ChatColor.BOLD).color(ChatColor.GOLD)
			.then(" Info, ranks, minigames etc: ").style(ChatColor.BOLD).color(ChatColor.DARK_AQUA)
			.then("/faq").link("http://monkeygamesmc.com/help").style(ChatColor.BOLD).color(ChatColor.AQUA).tooltip("frequently asked questions");

	FancyMessage vote = new FancyMessage(">> ").style(ChatColor.BOLD).color(ChatColor.GOLD)
			.then(" Vote for 30 minutes WorldEdit: ").style(ChatColor.BOLD).color(ChatColor.GREEN)
			.then("/vote").command("/vote").style(ChatColor.BOLD).color(ChatColor.AQUA).tooltip("vote to get worldedit time!");

	FancyMessage website = new FancyMessage(" http://www.monkeygamesmc.com/").link("http://www.monkeygamesmc.com/").tooltip("stay updated!").style(ChatColor.BOLD).color(ChatColor.GREEN);

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
