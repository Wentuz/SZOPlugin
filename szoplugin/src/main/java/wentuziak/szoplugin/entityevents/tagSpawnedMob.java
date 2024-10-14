package wentuziak.szoplugin.entityevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class tagSpawnedMob implements Listener {

    private static ItemStack mobAirItem = new ItemStack(Material.AIR);

    public static void startTagger() {
        // Schedule the repeating task to run every 10 minutes (600 seconds)
        new BukkitRunnable() {
            @Override
            public void run() {
                tagRandomUndead();
            }
        }.runTaskTimer(SzoPlugin.getInstance(), 0L, 600 * 20L);
    }

    private static void tagRandomUndead() {
        // Loop through all worlds and entities
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) { // Loop through all entities in the main world
            if ((entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.ZOMBIE) && LogicHolder.critRoll(10)) {
                LivingEntity undead = (LivingEntity) entity;
                boolean isArmorTiered = LogicHolder.critRoll(33) ? true : false;
                equipArmorEntity(undead, true, mobAirItem, mobAirItem, mobAirItem, mobAirItem, isArmorTiered);
                PersistentDataContainer container = undead.getPersistentDataContainer();
                container.set(Keys.MOB_RIOT, PersistentDataType.BYTE, (byte) 1);  // Tag the undead
            }
        }
    }

    public static void tagSpawnedEntity(LivingEntity entity, NamespacedKey key) {
        PersistentDataContainer container = entity.getPersistentDataContainer();
        container.set(key, PersistentDataType.BYTE, (byte) 1);
    }

    public static void equipArmorEntity(LivingEntity entity, boolean isRandom, ItemStack mobHeadGear, ItemStack mobChest, ItemStack mobLegs, ItemStack mobBoots, boolean isArmorTiered){
        LogicHolder.givePotionEffect(entity, "GLOWING", 20 * 2, 0);

        entity.getEquipment().setHelmetDropChance(0.05F);
        entity.getEquipment().setChestplateDropChance(0.05F);
        entity.getEquipment().setLeggingsDropChance(0.05F);
        entity.getEquipment().setBootsDropChance(0.05F);

        if (isRandom) {
            LogicHolder.equipRandomArmor(isArmorTiered, entity);
            return;
        }
        entity.getEquipment().setHelmet(mobHeadGear);
        entity.getEquipment().setChestplate(mobChest);
        entity.getEquipment().setLeggings(mobLegs);
        entity.getEquipment().setBoots(mobBoots);

    }

}
