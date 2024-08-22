package wentuziak.szoplugin;



import java.security.Key;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
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
                    RaceEffects.dwarfCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_WITCH)){
                    RaceEffects.witchCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_MEWCHANT)){
                    RaceEffects.mewchantCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_FOSSIL)){
                    RaceEffects.fossilCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
                if(player.getPersistentDataContainer().has(Keys.RACE_CARA)){
                    RaceEffects.caraCraftingEvent(player, itemInMainHand, itemInOffHand);
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
        //      On hit effects
        //


    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event){   
        if (event.getDamager() instanceof Player) {

            Player player = (Player) event.getDamager();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            PersistentDataContainer playerContainer;
            
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
                    Weapons.daemonSwordEffect(22, hitEntity);
                }
                if (playerContainer.has(Keys.CUSTOM_ANGEL_SWORD, PersistentDataType.BYTE)) {
                    Weapons.angelSwordEffect(22, player);
                }
                if (playerContainer.has(Keys.CUSTOM_ARMOR_PIERCER, PersistentDataType.BYTE)) {
                    Weapons.armorPiercerEffect(hitEntity, sharpLvl + 1);
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

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack itemOnChest = player.getInventory().getItem(EquipmentSlot.CHEST);

            
            if (itemOnChest == null || !itemOnChest.hasItemMeta()) {
                return;
            }

            LivingEntity damager = (LivingEntity) event.getDamager();
            PersistentDataContainer playerContainer;
            
            if (itemOnChest.hasItemMeta()) {
                int thornLvl = itemOnChest.getEnchantmentLevel(Enchantment.THORNS);
                playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
                if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_CHEST, PersistentDataType.BYTE)) {
                    Armour.explosiveChestEffect(20 ,damager, player);
                }else if(playerContainer.has(Keys.CUSTOM_REFLECTIVE_CHESTPIECE, PersistentDataType.BYTE)){
                    Armour.reflectiveChestEffect(25, thornLvl ,damager);
                }
            }
        }
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
    //      Mining Events
    //
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
        PersistentDataContainer playerContainer;
        Block brokenBlock = event.getBlock();
        int luckLvl = itemInMainHand.getEnchantmentLevel(Enchantment.FORTUNE);


        if (itemInMainHand.hasItemMeta()) {
            playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_DWARF_PICK, PersistentDataType.BYTE)) {
                int isDumb = itemInMainHand.getEnchantmentLevel(Enchantment.SILK_TOUCH);
                if (isDumb == 1) {
                    System.out.println(isDumb);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 10);
                }
                CustomTools.dwarfPickaxeEffect(8 * luckLvl, player, luckLvl, brokenBlock, "Ore");
            }
            else if (playerContainer.has(Keys.CUSTOM_RICH_AX, PersistentDataType.BYTE)) {
                CustomTools.richAxeEffect(luckLvl, brokenBlock);
            }
            //  Bootleg netherite pickaxe soul essence from diamonds
            else if (playerContainer.has(Keys.CUSTOM_ESSENCE_PICKER, PersistentDataType.BYTE)) {
                if (LogicHolder.critRoll((luckLvl + 1) * 25)) {
                    if ((brokenBlock.getType() == Material.DEEPSLATE_DIAMOND_ORE || brokenBlock.getType() == Material.DIAMOND_ORE)) {
                        for(int i = 3 - luckLvl; i < 4; i++){
                            if (LogicHolder.critRoll((luckLvl + 1) * 20)) {
                                brokenBlock.getLocation().getWorld().dropItemNaturally(brokenBlock.getLocation(), CreateCustomItem.createSoulEssence());
                            }
                        }
                        brokenBlock.getLocation().getWorld().dropItemNaturally(brokenBlock.getLocation(), CreateCustomItem.createSoulEssence());
                    }
                }
                return;
            }else if (playerContainer.has(Keys.CUSTOM_RICH_SHOVEL, PersistentDataType.BYTE)) {
                CustomTools.richShovelEffect(luckLvl, brokenBlock);
            }
        

        if (itemInOffHand.hasItemMeta()) {
            playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_LUCKY_CLOCK, PersistentDataType.BYTE)) {
                CustomTools.dwarfPickaxeEffect(4 * luckLvl, player, luckLvl - 1, brokenBlock, "Ore");
            }
        }

        if (player.getPersistentDataContainer().has(Keys.RACE_DWARF)) {
            CustomTools.dwarfPickaxeEffect(4 * luckLvl, player, 1 * luckLvl, brokenBlock, "Ore");
        }
        if ((brokenBlock.getType() == Material.SHORT_GRASS || brokenBlock.getType() == Material.TALL_GRASS
        || brokenBlock.getType() == Material.FERN || brokenBlock.getType() == Material.LARGE_FERN )) {
            if (LogicHolder.critRoll(5 * (luckLvl + 1)) && player.getPersistentDataContainer().has(Keys.RACE_WITCH)) {
                LogicHolder.rollTreasure(1, brokenBlock.getLocation(), "Plant");
            }
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
        
        if (itemLeggings == null || !isInWater) {
            Armour.stopMermaidTailTask(player);
        }else if (itemLeggings.hasItemMeta()) {
            playerContainer = itemLeggings.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_MERMAID_TAIL, PersistentDataType.BYTE) && (player.isSwimming() || isInWater)) {
                Armour.mermaidTailEffect(player);
            }else{
                Armour.stopMermaidTailTask(player);
            }
        }
        if (itemInOffHand.hasItemMeta()) {
            playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_ANCIENT_SHELL, PersistentDataType.BYTE) && (player.isSwimming() || isInWater)) {
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
    public void onPlayerShootArrow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player && event.getProjectile() instanceof Arrow) {
            Player player = (Player) event.getEntity();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

            if (itemInMainHand.hasItemMeta()) {
                PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
                Arrow arrow = (Arrow) event.getProjectile();
                if (itemInMainHand.getType() == Material.BOW) {
                    if (playerContainer.has(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.BYTE)) {
                        arrow.getPersistentDataContainer().set(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.STRING, "antiGravArrow");
                    }
                    if (playerContainer.has(Keys.CUSTOM_RAT_BOW, PersistentDataType.BYTE)) {
                        arrow.getPersistentDataContainer().set(Keys.CUSTOM_RAT_BOW, PersistentDataType.STRING, "ratArrow");
                    }
                }
                if (itemInMainHand.getType() == Material.CROSSBOW) {
                    if (playerContainer.has(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.BYTE)) {
                        arrow.getPersistentDataContainer().set(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.STRING, "bouncyArrow");
                    }
                }
            }
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
