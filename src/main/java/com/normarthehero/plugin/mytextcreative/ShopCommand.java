package com.normarthehero.plugin.mytextcreative;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ShopCommand extends InfoCommand {

	private static final FancyMessage message01 = new FancyMessage(" Vip: €7,99 - Vip+: €14,99 - Hero: €24,99 - Legendary - €39,99\n To see donator ranks and benefits, click ").color(ChatColor.GREEN)
			.then("[here]").tooltip("the official MoGMC shop").link("http://www.monkeygamesmc.com/shop").color(ChatColor.AQUA)
			.then(".").color(ChatColor.GREEN);

	@Override
	public void sendInfo(CommandSender sender) {
		message01.send(sender);

	}

}
