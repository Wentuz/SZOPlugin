package wentuziak.szoplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;




public class InteractionListener implements Listener{


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        boolean isPerishable = false;
  
        //System.out.println("RIGHT CLICKED: " + itemInHand);
        //System.out.println("LOOK DIRECTION: " + lookDirection);

        //
        //      Usable items
        //

        if (itemInHand.getType() == Material.FIRE_CHARGE) {
            givePotionEffect(player, "DAMAGE_RESISTANCE", 20, 3);
            player.getWorld().createExplosion(player.getLocation(), 4F, false, false);

            isPerishable = true;
        }

        if (itemInHand.getType() == Material.MAGMA_CREAM) {
            givePotionEffect(player, "FIRE_RESISTANCE", 400, 0);
            givePotionEffect(player, "POISON", 200, 1);

            isPerishable = true;
        }


        //
        //      Remove one time use item form player
        //

        if (itemInHand.getAmount() > 1 && isPerishable == true) {
            itemInHand.setAmount(itemInHand.getAmount() - 1);
        } else if (isPerishable == true) {
            player.getInventory().removeItem(itemInHand);
        }
    }


    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event)
    {
        Player player = event.getPlayer();
        ItemStack consumedItem = event.getItem();

        //System.out.println("Consumed item: " + consumedItem);

        if (consumedItem.getType() == Material.GLOW_BERRIES) {
            givePotionEffect(player, "GLOWING", 400, 0);
            givePotionEffect(player, "NIGHT_VISION", 400, 0);
        }

    }



    public void givePotionEffect(Player player,String effect,int duration,int amplifier)
    {
        PotionEffectType typeOfEffect = PotionEffectType.getByName(effect);
        player.addPotionEffect(new PotionEffect(typeOfEffect , duration, amplifier));
    }

}
