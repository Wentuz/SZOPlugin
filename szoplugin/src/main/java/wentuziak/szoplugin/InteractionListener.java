package wentuziak.szoplugin;


import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;




public class InteractionListener implements Listener{


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        boolean isPerishable = false;

        //PersistentDataContainer entityContainer = entity.getPersistentDataContainer();
        //PersistentDataContainer playerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();

  
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

            player.playSound(player.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 10, 10);
            isPerishable = true;
        }

        if (itemInHand.getType() == Material.PHANTOM_MEMBRANE) {
            givePotionEffect(player, "SLOW_FALLING", 200, 0);
           
            player.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_HURT, 10, 10);
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

    
        //
        //      Food effects
        //
 

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


        //
        //      On hit effects
        //


    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event)
    {   
        int critChance = 0;

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ItemStack itemInHand = player.getInventory().getItemInMainHand();
            org.bukkit.entity.Entity hitEntity = event.getEntity();
            PersistentDataContainer playerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();
            


            if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BYTE)) {
                int chanceForCrit = 33;
                if (critRoll(chanceForCrit)) {
                    hitEntity.getWorld().createExplosion(hitEntity.getLocation(), 2, false, false);
                }
            }
            

            //player.sendMessage("You hit an entity: " + hitEntity + itemInHand);
        }
    }

    public void givePotionEffect(Player player,String effect,int duration,int amplifier)
    {
        PotionEffectType typeOfEffect = PotionEffectType.getByName(effect);
        player.addPotionEffect(new PotionEffect(typeOfEffect , duration, amplifier));
    }

    public boolean critRoll(int critChance)
    {   
        int isCrit = (int)(Math.random() * 100 + 1);
        System.out.println(isCrit);
        if (critChance <= isCrit) {
            return false;
        }   else{
            return true;
        }
        
    }

    
}
