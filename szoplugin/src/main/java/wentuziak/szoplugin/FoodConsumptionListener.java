package wentuziak.szoplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class FoodConsumptionListener {
    
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event)
    {
        ItemStack consumedItem = event.getItem();

        System.out.println("Consumed item: " + consumedItem);
    }
}
