package NomarTheHero;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildEvent implements Listener {

	public BuildEvent() {

	}

	@EventHandler
	public void onBuild(BlockPlaceEvent e) {

		String worldName = e.getPlayer().getLocation().getWorld().getName();

		if (worldName.equalsIgnoreCase("vip")) {
			if (!e.getPlayer().hasPermission("group.vip")) {
				e.setCancelled(true);
				return;

			}
		}

		if (worldName.equalsIgnoreCase("hero")) {
			if (!e.getPlayer().hasPermission("group.hero")) {
				e.setCancelled(true);

			}
		}

	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {

		String worldName = e.getPlayer().getLocation().getWorld().getName();

		if (worldName.equalsIgnoreCase("vip")) {
			if (!e.getPlayer().hasPermission("group.vip")) {
				e.setCancelled(true);
				return;

			}
		}

		if (worldName.equalsIgnoreCase("hero")) {
			if (!e.getPlayer().hasPermission("group.hero")) {
				e.setCancelled(true);

			}
		}

	}
}
