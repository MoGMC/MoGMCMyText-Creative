package NomarTheHero;

import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BarMessages implements Runnable {

	Random rand = new Random();

	public String green = ChatColor.BOLD + "" + ChatColor.DARK_GREEN + "";
	public String gold = ChatColor.BOLD + "" + ChatColor.GOLD + "";

	public String[] messagesCreative = {//
	green + "The Survival Server IP: " + gold + "Life.MonkeyGamesMC.com",//
			gold + "Donating " + green + "keeps the server alive! Type " + gold + "/shop!",//
			green + "To turn chat sounds off type " + gold + "/sounds off",//
			green + "Don't forget to " + gold + "/vote " + green + "for WorldEdit!",//
			green + "Website:  " + gold + "http://MonkeyGamesMC.com"//
	};

	@Override
	public void run() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {

			// change for seperate servers
			BarAPI.setMessage(p, messagesCreative[rand.nextInt(messagesCreative.length)], 10);

			if (SoundsCommand.soundEnabled(p.getName())) {
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 5, 1);

			}

		}

	}

}
