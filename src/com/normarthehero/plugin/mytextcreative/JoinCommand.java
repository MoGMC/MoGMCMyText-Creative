package com.normarthehero.plugin.mytextcreative;

import org.bukkit.command.CommandSender;

import mkremins.fanciful.FancyMessage;

public class JoinCommand extends InfoCommand {

		FancyMessage message;

		public JoinCommand(FancyMessage message) {
			this.message = message;

		}

		FancyMessage plotme = new FancyMessage("§6§l>> §3§lTo start building, type or click ").then("§b§l/plotme auto").command("/plotme auto").tooltip("gives you a plot!");

		FancyMessage faq = new FancyMessage("§6§l>> §3§lInfo, ranks, minigames, etc: ").then("§b§l/faq").command("/faq").tooltip("frequently asked questions and more!");

		FancyMessage donate = new FancyMessage("§6§l>> §a§lDonate: ").then("§b§l/shop").command("/shop").tooltip("how you can get ranks and other goodies!");

		FancyMessage member = new FancyMessage("§6§l>> §a§lMember: ").then("§b§l/member").command("/member").tooltip("become a member!");

		FancyMessage vote = new FancyMessage("§6§l>> §a§lVote for 30 minutes of WorldEdit: ").then("§b§l/vote").command("/vote").tooltip("vote to get worldedit time!");

		FancyMessage website = new FancyMessage("§a§l Website: ").then("§b§lwww.monkeygamesmc.com").link("http://www.monkeygamesmc.com/").tooltip("stay updated!");

		@Override
		public void sendInfo(CommandSender sender) {

			message.send(sender);

			// sender.sendMessage(""); sender.sendMessage("§l Welcome to MonkeyCraft Creative!"); sender.sendMessage("§l Survival IP: life.monkeygamesmc.com"); sender.sendMessage(""); plotme.send(sender); faq.send(sender); donate.send(sender); member.send(sender); vote.send(sender); sender.sendMessage(""); sender.sendMessage("§a§l To stay updated, visit the homepage regulary!"); website.send(sender); sender.sendMessage("");

		}

}
