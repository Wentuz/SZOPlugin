package wentuziak.szoplugin;



import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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

        if (clickedRightButton) {
            SpecialItems.simpleItemEffect(player, itemInMainHand, itemInOffHand);
                    //
                    //      RACE HAND CRAFTING
                    //
            if ((event.getHand() == EquipmentSlot.HAND && itemInMainHand.getType() != Material.AIR && itemInOffHand.getType() != Material.AIR)) {
                if (player.getPersistentDataContainer().has(Keys.RACE_DWARF)) {
                    RaceEffects.dwarfCraftingEvent(player, itemInMainHand, itemInOffHand);
                } else if(player.getPersistentDataContainer().has(Keys.RACE_WITCH)){
                    RaceEffects.witchCraftingEvent(player, itemInMainHand, itemInOffHand);
                }
            }
        }

        //
        //      META ITEMS
        //

        // Magic for main hand only
        if (itemInMainHand.getType() != Material.AIR && itemInMainHand.hasItemMeta()) {
            PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_TELEPORT_SPELL, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.teleportSpell(player);
                return;
            }
            if (playerContainer.has(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.BYTE) && clickedRightButton) {
                MagicItems.spiritLeech(player);
                return;
            }
        }

        // Off hand items only
        if (itemInOffHand.getType() != Material.AIR && itemInOffHand.hasItemMeta()) {
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


        PersistentDataContainer playerContainer;
        if (itemOnChest != null && itemOnChest.hasItemMeta()) {
            playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_GOLEM_CHEST, PersistentDataType.BYTE)) {
                Armour.golemChestEffect(player);
            }
        }
    
        if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
            playerContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_JET_BOOTS, PersistentDataType.BYTE)) {
                Armour.jetBootsEffect(player);
            }
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
            
            if (itemInMainHand == null || itemInMainHand.getType() == Material.AIR) {
                return;
            }

            LivingEntity hitEntity = (LivingEntity) event.getEntity();
            PersistentDataContainer playerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();
            

            if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BYTE)) {
                Weapons.explosiveSwordEffect(33, hitEntity);
                return;
            }
            if (playerContainer.has(Keys.CUSTOM_THUNDER_HAMMER, PersistentDataType.BYTE)) {
                Weapons.thunderHammerEffect(40, hitEntity);
                return;
            }
            if (playerContainer.has(Keys.CUSTOM_DAEMON_SWORD, PersistentDataType.BYTE)) {
                Weapons.daemonSwordEffect(22, hitEntity);
                return;
            }
            if (playerContainer.has(Keys.CUSTOM_ANGEL_SWORD, PersistentDataType.BYTE)) {
                Weapons.angelSwordEffect(22, player);
                return;
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
                playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
                if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_CHEST, PersistentDataType.BYTE)) {
                    Armour.explosiveChestEffect(20 ,damager, player);
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
            CustomTools.stopHastyToolTask();
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
        PersistentDataContainer playerContainer;
        Projectile projectile = event.getHook();
        int luckLvl = itemInMainHand.getEnchantmentLevel(Enchantment.FORTUNE);

        if (event.getState() == State.CAUGHT_FISH) {
            if (!(itemInMainHand.hasItemMeta())) {
                return;
            }
            playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();

            if (playerContainer.has(Keys.CUSTOM_TREASURE_FISHING, PersistentDataType.BYTE)) {
                CustomTools.treasureFishingRodEffect(66 ,player, projectile, luckLvl, "Ore");
                System.out.println(luckLvl);
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
                CustomTools.dwarfPickaxeEffect(11, player, luckLvl, brokenBlock, "Ore");
            }
        }

        if (player.getPersistentDataContainer().has(Keys.RACE_DWARF)) {
            CustomTools.dwarfPickaxeEffect(5, player, 1, brokenBlock, "Ore");
        }
        if (player.getPersistentDataContainer().has(Keys.RACE_WITCH) && 
        (brokenBlock.getType() == Material.SHORT_GRASS || brokenBlock.getType() == Material.TALL_GRASS
        || brokenBlock.getType() == Material.FERN || brokenBlock.getType() == Material.LARGE_FERN )) {
            if (LogicHolder.critRoll(5 * (luckLvl + 1))) {
                LogicHolder.rollTreasure((luckLvl / 2) + 1, brokenBlock.getLocation(), "Plant");
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
        PersistentDataContainer playerContainer;
        
        boolean isInWater = LogicHolder.isPlayerInWater(player);
        
        if (itemLeggings == null) {
            Armour.stopMermaidTailTask();
        }else if (itemLeggings.hasItemMeta()) {
            playerContainer = itemLeggings.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_MERMAID_TAIL, PersistentDataType.BYTE)) {
                if (isInWater && Armour.mermaidTailTask == null) {
                    Armour.mermaidTailEffect(player);
                }
            }
        }

        if (!isInWater) {
            Armour.stopMermaidTailTask();
            RaceEffects.stopDwarfSwimTask();
        }
        if ((player.getPersistentDataContainer().has(Keys.RACE_DWARF) && isInWater)) {
            RaceEffects.dwarfSwimEvent(player);
        }
    }

    @EventHandler
    public void onPlayerShootArrow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player && event.getProjectile() instanceof Arrow) {
            Player player = (Player) event.getEntity();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

            if (itemInMainHand.hasItemMeta()) {
                PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();
                Arrow arrow = (Arrow) event.getProjectile();
                if (itemInMainHand.getType() == Material.BOW) {
                    if (playerContainer.has(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.BYTE)) {
                        arrow.getPersistentDataContainer().set(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.STRING, "antiGravArrow");
                    }
                    if (playerContainer.has(Keys.CUSTOM_RAT_BOW, PersistentDataType.BYTE)) {
                        System.out.println("CUSTOM RAT BOW WOWOW");
                        arrow.getPersistentDataContainer().set(Keys.CUSTOM_RAT_BOW, PersistentDataType.STRING, "ratArrow");
                    }
                }
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
    }
}
