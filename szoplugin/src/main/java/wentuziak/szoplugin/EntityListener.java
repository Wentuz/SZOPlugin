package wentuziak.szoplugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;


public class EntityListener implements Listener {


    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event){
        if (event.getHand() == EquipmentSlot.OFF_HAND) {
             return;
        }
        
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();


        if (entity instanceof Cow && entity.hasMetadata("SzoPlugin") == true && itemInHand.getType() == Material.BUCKET) {
            Cow cowClicked = (Cow) event.getRightClicked();
            ItemStack itemToDrop = new ItemStack(Material.GUNPOWDER);
            int amountToDrop = (int)(Math.random() * 10 + 1);
            itemToDrop.setAmount(amountToDrop);

            cowClicked.getWorld().createExplosion(cowClicked.getLocation(), 2.5F);
            cowClicked.getWorld().dropItem(cowClicked.getLocation(), itemToDrop);

        }

        if (event.getRightClicked().getType() == EntityType.CHICKEN) {
            LivingEntity chickenClicked = (LivingEntity) event.getRightClicked();
            ItemStack itemToDrop = new ItemStack(Material.FEATHER);
            double damageAmount = 2.0;

            chickenClicked.damage(damageAmount);
            if (!chickenClicked.isDead()) {
                chickenClicked.getWorld().dropItem(chickenClicked.getLocation(), itemToDrop);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomRecipes.getRecipeKeys().forEach(player::discoverRecipe);

    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            
            //
            //      Turn off Natural Regen for Celestial's
            //
            if (player.getPersistentDataContainer().has(Keys.RACE_CELESTIAL) && event.getRegainReason() == RegainReason.REGEN) {
                event.setCancelled(true);
            }
        }
    }
}
