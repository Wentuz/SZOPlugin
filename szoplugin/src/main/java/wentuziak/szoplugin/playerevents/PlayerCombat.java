package wentuziak.szoplugin.playerevents;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.customitems.Armour;
import wentuziak.szoplugin.customitems.MagicItems;
import wentuziak.szoplugin.customitems.Weapons;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.races.RaceEffects;

public class PlayerCombat implements Listener{
        
        //
        //      On hit effects
        //
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.THORNS) {
            return;
        }

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            handlePlayerAttack(player, event);
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            handlePlayerDamage(player, event);
        }
    }

    // Method to handle when a player attacks another entity
    private void handlePlayerAttack(Player player, EntityDamageByEntityEvent event) {
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (itemInMainHand == null || itemInMainHand.getType() == Material.AIR) {
            return;
        }

        float attackCooldown = player.getAttackCooldown();
        if (attackCooldown < 1) {
            return;
        }

        LivingEntity hitEntity = (LivingEntity) event.getEntity();
        PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();

        applyRaceEffects(player, hitEntity);
        applySpecialWeapons(playerContainer, itemInMainHand, hitEntity, player);
        applyHeadgearEffects(player, hitEntity);
    }

    // Method to apply race-specific effects when a player attacks
    private void applyRaceEffects(Player player, LivingEntity hitEntity) {
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        if (playerContainer.has(Keys.RACE_WITCH, PersistentDataType.BYTE)) {
            RaceEffects.witchAttackEvent(player, hitEntity);
        }

        if (playerContainer.has(Keys.RACE_CELESTIAL, PersistentDataType.BYTE)) {
            RaceEffects.celestialAttackEvent(player, hitEntity);
        }

        if (playerContainer.has(Keys.RACE_ZEPHYR, PersistentDataType.BYTE) && LogicHolder.critRoll(22)) {
            RaceEffects.zaphyrKnockback(player);
        }
    }

    // Method to apply special weapon effects when a player attacks
    private void applySpecialWeapons(PersistentDataContainer playerContainer, ItemStack itemInMainHand, LivingEntity hitEntity, Player player) {
        int sharpLvl = itemInMainHand.getEnchantmentLevel(Enchantment.SHARPNESS);

        if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BYTE)) {
            Weapons.explosiveSwordEffect(33, hitEntity);
        }
        if (playerContainer.has(Keys.CUSTOM_THUNDER_HAMMER, PersistentDataType.BYTE)) {
            Weapons.thunderHammerEffect(40, hitEntity);
        }
        if (playerContainer.has(Keys.CUSTOM_DAEMON_SWORD, PersistentDataType.BYTE)) {
            Weapons.daemonSwordEffect(40, hitEntity);
        }
        if (playerContainer.has(Keys.CUSTOM_ANGEL_SWORD, PersistentDataType.BYTE)) {
            Weapons.angelSwordEffect(44, player);
        }
        if (playerContainer.has(Keys.CUSTOM_ARMOR_PIERCER, PersistentDataType.BYTE)) {
            LogicHolder.modifyCurrentHeatlhPoints(hitEntity, (double) sharpLvl);
            if (LogicHolder.critRoll(sharpLvl * 10)) {
                Weapons.bleedEffect(hitEntity, 2.0, sharpLvl);
            }
        }
    }

    // Method to apply headgear effects when a player attacks
    private void applyHeadgearEffects(Player player, LivingEntity hitEntity) {
        ItemStack itemOnHead = player.getInventory().getItem(EquipmentSlot.HEAD);
        if (itemOnHead == null || itemOnHead.getType() == Material.AIR || !itemOnHead.hasItemMeta()) {
            return;
        }

        PersistentDataContainer playerContainer = itemOnHead.getItemMeta().getPersistentDataContainer();
        if (playerContainer.has(Keys.CUSTOM_STRIGA_VEIL, PersistentDataType.BYTE)) {
            Armour.strigaVeilEffect(15, player);
        }

        if (playerContainer.has(Keys.CUSTOM_RAM_CAP, PersistentDataType.BYTE) && player.isSprinting()) {
            Armour.ramCapEffect(player, hitEntity);
        }
    }

    // Method to handle when a player gets hit
    private void handlePlayerDamage(Player player, EntityDamageByEntityEvent event) {
        LivingEntity damager = (LivingEntity) event.getDamager();
        applyChestArmorEffects(player, damager);
        applyLegArmorEffects(player);
    }

    // Method to apply chest armor effects when a player gets hit
    private void applyChestArmorEffects(Player player, LivingEntity damager) {
        ItemStack itemOnChest = player.getInventory().getItem(EquipmentSlot.CHEST);
        if (itemOnChest == null || !itemOnChest.hasItemMeta()) {
            return;
        }

        int thornLvl = itemOnChest.getEnchantmentLevel(Enchantment.THORNS);
        PersistentDataContainer playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();

        if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_CHEST, PersistentDataType.BYTE)) {
            Armour.explosiveChestEffect(20 * (1 + thornLvl), damager, player);
        } else if (playerContainer.has(Keys.CUSTOM_REFLECTIVE_CHESTPIECE, PersistentDataType.BYTE)) {
            Armour.reflectiveChestEffect(20 * (1 + thornLvl), thornLvl, damager);
        }
    }

    // Method to apply leg armor effects when a player gets hit
    private void applyLegArmorEffects(Player player) {
        ItemStack itemOnLegs = player.getInventory().getItem(EquipmentSlot.LEGS);
        if (itemOnLegs == null || !itemOnLegs.hasItemMeta()) {
            return;
        }

        PersistentDataContainer playerContainer = itemOnLegs.getItemMeta().getPersistentDataContainer();
        if (playerContainer.has(Keys.CUSTOM_NINJA_PANT, PersistentDataType.BYTE)) {
            Armour.ninjaPantEffect(player);
        }
    }

        //
        //      Shoot Arrow
        //
    @EventHandler
    public void onPlayerShootArrow(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player) || !(event.getProjectile() instanceof Arrow)) {
            return;
        }
    
        Player player = (Player) event.getEntity();
        Arrow arrow = (Arrow) event.getProjectile();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
    
        // Handle arrow effects based on bow type
        String bowType = determineBowType(itemInMainHand, arrow);
    
        // Handle shield effects (offhand item)
        applyShieldEffects(itemInOffHand, arrow);
    
        // Handle Elf Race special effects
        if (player.getPersistentDataContainer().has(Keys.RACE_ELF, PersistentDataType.BYTE)) {
            applyElfEffects(player, arrow, itemInMainHand, itemInOffHand, bowType);
        }
    }
    
    // Method to determine and apply bow effects
    private String determineBowType(ItemStack mainHandItem, Arrow arrow) {
        PersistentDataContainer container = mainHandItem.getItemMeta().getPersistentDataContainer();
        String bowType = "normal"; // Default type
    
        if (mainHandItem.getType() == Material.BOW) {
            if (container.has(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.BYTE)) {
                arrow.getPersistentDataContainer().set(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.STRING, "antiGravArrow");
                Weapons.gravityArrow(arrow);
                bowType = "gravity";
            } else if (container.has(Keys.CUSTOM_RAT_BOW, PersistentDataType.BYTE)) {
                arrow.getPersistentDataContainer().set(Keys.CUSTOM_RAT_BOW, PersistentDataType.STRING, "ratArrow");
                bowType = "rat";
            }
        } else if (mainHandItem.getType() == Material.CROSSBOW) {
            if (container.has(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.BYTE)) {
                arrow.getPersistentDataContainer().set(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.STRING, "bouncyArrow");
                bowType = "bouncy";
            }
        }
    
        return bowType;
    }
    
    // Method to apply shield effects (offhand item)
    private void applyShieldEffects(ItemStack offHandItem, Arrow arrow) {
        if (offHandItem.hasItemMeta() && offHandItem.getType() == Material.SHIELD) {
            PersistentDataContainer shieldContainer = offHandItem.getItemMeta().getPersistentDataContainer();
            if (shieldContainer.has(Keys.CUSTOM_ARROW_ENCHANTER, PersistentDataType.BYTE)) {
                MagicItems.arrowEnchanterEffect(arrow);
            }
        }
    }
    
    // Method to apply Elf race specific effects
    private void applyElfEffects(Player player, Arrow arrow, ItemStack mainHandItem, ItemStack offHandItem, String bowType) {
        Vector arrowVelocity = arrow.getVelocity();
        
        String effectType = getEffectType(mainHandItem, offHandItem);
        
        RaceEffects.elfShotEffect(player, arrowVelocity, effectType, bowType);
    }
    
    // Helper method to get the type of arrow effect (based on enchantments)
    private String getEffectType(ItemStack mainHandItem, ItemStack offHandItem) {
        if ((mainHandItem.getEnchantmentLevel(Enchantment.FLAME) > 0) || (offHandItem.getEnchantmentLevel(Enchantment.FLAME) > 0)) {
            return "flame";
        } else if ((mainHandItem.getEnchantmentLevel(Enchantment.MULTISHOT) > 0) || (offHandItem.getEnchantmentLevel(Enchantment.MULTISHOT) > 0)) {
            return "multishot";
        } else if ((mainHandItem.getEnchantmentLevel(Enchantment.PIERCING) > 0) || (offHandItem.getEnchantmentLevel(Enchantment.PIERCING) > 0)) {
            return "piercing";
        } else {
            return null;
        }
    }
    
    
    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        PersistentDataContainer playerContainer;
        
        if (event.getEntity() instanceof Trident) {
            Trident trident = (Trident) event.getEntity();
            Player player = (Player) trident.getShooter();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

            if (itemInMainHand.getType() == Material.TRIDENT) {
                playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
                if (playerContainer != null) {
                    if (playerContainer.has(Keys.CUSTOM_MAGNETIC_TRIDENT, PersistentDataType.BOOLEAN)) {
                        Weapons.magneticTridentEffect(player);
                    }
                }
            }if (itemInOffHand.getType() == Material.TRIDENT) {
                playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
                if (playerContainer != null) {
                    if (playerContainer.has(Keys.CUSTOM_MAGNETIC_TRIDENT, PersistentDataType.BOOLEAN)) {
                        Weapons.magneticTridentEffect(player);
                    }
                }
            }
        }
    }
}
