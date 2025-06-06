package wentuziak.szoplugin.playerevents;




import java.lang.reflect.AccessFlag.Location;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.TaskManager;
import wentuziak.szoplugin.customcrafting.RaceCrafting;
import wentuziak.szoplugin.customitems.Armour;
import wentuziak.szoplugin.customitems.CustomTools;
import wentuziak.szoplugin.customitems.MagicItems;
import wentuziak.szoplugin.customitems.Weapons;
import wentuziak.szoplugin.customlogic.*;
import wentuziak.szoplugin.races.RaceEffects;







public class InteractionListener implements Listener{


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

        boolean clickedRightButton = !(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK);

        if (clickedRightButton && (itemInMainHand != null && itemInOffHand != null)) {
            SpecialItems.simpleItemEffect(player, itemInMainHand, itemInOffHand);

            if (itemInOffHand.hasItemMeta()) {
                PersistentDataContainer playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
                if (playerContainer.has(Keys.CUSTOM_MARKING_SPYGLASS, PersistentDataType.BYTE)) {
                    CustomTools.markingSpyglassEffect(player, false);
                }
                else if (playerContainer.has(Keys.CUSTOM_HOMECOMING_COMPASS, PersistentDataType.BYTE)) {
                    MagicItems.homeTeleport(player);
                    LogicHolder.removeItem(player, itemInOffHand);
                }
                else if (playerContainer.has(Keys.CUSTOM_DEATH_CALLER, PersistentDataType.BYTE)) {
                    MagicItems.deathTeleport(player);
                    LogicHolder.removeItem(player, itemInOffHand);
                }
                else if (playerContainer.has(Keys.CUSTOM_WIND_CHARM, PersistentDataType.BYTE)) {
                    MagicItems.windCharmEffect(player);
                    LogicHolder.removeItem(player, itemInOffHand);
                }

            }
            if (itemInMainHand.hasItemMeta()) {
                PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
                if (playerContainer.has(Keys.CUSTOM_MARKING_SPYGLASS, PersistentDataType.BYTE)) {
                    CustomTools.markingSpyglassEffect(player, true);
                }
                else if (playerContainer.has(Keys.CUSTOM_HOMECOMING_COMPASS, PersistentDataType.BYTE)) {
                    MagicItems.homeTeleport(player);
                    LogicHolder.removeItem(player, itemInMainHand);
                }
                else if (playerContainer.has(Keys.CUSTOM_DEATH_CALLER, PersistentDataType.BYTE)) {
                    MagicItems.deathTeleport(player);
                    LogicHolder.removeItem(player, itemInMainHand);
                }
                else if (playerContainer.has(Keys.CUSTOM_WIND_CHARM, PersistentDataType.BYTE)) {
                    MagicItems.windCharmEffect(player);
                    LogicHolder.removeItem(player, itemInMainHand);
                }
            }
                    //
                    //      RACE HAND CRAFTING
                    //
            if ((event.getHand() == EquipmentSlot.HAND && itemInMainHand.getType() != Material.AIR && itemInOffHand.getType() != Material.AIR)) {
                if (player.getPersistentDataContainer().has(Keys.RACE_DWARF)) {
                    RaceCrafting.dwarfCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_WITCH)){
                    RaceCrafting.witchCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_MEWCHANT)){
                    RaceCrafting.mewchantCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_FOSSIL)){
                    RaceCrafting.fossilCraftingEvent(player, itemInMainHand, itemInOffHand);
                    RaceEffects.fossilSummonEvilAxolotl(player, itemInOffHand, itemInMainHand);
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_CARA)){
                    RaceCrafting.caraCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if (player.getPersistentDataContainer().has(Keys.RACE_ELF)) {
                    if (!player.hasCooldown(Material.POPPED_CHORUS_FRUIT)) {
                        RaceEffects.elfBreedingEffect(player, itemInMainHand, itemInOffHand);
                    }
                }
                if (player.getPersistentDataContainer().has(Keys.RACE_SANGUINITE)) {
                    RaceCrafting.sanguiniteCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if (player.getPersistentDataContainer().has(Keys.RACE_MISKARU)) {
                    RaceCrafting.miskaruCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
            }
        }

        //
        //      META ITEMS
        //

        // Magic for main hand only
            if (itemInMainHand != null && itemInMainHand.getType() != Material.AIR && itemInMainHand.hasItemMeta()){
                //
                // Spell scrolls
                //                
                if(!player.hasCooldown(Material.GLOBE_BANNER_PATTERN)) {
                ItemStack itemOnFeet = player.getInventory().getItem(EquipmentSlot.FEET);
                PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
                PersistentDataContainer bootContainer;
                boolean isSpellBoosted = false;

                // Check if boots have the spell boost key
                if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
                bootContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
                if (bootContainer.has(Keys.CUSTOM_MAGIC_BOOTS, PersistentDataType.BYTE)) {
                    isSpellBoosted = true;
                }
                }

                // Handle teleport spell
                if (playerContainer.has(Keys.CUSTOM_TELEPORT_SPELL, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.teleportSpell(player, isSpellBoosted, 50);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 10);
                return;
                }

                // Handle spirit leech
                if (playerContainer.has(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.spiritLeech(player, Keys.CUSTOM_SPIRIT_LEECH, isSpellBoosted);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 5);
                return;
                }

                // Handle spider yeet
                if (playerContainer.has(Keys.CUSTOM_SPIDER_YEET, PersistentDataType.BYTE) && clickedRightButton) {
                if (isSpellBoosted) {
                    MagicItems.spiderYeet(player, Keys.CUSTOM_SPIDER_YEET);
                }
                MagicItems.spiderYeet(player, Keys.CUSTOM_SPIDER_YEET);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 15);
                return;
                }

                // Handle web trap
                if (playerContainer.has(Keys.CUSTOM_WEB_TRAP, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.webTrapThrow(player, Keys.CUSTOM_WEB_TRAP);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 10);
                return;
                }

                // Handle obliterate
                if (playerContainer.has(Keys.CUSTOM_OBLITERATE, PersistentDataType.BYTE) && clickedRightButton) {
                if (isSpellBoosted) {
                    LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * 2, 1);
                    MagicItems.obliterate(player);
                }
                MagicItems.obliterate(player);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 360);
                return;
                }

                // Handle sanguinite scroll
                if (playerContainer.has(Keys.CUSTOM_SANGUINITE_SCROLL, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.crimsonMagic(player, itemInOffHand, itemInMainHand, isSpellBoosted);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 20);
                return;
                }

                // Handle magic storm
                if (playerContainer.has(Keys.CUSTOM_MAGIC_STORM, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.magicStormCall(player, isSpellBoosted);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 30);
                return;
                }
                }

                //
                //      Sword Skills
                //
                if (itemInOffHand.getType() == Material.AIR && clickedRightButton) {
                    PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
                    if (playerContainer.has(Keys.CUSTOM_DAEMON_SWORD, PersistentDataType.BYTE) 
                    		&& !player.hasCooldown(Material.STONE_SWORD)) {
                        MagicItems.spiritLeech(player, Keys.CUSTOM_SPIRIT_LEECH, false);
                        player.setCooldown(Material.STONE_SWORD, 20 * 15);
                        return;
                    }
                    if (playerContainer.has(Keys.CUSTOM_ANGEL_SWORD, PersistentDataType.BYTE)
                    		&& !player.hasCooldown(Material.GOLDEN_SWORD)) {
                        LogicHolder.lingeringPotionDrop(PotionType.HEALING, PotionEffectType.INSTANT_HEALTH, player);
                        player.setCooldown(Material.GOLDEN_SWORD, 20 * 30);
                        return;
                    }
                    if (playerContainer.has(Keys.CUSTOM_SPELL_SWORD, PersistentDataType.BYTE)
                    		&& !player.hasCooldown(Material.IRON_SWORD)) {
                    	MagicItems.teleportSpell(player, false, 5);
                        player.setCooldown(Material.IRON_SWORD, 20 * 2);
                        return;
                    }
                    if (playerContainer.has(Keys.CUSTOM_ARMOR_PIERCER, PersistentDataType.BYTE)
                    		&& !player.hasCooldown(Material.DIAMOND_AXE)) {
                        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
                            Vector direction = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
                            entity.getWorld().spawnParticle(Particle.SMOKE, entity.getLocation(), 10, 1, 1, 1, 0.015);
                            double force = 3.0;
                            entity.setVelocity(direction.multiply(force));
                            LogicHolder.givePotionEffect((LivingEntity) entity, "SLOW", 20 * 5, 0);
                            Weapons.bleedEffect((LivingEntity)entity, 3.0, 5);
                        }
                        player.setCooldown(Material.DIAMOND_AXE, 20 * 10);
                        return;
                    }
                    if (playerContainer.has(Keys.CUSTOM_THUNDER_HAMMER, PersistentDataType.BYTE)
                    		&& !player.hasCooldown(Material.DIAMOND_AXE)) {
                        for (Entity entity : player.getNearbyEntities(10, 3, 10)) {
                            Vector direction = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
                            entity.getWorld().spawnParticle(Particle.SMOKE, entity.getLocation(), 10, 1, 1, 1, 0.015);
                            double force = 3.0;
                            entity.setVelocity(direction.multiply(force));
                            Weapons.thunderHammerEffect(100, (LivingEntity) entity);
                        }
                        player.setCooldown(Material.DIAMOND_AXE, 20 * 30);
                        LogicHolder.givePotionEffect(player, "WEAKNESS", 20 * 30, 1);
                        return;
                    }
                    if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BYTE)
                    		&& !player.hasCooldown(Material.DIAMOND_SWORD)) {
                        org.bukkit.Location location = player.getLocation();
                        WindCharge windCharge = (WindCharge) player.getWorld().spawnEntity(location, EntityType.WIND_CHARGE);
                        windCharge.explode();
                        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                            Weapons.grenadeEffect(location);
                        }, 5L);
                        
                        player.setCooldown(Material.DIAMOND_SWORD, 20 * 5);

                        return;
                    }
                }
            }

        // Main hand non magic
        if (clickedRightButton && itemInMainHand != null && itemInMainHand.getType() != Material.AIR && itemInMainHand.hasItemMeta()) {
            PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
            if (itemInOffHand != null && itemInOffHand.getType() == Material.FLINT_AND_STEEL
            && !player.hasCooldown(Material.FIREWORK_STAR)) {
                if (playerContainer.has(Keys.CUSTOM_GRENADE, PersistentDataType.BYTE)) {
                    Weapons.grenadeThrow(player, playerContainer);
                    player.setCooldown(Material.FIREWORK_STAR, 20 * 8);
                    LogicHolder.removeItem(player, itemInMainHand);
                    return;
                }
                else if (playerContainer.has(Keys.CUSTOM_BREACH_CHARGE, PersistentDataType.BYTE)) {
                    Weapons.breachThrow(player);
                    player.setCooldown(Material.FIREWORK_STAR, 20 * 10);
                    LogicHolder.removeItem(player, itemInMainHand);
                    return;
                }
                else if(playerContainer.has(Keys.CUSTOM_SMOKE_BOMB, PersistentDataType.BYTE)){
                    Weapons.smokeThrow(player, playerContainer);
                    player.setCooldown(Material.FIREWORK_STAR, 20 * 8);
                    LogicHolder.removeItem(player, itemInMainHand);
                    return;
                }
                else if(playerContainer.has(Keys.CUSTOM_THROWING_FIREWORK, PersistentDataType.BYTE)){
                    Weapons.fireworkThrow(player, playerContainer);
                    player.setCooldown(Material.FIREWORK_STAR, 10);
                    LogicHolder.removeItem(player, itemInMainHand);
                    return;
                }
            }
        }
    }

    //
    //      Sneak
    //
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemOnFeet = player.getInventory().getItem(EquipmentSlot.FEET);
        ItemStack itemOnChest = player.getInventory().getItem(EquipmentSlot.CHEST);
        ItemStack itemOnLegs = player.getInventory().getItem(EquipmentSlot.LEGS);
        Boolean isInWater = LogicHolder.isPlayerInWater(player);


        PersistentDataContainer playerContainer;
        if (itemOnChest != null && itemOnChest.hasItemMeta()) {
            playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_GOLEM_CHEST, PersistentDataType.BYTE)) {
                Armour.golemChestEffect(player);
            }
        }
        
        if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
            playerContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
            if (!player.isClimbing() && !isInWater) {
                if (playerContainer.has(Keys.CUSTOM_JET_BOOTS, PersistentDataType.BYTE)) {
                    Armour.jetBootsEffect(player, 2);
                }
            }
        }
    
        if (itemOnLegs != null && itemOnLegs.hasItemMeta()) {
            playerContainer = itemOnLegs.getItemMeta().getPersistentDataContainer();
            if (!player.isClimbing() && !isInWater ) {
                if (!player.hasCooldown(Material.LEATHER_LEGGINGS)) {
                    if (playerContainer.has(Keys.CUSTOM_JUMP_PACK, PersistentDataType.BYTE) 
                    && ((player.getPersistentDataContainer().has(Keys.RACE_SANGUINITE) || (player.getPersistentDataContainer().has(Keys.RACE_MECHANICAL))))) { 
                        Armour.jumpPackEffect(player, 2.0);
                    }
                    else if (playerContainer.has(Keys.CUSTOM_JUMP_PACK, PersistentDataType.BYTE)) {
                        Armour.jumpPackEffect(player, 1.1);
                    }
                    player.setCooldown(Material.LEATHER_LEGGINGS, 20);
                }
            }
        }

        if (player.getPersistentDataContainer().has(Keys.RACE_SANGUINITE)
        && !player.isClimbing() && !isInWater
        && !player.hasCooldown(Material.NETHER_STAR)) {
            RaceEffects.sanguiniteJumpEffect(player);
        }


        if (player.getPersistentDataContainer().has(Keys.RACE_CARA) && LogicHolder.isPlayerAboveGround(player, 1)
        && !player.isClimbing() && !isInWater && player.isSneaking()) {
            if (!TaskManager.isTaskRunning(player, "caraGlide")) {
                RaceEffects.caraGlideEvent(player);
            }else{
                RaceEffects.stopCaraGlideTask(player);
            }
        }else{
            RaceEffects.stopCaraGlideTask(player);
        }
        
    }
    
        //
        //      Food effects
        //
 
    @EventHandler
    public void onPlayerLooseHunger(FoodLevelChangeEvent event){
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            int oldHunger = player.getFoodLevel();
            int newHunger = event.getFoodLevel();

            if(newHunger < oldHunger) {
                if (newHunger > 0) {
                    if (player.getPersistentDataContainer().has(Keys.RACE_HOBBIT) && LogicHolder.critRoll(25)) {
                        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                            player.setFoodLevel(newHunger-1);
                        }, 10L);
                    }
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_MECHANICAL)) {
                	if(newHunger < 6) RaceEffects.mechanicalHungerEvent(player);

                	if(newHunger > 10 && LogicHolder.critRoll(35)) {
                		Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                            player.setFoodLevel(newHunger-2);
                        }, 10L);
                	}else if(LogicHolder.critRoll(newHunger * 8)) {
                    	event.setCancelled(true);
                	}
                }
            }
        }
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack consumedItem = event.getItem();
        ItemStack itemOnLeg = player.getInventory().getItem(EquipmentSlot.LEGS);

        if (itemOnLeg != null && itemOnLeg.hasItemMeta()) {
            PersistentDataContainer playerContainer = itemOnLeg.getItemMeta().getPersistentDataContainer();

            if (playerContainer.has(Keys.CUSTOM_GLUTTONY_PANTS, PersistentDataType.BYTE)) {
                Armour.gluttonyPantsEffect(player);
            }
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_DWARF)) {
            RaceEffects.dwarfConsumptionEffect(player, consumedItem);
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_HOBBIT)) {
            RaceEffects.hobbitConsumptionEffect(player, consumedItem);
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_WITCH)) {
            RaceEffects.witchConsumptionEffect(player, consumedItem);
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_MISKARU)) {
            RaceEffects.miskaruConsumptionEffect(player, consumedItem);
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_MEWCHANT)) {
            RaceEffects.mewchantConsumptionEffect(player, consumedItem);
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_SANGUINITE)) {
            RaceEffects.sanguiniteConsumptionEffect(player, consumedItem);
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_ZEPHYR)) {
            if (player.getFoodLevel() > 0) {
                player.setFoodLevel(player.getFoodLevel() - 1);
                
                if (player.getFoodLevel() < 0) {
                    player.setFoodLevel(0);
                }
            }
        }
        SpecialFood.effectFoodFunc(player, consumedItem);
        
    }

        //
        //      On item held
        //
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItem(event.getNewSlot());

        if (itemInMainHand == null) {
            CustomTools.stopHastyToolTask(player);
            return;
        }

        PersistentDataContainer playerContainer;
        if (itemInMainHand.hasItemMeta()) {
            playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_HASTY_TOOL, PersistentDataType.BYTE)) {
                CustomTools.hastyToolEffect(player);
            }
        }
    }


    //
    //      Fishing Events
    //
    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
        PersistentDataContainer playerContainer;
        PersistentDataContainer playerOffHandContainer;
        Projectile projectile = event.getHook();
        int luckLvl = itemInMainHand.getEnchantmentLevel(Enchantment.LUCK_OF_THE_SEA);

        if (event.getState() == State.CAUGHT_FISH) {
            if (!(itemInMainHand.hasItemMeta()) && !(itemInOffHand.hasItemMeta())) {
                return;
            }
            if (itemInMainHand.hasItemMeta()){
                playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
                if (playerContainer.has(Keys.CUSTOM_TREASURE_FISHING, PersistentDataType.BYTE)) {
                    CustomTools.treasureFishingRodEffect(8 * luckLvl ,player, projectile, luckLvl, "Mobs");
                    CustomTools.treasureFishingRodEffect(11 * luckLvl ,player, projectile, luckLvl, "Ore");
                    CustomTools.treasureFishingRodEffect(22 * luckLvl ,player, projectile, luckLvl, "FishingTreasure");
                }
            }
            if (itemInOffHand.hasItemMeta()) {
                playerOffHandContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
    
                if (playerOffHandContainer.has(Keys.CUSTOM_LUCKY_CLOCK, PersistentDataType.BYTE)) {
                    CustomTools.treasureFishingRodEffect(11 * luckLvl ,player, projectile, luckLvl, "FishingTreasure");
                }
            }
            if (player.getPersistentDataContainer().has(Keys.RACE_MEWCHANT)) {
                RaceEffects.mewchantFishEvent(player, projectile);
                CustomTools.treasureFishingRodEffect(11 * luckLvl ,player, projectile, luckLvl, "FishingTreasure");
            }
        }
    }
    

    //
    //      Movement
    //
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        ItemStack itemLeggings = player.getInventory().getLeggings();
        ItemStack itemBoots = player.getInventory().getBoots();
        ItemStack itemHelmet = player.getInventory().getHelmet();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
        PersistentDataContainer playerContainer;
        
        boolean isInWater = LogicHolder.isPlayerInWater(player);
        if (itemLeggings != null ) {
            if (itemLeggings.hasItemMeta()) {
                playerContainer = itemLeggings.getItemMeta().getPersistentDataContainer();
                if (playerContainer.has(Keys.CUSTOM_CERBERUS_CHAIN, PersistentDataType.BYTE)) {
                    MagicItems.summonCerberus(player);
                }else{
                    MagicItems.killCerberus(player);
                }
            }
        }else{
            MagicItems.killCerberus(player);
        }
        if (itemInOffHand.hasItemMeta()) {
            playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_ANCIENT_SHELL, PersistentDataType.BYTE) && (player.isSwimming() || isInWater || LogicHolder.isRaining(player.getWorld()))) {
                MagicItems.ancientShellEffect(player);
            }else{
                MagicItems.stopAncientShellTask(player);
            }
        }
        if (itemBoots != null && itemBoots.hasItemMeta()) {
            playerContainer = itemBoots.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_WALKERS, PersistentDataType.BYTE) && (!player.isSwimming() || !isInWater)
            && !player.isSneaking()) {
                Armour.walkersEffect(player);
            }
        }
        if (itemHelmet != null && itemHelmet.hasItemMeta()) {
            playerContainer = itemHelmet.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_RAM_CAP, PersistentDataType.BYTE) && (player.isSprinting())) {
                Armour.ramCapSprint(player);
            }
            if (playerContainer.has(Keys.CUSTOM_WITCH_HELMET, PersistentDataType.BYTE) && !player.hasCooldown(Material.NETHERITE_HELMET)) {
                Armour.witchHelmetEffect(player);
            }
            
        }


        if (!isInWater) {
            MagicItems.stopAncientShellTask(player);
            RaceEffects.stopDwarfSwimTask(player);
            RaceEffects.stopFossilSwimTask(player);
        }
        if ((player.getPersistentDataContainer().has(Keys.RACE_DWARF) && (player.isSwimming() || isInWater))) {
            RaceEffects.dwarfSwimEvent(player);
        }
        if ((player.getPersistentDataContainer().has(Keys.RACE_FOSSIL) && (player.isSwimming() || isInWater))) {
            RaceEffects.fossilSwimEvent(player);
        }
        if ((player.getPersistentDataContainer().has(Keys.RACE_HOBBIT) && (!LogicHolder.isDaytime(player.getWorld())))) {
            RaceEffects.hobbitNightEvent(player);
        }
    }

    @EventHandler
    public void onPlayerShearEntity(PlayerShearEntityEvent event) {
        Entity sheep = event.getEntity();
        ItemStack shears = event.getItem();

        if (shears.hasItemMeta() && sheep.getType() == EntityType.SHEEP) {
            if (shears.getItemMeta().getPersistentDataContainer().has(Keys.CUSTOM_SUPER_SHEARS, PersistentDataType.BOOLEAN)) {
                for(int i = 0; i < 8; i++){
                    if (LogicHolder.critRoll(66)) {
                        sheep.getWorld().dropItemNaturally(sheep.getLocation(), new ItemStack(Material.STRING));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerSwapHand(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

        if (itemInMainHand.getType() != Material.AIR || itemInOffHand.getType() != Material.AIR) {
            return;
        }

        if (player.getPersistentDataContainer().has(Keys.RACE_CARA) && 
        LogicHolder.isPlayerAboveGround(player, 0.5) && !player.isClimbing()) {
            RaceEffects.caraJumpEvent(player);
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_CELESTIAL)) {
            RaceEffects.celestialSummonEvent(player);
        }
    }
    
    @EventHandler
    public void onFurnanceExtract(FurnaceExtractEvent event) {
    	Block block = event.getBlock();
    	Material item = event.getItemType();
    	int amount = event.getItemAmount();
    	Player player = event.getPlayer();
    	
    	if (player.getPersistentDataContainer().has(Keys.RACE_HOBBIT)) {
    	    if(RaceEffects.hobbitFood(player, item, amount)) {
    	    	player.sendMessage("YES");
    	    }
    	}
    }
}
