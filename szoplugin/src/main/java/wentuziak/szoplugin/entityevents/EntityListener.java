package wentuziak.szoplugin.entityevents;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;
import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.customcrafting.CreateCustomItem;
import wentuziak.szoplugin.customcrafting.CustomRecipes;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.races.RaceEffects;
import wentuziak.szoplugin.races.UpdateAttributes;


public class EntityListener implements Listener {


    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event){   
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        ItemStack itemOffHand = player.getInventory().getItemInOffHand();


        if (player.getPersistentDataContainer().has(Keys.RACE_ELF)
        && (itemInHand.getType().isEdible() || itemOffHand.getType().isEdible())) {
            RaceEffects.elfFeedEffect((LivingEntity) entity);
            if (LogicHolder.critRoll(10)) {
                entity.getWorld().dropItemNaturally(entity.getLocation(), CreateCustomItem.createSoulEssence());
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomRecipes.getRecipeKeys().forEach(player::discoverRecipe);
        reloadRace(player);
    }

    public static void reloadRace(Player player){
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        NamespacedKey[] raceKeys = Keys.getRaceKeys();

        for (NamespacedKey key : raceKeys) {
            if (dataContainer.has(key, PersistentDataType.BOOLEAN)) {
                String keyString = "RACE_" + key.getKey().toUpperCase();
                player.sendMessage(ChatColor.GREEN + keyString + " Loaded");
                UpdateAttributes.attributeManager(player, false, keyString);
            }
        }
    }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            
            //
            //      Turn off Natural Regen for Celestial's and Fossils
            //
            if ((player.getPersistentDataContainer().has(Keys.RACE_CELESTIAL) || player.getPersistentDataContainer().has(Keys.RACE_FOSSIL)) && 
            (event.getRegainReason() == RegainReason.REGEN || event.getRegainReason() == RegainReason.SATIATED)) {
                event.setCancelled(true);
            }
        }
    }   
}
