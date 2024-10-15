package wentuziak.szoplugin.entityevents;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.customcrafting.CreateCustomItem;
import wentuziak.szoplugin.customitems.Armour;
import wentuziak.szoplugin.customitems.CustomTools;
import wentuziak.szoplugin.customitems.MagicItems;
import wentuziak.szoplugin.customitems.Weapons;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.races.RaceEffects;

public class EntityCombat implements Listener{
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
                else if (value == "webTrap") {
                    MagicItems.webTrapEffect(target.getLocation());
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
                else if (value == "webTrap") {
                    MagicItems.webTrapEffect(hitLocation);
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
            //  By RACE
            if (killer.getPersistentDataContainer().has(Keys.RACE_FOSSIL)) {
                if (LogicHolder.critRoll((luckLvl + 1))) {
                    LogicHolder.rollTreasure(luckLvl + 1, killedEntity.getLocation(), "Mobs");
                }
            }
            if (killer.getPersistentDataContainer().has(Keys.RACE_SANGUINITE)) {
                RaceEffects.sanguiniteKillEffect(killer);
            }

            if ((killedEntity.getType() == EntityType.COD || 
                killedEntity.getType() == EntityType.TROPICAL_FISH ||
                killedEntity.getType() == EntityType.SALMON) &&               
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

        if (killedEntity.getType() == EntityType.CREEPER 
        && killedEntity.getPersistentDataContainer().has(Keys.MOB_RIOT, PersistentDataType.BYTE)) {
            Weapons.smokeEffect(killedEntity.getLocation());
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();


            ItemStack itemOnChest = player.getInventory().getChestplate();
    
            if (itemOnChest != null) {
    
                if (itemOnChest.hasItemMeta()) {
                    PersistentDataContainer playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
                    if (playerContainer.has(Keys.CUSTOM_GUARDING_VEST, PersistentDataType.BYTE)) {
                        Armour.guardingVestEffect(player, event);
                    }
                }
            }

            if (player.isBlocking()) {
                ItemStack itemShield = player.getInventory().getItemInOffHand();

                if (itemShield.hasItemMeta()) {
                    PersistentDataContainer playerContainer = itemShield.getItemMeta().getPersistentDataContainer();
                    if (playerContainer.has(Keys.CUSTOM_WIND_BLAST_SHIELD, PersistentDataType.BYTE)) {
                        CustomTools.windBlastShieldEffect(player);
                    }
                    if (playerContainer.has(Keys.CUSTOM_IRON_BREAKER_SHIELD, PersistentDataType.BYTE)) {
                        CustomTools.effectShieldBlock(player, 3);
                    }
                    else if (playerContainer.has(Keys.CUSTOM_BERSERKER_SHIELD, PersistentDataType.BYTE)) {
                        CustomTools.effectShieldBlock(player, 2);
                    }
                }
            }
        }
    }
}
