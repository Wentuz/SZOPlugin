package wentuziak.szoplugin;


import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

        //PersistentDataContainer entityContainer = entity.getPersistentDataContainer();
        //PersistentDataContainer playerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();


        boolean clickedRightButton;

        // Detect left-click (can be left-clicking air or a block)
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            clickedRightButton = false;
        }   else{
            clickedRightButton = true;
        }

        if (clickedRightButton) {
            SpecialItems.simpleItemFunc(player, itemInMainHand, itemInOffHand);
        }

        //
        //      META ITEMS
        //

        // Magic for main hand only
        if (itemInMainHand != null && itemInMainHand.hasItemMeta()) {
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
        if (itemInOffHand != null && itemInOffHand.hasItemMeta()) {
            PersistentDataContainer playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_IRON_BREAKER_SHIELD, PersistentDataType.BYTE) && clickedRightButton) {
                if (!(player.isHandRaised())) {
                    CustomTools.effectRaisedShieldFunc(player, 3);
                    return;
                }
            }
            if (playerContainer.has(Keys.CUSTOM_BERSERKER_SHIELD, PersistentDataType.BYTE) && clickedRightButton) {
                if (!(player.isHandRaised())) {
                    CustomTools.effectRaisedShieldFunc(player, 2);
                    return;
                }
            }

        }
    }


    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemOnFeet = player.getInventory().getItem(EquipmentSlot.FEET);
        ItemStack itemOnChest = player.getInventory().getItem(EquipmentSlot.CHEST);

        System.out.println("SNEAK");

        PersistentDataContainer playerContainer;
        if (itemOnChest != null && itemOnChest.hasItemMeta()) {
            playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_GOLEM_CHEST, PersistentDataType.BYTE)) {
                Armour.golemChestFunc(player);
            }
        }
    
        if (itemOnFeet != null && itemOnFeet.hasItemMeta()) {
            playerContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_JET_BOOTS, PersistentDataType.BYTE)) {
                Armour.jetBootsFunc(player);
            }
        }
        
    }
    
        //
        //      Food effects
        //
 

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event)
    {
        Player player = event.getPlayer();
        ItemStack consumedItem = event.getItem();
        ItemStack itemOnLeg = player.getInventory().getItem(EquipmentSlot.LEGS);

        if (itemOnLeg.hasItemMeta()) {
            PersistentDataContainer playerContainer = itemOnLeg.getItemMeta().getPersistentDataContainer();

            if (playerContainer.has(Keys.CUSTOM_GLUTTONY_PANTS, PersistentDataType.BYTE)) {
                Armour.gluttonyPantsFunc(player);
            }
        }


        SpecialFood.effectFoodFunc(player, consumedItem.getType());
    }


        //
        //      On hit effects
        //


    @SuppressWarnings("deprecation")
    @EventHandler
    public void onEntityDamageEntity(EntityDamageByEntityEvent event)
    {   

        if (event.getDamager() instanceof Player) {

            Player player = (Player) event.getDamager();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            
            if (itemInMainHand == null || itemInMainHand.getType() == Material.AIR) {
                return;
            }
            LivingEntity hitEntity = (LivingEntity) event.getEntity();
            PersistentDataContainer playerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();
            

            if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BYTE)) {
                Weapons.explosiveSwordFunc(33, hitEntity);
                return;
            }
            if (playerContainer.has(Keys.CUSTOM_THUNDER_HAMMER, PersistentDataType.BYTE)) {
                Weapons.thunderHammerFunc(40, hitEntity);
                return;
            }
            if (playerContainer.has(Keys.CUSTOM_DAEMON_SWORD, PersistentDataType.BYTE)) {
                Weapons.daemonSwordFunc(22, hitEntity);
                return;
            }
            if (playerContainer.has(Keys.CUSTOM_ANGEL_SWORD, PersistentDataType.BYTE)) {
                Weapons.angelSwordFunc(22, player);
                return;
            }
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack itemOnChest = player.getInventory().getItem(EquipmentSlot.CHEST);

            LivingEntity damager = (LivingEntity) event.getDamager();

            if (!(itemOnChest.hasItemMeta())) {
                return;
            }

            PersistentDataContainer playerContainer;
            if (itemOnChest.hasItemMeta()) {
                playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
                if (playerContainer.has(Keys.CUSTOM_EXPLOSIVE_CHEST, PersistentDataType.BYTE)) {
                    Armour.explosiveChestFunc(20 ,damager, player);
                    return;
                }
            }

        }
    }

        //
        //      On item held
        //
    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event)
    {
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
                CustomTools.hastyToolFunc(player);
                return;
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
                CustomTools.treasureFishingRodFunc(66 ,player, projectile, luckLvl);
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
                    return;
                }
                CustomTools.dwarfPickaxeFunc(11, player, luckLvl, brokenBlock);
                return;
            }
        }
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        ItemStack itemLeggings = player.getInventory().getLeggings();
        PersistentDataContainer playerContainer;

        if (itemLeggings == null) {
            Armour.stopMermaidTailTask();

            return;
        }

        boolean isInWater = LogicHolder.isPlayerInWater(player);

        if (!isInWater) {
            Armour.stopMermaidTailTask();
        }

        if (itemLeggings.hasItemMeta()) {
            playerContainer = itemLeggings.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_MERMAID_TAIL, PersistentDataType.BYTE)) {
                if (isInWater && Armour.mermaidTailTask == null) {
                    Armour.mermaidTailFunc(player);
                }
            }
                //CustomTools.dwarfPickaxeFunc(11, player, luckLvl, brokenBlock);
        }
    }
}
