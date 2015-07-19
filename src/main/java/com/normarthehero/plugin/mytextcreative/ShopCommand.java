package com.normarthehero.plugin.mytextcreative;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ShopCommand extends InfoCommand {

	private static final String message00 = ChatColor.GOLD + " Vip: €7,99 - Vip+: €14,99 - Hero: €24,99 - Legendary - €39,99";
	private static final FancyMessage message01 = new FancyMessage(" To see donator ranks and benefits, click ").color(ChatColor.RED)
			.then("[here]").tooltip("the official MoGMC shop").link("http://www.monkeygamesmc.com/shop")
			.then(".");

	@Override
	public void sendInfo(CommandSender sender) {
		sender.sendMessage(message00);
		message01.send(sender);

	}

}
