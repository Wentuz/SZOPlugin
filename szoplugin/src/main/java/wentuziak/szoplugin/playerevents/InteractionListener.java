package wentuziak.szoplugin.playerevents;




import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
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
            }
        }

        //
        //      META ITEMS
        //

        // Magic for main hand only
        if (itemInMainHand != null && itemInMainHand.getType() != Material.AIR && itemInMainHand.hasItemMeta() 
        && !player.hasCooldown(Material.GLOBE_BANNER_PATTERN)) {
            ItemStack itemOnFeet = player.getInventory().getItem(EquipmentSlot.FEET);

            PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
            PersistentDataContainer bootContainer;
            if (playerContainer.has(Keys.CUSTOM_TELEPORT_SPELL, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.teleportSpell(player);
                if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
                    bootContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
                    if (bootContainer.has(Keys.CUSTOM_MAGIC_BOOTS, PersistentDataType.BYTE)) {
                        LogicHolder.givePotionEffect(player, "SPEED", 20 * 10, 0);
                    }
                }
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 35);
                return;
            }
            if (playerContainer.has(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.spiritLeech(player, playerContainer);
                if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
                    bootContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
                    if (bootContainer.has(Keys.CUSTOM_MAGIC_BOOTS, PersistentDataType.BYTE)) {
                        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                            MagicItems.spiritLeech(player, itemInMainHand.getItemMeta().getPersistentDataContainer());
                        }, 5L);
                        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                            MagicItems.spiritLeech(player, itemInMainHand.getItemMeta().getPersistentDataContainer());
                        }, 10L);
                    }
                }
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 10);
                return;
            }   
            if (playerContainer.has(Keys.CUSTOM_SPIDER_YEET, PersistentDataType.BYTE) && clickedRightButton) {
                if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
                    bootContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
                    if (bootContainer.has(Keys.CUSTOM_MAGIC_BOOTS, PersistentDataType.BYTE)) {
                        MagicItems.spiderYeet(player, playerContainer);
                    }
                }
                MagicItems.spiderYeet(player, playerContainer);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 45);
                return;
            }   
            if (playerContainer.has(Keys.CUSTOM_OBLITERATE, PersistentDataType.BYTE) && clickedRightButton) {
                if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
                    bootContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
                    if (bootContainer.has(Keys.CUSTOM_MAGIC_BOOTS, PersistentDataType.BYTE)) {
                        LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20*2, 1);
                    }
                }
                MagicItems.obliterate(player);
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 360);
                return;
            }   
            if (playerContainer.has(Keys.CUSTOM_SANGUINITE_SCROLL, PersistentDataType.BYTE) && clickedRightButton) {
                if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
                    bootContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
                    if (bootContainer.has(Keys.CUSTOM_MAGIC_BOOTS, PersistentDataType.BYTE)) {
                        RaceEffects.sanguiniteMagic(player, itemInOffHand, itemInMainHand, true);
                    }
                }else{
                    RaceEffects.sanguiniteMagic(player, itemInOffHand, itemInMainHand, false);
                }
                player.setCooldown(Material.GLOBE_BANNER_PATTERN, 20 * 20);
                return;
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
                    player.setCooldown(Material.FIREWORK_STAR, 20 * 2);
                    LogicHolder.removeItem(player, itemInMainHand);
                    return;
                }
            }
        }

        // Off hand items only
        if (itemInOffHand != null && itemInOffHand.getType() != Material.AIR && itemInOffHand.hasItemMeta()) {
            PersistentDataContainer playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_IRON_BREAKER_SHIELD, PersistentDataType.BYTE) && clickedRightButton && !(player.isHandRaised())) {
                CustomTools.effectRaisedShieldEffect(player, 3);
            }
            else if (playerContainer.has(Keys.CUSTOM_BERSERKER_SHIELD, PersistentDataType.BYTE) && clickedRightButton && !(player.isHandRaised())) {
                CustomTools.effectRaisedShieldEffect(player, 2);
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
        ItemStack itemOnHead = player.getInventory().getItem(EquipmentSlot.HEAD);
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
                    Armour.jetBootsEffect(player);
                }
            }
        }
    
        if (itemOnLegs != null && itemOnLegs.hasItemMeta()) {
            playerContainer = itemOnLegs.getItemMeta().getPersistentDataContainer();
            if (!player.isClimbing() && !isInWater) {
                if (!player.hasCooldown(Material.LEATHER_LEGGINGS)) {
                    if (playerContainer.has(Keys.CUSTOM_JUMP_PACK, PersistentDataType.BYTE) 
                    && player.getPersistentDataContainer().has(Keys.RACE_SANGUINITE)) {
                        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                            Armour.jumpPackEffect(player);
                        }, 10L);
                    }
                    else if (playerContainer.has(Keys.CUSTOM_JUMP_PACK, PersistentDataType.BYTE)) {
                        Armour.jumpPackEffect(player);
                    }
                    
                }
            }
        }

        if (player.getPersistentDataContainer().has(Keys.RACE_SANGUINITE)
        && !player.isClimbing() && !isInWater
        && !player.hasCooldown(Material.NETHER_STAR)) {
            RaceEffects.sanguiniteJumpEffect(player);
        }
        if (itemOnHead != null && itemOnHead.hasItemMeta()) {
            playerContainer = itemOnHead.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_NIGHT_HELMET, PersistentDataType.BYTE)) {
                Armour.nightHelmetEffect(player);
            }
        }

        if (player.getPersistentDataContainer().has(Keys.RACE_CARA) && LogicHolder.isPlayerAboveGround(player, 0.5)
        && !player.isClimbing() && !isInWater) {
            RaceEffects.caraGlideEvent(player);
        }else{
            RaceEffects.stopCaraGlideTask(player);
        }
        
    }
    
        //
        //      Food effects
        //
 

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
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
        PersistentDataContainer playerContainer;
        
        boolean isInWater = LogicHolder.isPlayerInWater(player);
        
        if (itemLeggings == null || (!isInWater && LogicHolder.isRaining(player.getWorld()))) {
            Armour.stopMermaidTailTask(player);
        }else if (itemLeggings.hasItemMeta()) {
            playerContainer = itemLeggings.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_MERMAID_TAIL, PersistentDataType.BYTE) && (player.isSwimming() || isInWater || LogicHolder.isRaining(player.getWorld()))) {
                Armour.mermaidTailEffect(player);
            }else{
                Armour.stopMermaidTailTask(player);
            }
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


        if (!isInWater) {
            Armour.stopMermaidTailTask(player);
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
}
