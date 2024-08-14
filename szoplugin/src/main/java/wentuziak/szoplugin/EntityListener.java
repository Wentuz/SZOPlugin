package wentuziak.szoplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.PlayerDeathEvent;
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
        reloadRace(player);
    }

    public static void reloadRace(Player player){
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        NamespacedKey[] raceKeys = Keys.getRaceKeys();

        for (NamespacedKey key : raceKeys) {
            if (dataContainer.has(key, PersistentDataType.BOOLEAN)) {
                String keyString = "RACE_" + key.getKey().toUpperCase();
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
                if (value == "bouncyArrow") {
                    Weapons.bouncyCrossbowTargetEffect(arrow, target);
                }
            }else{
                Location hitLocation = event.getHitBlock().getLocation();
                if (value == "ratArrow") {
                    Weapons.ratBowEffect(hitLocation);
                }
                if (value == "bouncyArrow") {
                    Weapons.bouncyCrossbowGroundEffect(arrow, event.getHitBlockFace().getDirection());
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
                }else if (value == "throwingFirework") {
                    Weapons.fireworkEffect(target.getLocation());
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
                else if (value == "throwingFirework") {
                    Weapons.fireworkEffect(hitLocation);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        Entity killedEntity = event.getEntity();
        if (killer != null) {
            ItemStack itemInMainHand = killer.getInventory().getItemInMainHand();
            ItemStack itemInOffHand = killer.getInventory().getItemInOffHand();
            int luckLvl = itemInMainHand.getEnchantmentLevel(Enchantment.LOOTING);
            if (itemInOffHand.hasItemMeta()) {
                PersistentDataContainer playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
                if (playerContainer.has(Keys.CUSTOM_LUCKY_CLOCK, PersistentDataType.BYTE)) {
                    if (LogicHolder.critRoll((luckLvl))) {
                        LogicHolder.rollTreasure(luckLvl, killedEntity.getLocation(), "Mobs");
                    }
                }
            }
            if (killer.getPersistentDataContainer().has(Keys.RACE_FOSSIL)) {
                if (LogicHolder.critRoll((luckLvl + 1))) {
                    LogicHolder.rollTreasure(luckLvl + 1, killedEntity.getLocation(), "Mobs");
                }
            }
            if (killedEntity.getType() == EntityType.COD || 
                killedEntity.getType() == EntityType.TROPICAL_FISH ||
                killedEntity.getType() == EntityType.SALMON ||               
                killer.getPersistentDataContainer().has(Keys.RACE_MEWCHANT)) {
                    if (LogicHolder.critRoll(((luckLvl + 1) * 3))) {
                        killedEntity.getLocation().getWorld().dropItemNaturally(killedEntity.getLocation(), CreateCustomItem.createSoulEssence());
                    }
            }
            

            if (killedEntity.getType() == EntityType.WITHER ||
                killedEntity.getType() == EntityType.ENDER_DRAGON) {
                    Location killedEntityLocation = killedEntity.getLocation();
                    for(int i = 0; i < 4; i++){
                        if (LogicHolder.critRoll((luckLvl + 1) * 15)) {
                            killedEntity.getWorld().dropItemNaturally(killedEntityLocation, CreateCustomItem.createSoulFragment());
                        }
                    }
                }
            if ((killedEntity.getType() == EntityType.WARDEN ||
                killedEntity.getType() == EntityType.ELDER_GUARDIAN) &&
                killedEntity.getType() != EntityType.GUARDIAN) {
                    Location killedEntityLocation = killedEntity.getLocation();
                    for(int i = 0; i < 6; i++){
                        if (LogicHolder.critRoll((luckLvl + 1) * 15)) {
                            killedEntity.getWorld().dropItemNaturally(killedEntityLocation, CreateCustomItem.createSoulEssence());
                        }
                    }
                    if (LogicHolder.critRoll((luckLvl + 1) * 5)) {
                        killedEntity.getWorld().dropItemNaturally(killedEntityLocation, CreateCustomItem.createSoulFragment());
                    }
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            ItemStack itemOnChest = player.getInventory().getChestplate();
    
            if (itemOnChest != null && itemOnChest != null) {
    
                if (itemOnChest.hasItemMeta()) {
                    PersistentDataContainer playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
                    if (playerContainer.has(Keys.CUSTOM_GUARDING_VEST, PersistentDataType.BYTE)) {
                        Armour.guardingVestEffect(player, event);
                    }
                }
            }
        }
    }   
}
