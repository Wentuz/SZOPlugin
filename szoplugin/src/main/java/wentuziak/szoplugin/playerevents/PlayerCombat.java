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
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event){   
        if (event.getCause() == EntityDamageEvent.DamageCause.THORNS) {
            return;
        }

        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            PersistentDataContainer playerContainer;


            float attackCooldown = player.getAttackCooldown();
            if (attackCooldown < 1) {
                return;
            }
            
            LivingEntity hitEntity = (LivingEntity) event.getEntity();
            if (!(itemInMainHand == null || itemInMainHand.getType() == Material.AIR)) {
                int sharpLvl = itemInMainHand.getEnchantmentLevel(Enchantment.SHARPNESS);
                playerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();
                if (player.getPersistentDataContainer().has(Keys.RACE_WITCH)){
                    RaceEffects.witchAttackEvent(player, hitEntity);
                }
                if (player.getPersistentDataContainer().has(Keys.RACE_CELESTIAL)){
                    RaceEffects.celestialAttackEvent(player, hitEntity);
                }
                if (player.getPersistentDataContainer().has(Keys.RACE_ZEPHYR)){
                    if (LogicHolder.critRoll(22)) {
                        RaceEffects.zaphyrKnockback(player);
                    }
                }
    
                //
                //      Special Weapons
                //
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
                    LogicHolder.modifyCurrentHeatlhPoints(hitEntity, (double) (sharpLvl));
                    if (LogicHolder.critRoll(sharpLvl * 10)) {
                        Weapons.bleedEffect(hitEntity, 2.0, sharpLvl);
                    }
                }
            }
            
            ItemStack itemOnHead = player.getInventory().getItem(EquipmentSlot.HEAD);

            if (!(itemOnHead == null || itemOnHead.getType() == Material.AIR)) {               
                if (itemOnHead.hasItemMeta()) {
                    playerContainer = itemOnHead.getItemMeta().getPersistentDataContainer();
                    if (playerContainer.has(Keys.CUSTOM_STRIGA_VEIL, PersistentDataType.BYTE)) {
                        Armour.strigaVeilEffect(15, player);
                    }
                    if (playerContainer.has(Keys.CUSTOM_RAM_CAP, PersistentDataType.BYTE) && player.isSprinting()) {
                        Armour.ramCapEffect(player, hitEntity);
                    }
                }
            }
        }
        //
        //      Player got hit
        //
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack itemOnChest = player.getInventory().getItem(EquipmentSlot.CHEST);
            ItemStack itemOnLegs = player.getInventory().getItem(EquipmentSlot.LEGS);

            
            if (!(itemOnChest == null || !itemOnChest.hasItemMeta())) {
                LivingEntity damager = (LivingEntity) event.getDamager();
                PersistentDataContainer playerContainer;
                
                if (itemOnChest.hasItemMeta()) {
                    int thornLvl = itemOnChest.getEnchantmentLevel(Enchantment.THORNS);
                    playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
                    if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_CHEST, PersistentDataType.BYTE)) {
                        Armour.explosiveChestEffect(20 & thornLvl ,damager, player);
                    }else if(playerContainer.has(Keys.CUSTOM_REFLECTIVE_CHESTPIECE, PersistentDataType.BYTE)){
                        Armour.reflectiveChestEffect(20 * thornLvl, thornLvl ,damager);
                    }
                }
            }
            if (itemOnLegs != null) {
                PersistentDataContainer playerContainer;
                
                if (itemOnLegs.hasItemMeta()) {
                    playerContainer = itemOnLegs.getItemMeta().getPersistentDataContainer();
                    if (playerContainer.has(Keys.CUSTOM_NINJA_PANT, PersistentDataType.BYTE)) {
                        Armour.ninjaPantEffect(player);
                    }
                }
            }

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
