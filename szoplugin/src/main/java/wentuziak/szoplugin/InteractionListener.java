package wentuziak.szoplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;




public class InteractionListener implements Listener{


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        //Vector lookDirection = player.getLocation().getDirection();
  
        System.out.println("RIGHT CLICKED: " + itemInHand);
        //System.out.println("LOOK DIRECTION: " + lookDirection);


        if (itemInHand.getType() == Material.FIRE_CHARGE) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 2));
            player.getWorld().createExplosion(player.getLocation(), 4F, false, false);

            if (itemInHand.getAmount() > 1) {
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            } else {
                player.getInventory().removeItem(itemInHand);
            }
        }
    }

}
