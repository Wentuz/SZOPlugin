package wentuziak.szoplugin.entityevents;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


import net.md_5.bungee.api.ChatColor;
import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.customcrafting.CreateCustomItem;
import wentuziak.szoplugin.customcrafting.CustomRecipes;
import wentuziak.szoplugin.customitems.MagicItems;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.races.RaceEffects;
import wentuziak.szoplugin.races.UpdateAttributes;


public class EntityListener implements Listener {

    private static ItemStack mobHeadGear = new ItemStack(Material.CARVED_PUMPKIN);


    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event){   
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        ItemStack itemOffHand = player.getInventory().getItemInOffHand();

        if (itemInHand.getType() == Material.AIR && itemOffHand.getType() == Material.AIR && player.getPersistentDataContainer().has(Keys.RACE_HOBBIT)) {
            entity.addPassenger(player);
        }


        if (itemInHand == null || !itemInHand.hasItemMeta()) {
            return;
        }
        PersistentDataContainer playerContainer = itemInHand.getItemMeta().getPersistentDataContainer();
        
        if (playerContainer.has(Keys.CUSTOM_EFFECT_TRANSFUSER, PersistentDataType.BYTE)) {
            MagicItems.effectTransfuserEffect(player, (LivingEntity) entity);
        }
    }

    @EventHandler
    public void onEntityBreed(EntityBreedEvent event){
        Entity entity = event.getEntity();

        if (event.getBreeder() instanceof Player) {            
            Player player = (Player) event.getBreeder();
    
            if (player.getPersistentDataContainer().has(Keys.RACE_ELF)) {
                RaceEffects.elfFeedEffect((LivingEntity) entity);
                if (LogicHolder.critRoll(45)) {
                    entity.getWorld().dropItemNaturally(entity.getLocation(), CreateCustomItem.createSoulEssence());
                }
                if(LogicHolder.critRoll(25)) {
                	LivingEntity copiedEntity = (LivingEntity) entity.getLocation().getWorld().spawnEntity(entity.getLocation(), entity.getType());
                	((Ageable) copiedEntity).setBaby();
                }
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

         UpdateAttributes.attributeManager(player, true, ""); // ensure none are loaded
         for (NamespacedKey key : raceKeys) {
             if (dataContainer.has(key, PersistentDataType.BOOLEAN)) {
                 String keyString = "RACE_" + key.getKey().toUpperCase();
                 player.sendMessage(ChatColor.GREEN + keyString + " Loaded"); // load attributes
                 UpdateAttributes.attributeManager(player, false, keyString);
             }
         }
     }

    @EventHandler
    public void onEntityRegainHealth(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            
            //
            //      Turn off Natural Regen for Celestial's, Fossils and Mechanical
            //
            if ((player.getPersistentDataContainer().has(Keys.RACE_CELESTIAL) || player.getPersistentDataContainer().has(Keys.RACE_FOSSIL)
            		|| player.getPersistentDataContainer().has(Keys.RACE_MECHANICAL)) &&
            (event.getRegainReason() == RegainReason.REGEN || event.getRegainReason() == RegainReason.SATIATED)) {
                event.setCancelled(true);
            }
        }
    }  

    @EventHandler
     public void onEntitySpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        
        if (LogicHolder.critRoll(75)) {
            return;
        }

        if (entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.ZOMBIE) {
            tagSpawnedMob.tagSpawnedEntity(entity, Keys.MOB_RIOT);
            boolean isArmorTiered = LogicHolder.critRoll(33) ? true : false;
            LogicHolder.equipRandomArmor(isArmorTiered, entity);
            return;
        }
        if (entity.getType() == EntityType.SPIDER) {
            if (LogicHolder.critRoll(20)) {
                repleaceEntity(entity, EntityType.CAVE_SPIDER);
            }
            if (LogicHolder.critRoll(100)) { // test value
                Witch witch = (Witch) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WITCH);
                entity.addPassenger(witch);
            }
            tagSpawnedMob.tagSpawnedEntity(entity, Keys.MOB_RIOT);
            return;
        }
        if (entity.getType() == EntityType.CREEPER) {
            if (LogicHolder.critRoll(25)) {
                ((Creeper) entity).setPowered(true);
            }
            tagSpawnedMob.tagSpawnedEntity(entity, Keys.MOB_RIOT);
            return;
        }
        if (entity.getType() == EntityType.PHANTOM) {
            if (LogicHolder.critRoll(25)) {
                Skeleton skeleton = (Skeleton) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.SKELETON);
                entity.addPassenger(skeleton);
            }
            tagSpawnedMob.tagSpawnedEntity(entity, Keys.MOB_RIOT);
            return;
        }
        if (entity.getType() == EntityType.WITCH) {
            tagSpawnedMob.tagSpawnedEntity(entity, Keys.MOB_RIOT);
            return;
        }
    }

    public static void repleaceEntity(LivingEntity entityToRelpeace, EntityType newType){
        Location entityLocation = entityToRelpeace.getLocation();
        
        entityLocation.getWorld().spawnEntity(entityLocation, newType);

        entityToRelpeace.setHealth(0);
    }

    @EventHandler
    public static void piglinBarterEvent(PiglinBarterEvent event){
        List<ItemStack> barteredItems = event.getOutcome();
        Piglin piglin = event.getEntity();

        Player player = LogicHolder.findNearestPlayer(piglin.getLocation());

        if (player.getPersistentDataContainer().has(Keys.RACE_MEWCHANT)) {
            RaceEffects.mewchantBarterEvent(barteredItems);
        }
    }
}
