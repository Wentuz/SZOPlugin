package wentuziak.szoplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;


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
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CustomRecipes.getRecipeKeys().forEach(player::discoverRecipe);

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        NamespacedKey[] raceKeys = Keys.getRaceKeys();

        for (NamespacedKey key : raceKeys) {
            if (dataContainer.has(key, PersistentDataType.BOOLEAN)) {
                String keyString = "RACE_" + key.getKey().toUpperCase();
                UpdateAttributes.attributeManager(player, false, keyString);
                player.sendMessage(ChatColor.GREEN + key.getKey().toUpperCase() + " loaded.");
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
    
    @EventHandler
    public void onArrowLand(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();

            PersistentDataContainer container = arrow.getPersistentDataContainer();
            String value = null;

            for (NamespacedKey key : container.getKeys()) {
                if (container.has(key, PersistentDataType.STRING)) {
                    value = container.get(key, PersistentDataType.STRING);
                }
            }
            
            if (event.getHitEntity() != null) {
                LivingEntity target = (LivingEntity) event.getHitEntity();
                if (value == "antiGravArrow") {
                    Weapons.gravityBowEffect(target);
                }
                if (value == "ratArrow") {
                    Weapons.ratBowEffect(target.getLocation());
                }
            }else{
                Location hitLocation = event.getHitBlock().getLocation();
                if (value == "ratArrow") {
                    Weapons.ratBowEffect(hitLocation);
                }
            }
        }
        if (event.getEntity() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getEntity();

            PersistentDataContainer container = snowball.getPersistentDataContainer();
            String value = null;

            for (NamespacedKey key : container.getKeys()) {
                if (container.has(key, PersistentDataType.STRING)) {
                    value = container.get(key, PersistentDataType.STRING);
                }
            }
            
            if (event.getHitEntity() != null) {
                LivingEntity target = (LivingEntity) event.getHitEntity();
                if (value == "spiritLeech") {
                    MagicItems.spiritLeechEffect(target);
                }else if (value == "spiderYeet") {
                    MagicItems.spiderYeetEffect(target.getLocation());
                }else if (value == "grenade") {
                    Weapons.grenadeEffect(target.getLocation());
                }else if (value == "smokeBomb") {
                    Weapons.smokeEffect(target.getLocation());
                }
            }else{
                Location hitLocation = event.getHitBlock().getLocation();
                if (value == "spiderYeet") {
                    MagicItems.spiderYeetEffect(hitLocation);
                }else if (value == "grenade") {
                    Weapons.grenadeEffect(hitLocation);
                }else if (value == "smokeBomb") {
                    Weapons.smokeEffect(hitLocation);
                }
            }
        }
    }
}
