package wentuziak.szoplugin;

import org.bukkit.entity.Cow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event)
    {
        if (event.getRightClicked() instanceof Cow) {
            Cow cow = (Cow) event.getRightClicked();

            cow.getWorld().createExplosion(cow.getLocation(), 2.5F);
        }
    }
}
