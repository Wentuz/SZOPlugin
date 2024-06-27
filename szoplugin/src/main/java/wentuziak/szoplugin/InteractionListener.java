package wentuziak.szoplugin;


import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.Action;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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


    }


    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemOnFeet = player.getInventory().getItem(EquipmentSlot.FEET);
        ItemStack itemOnChest = player.getInventory().getItem(EquipmentSlot.CHEST);

        if (!(itemOnChest.hasItemMeta()) && !(itemOnFeet.hasItemMeta())) {
            return;
        }
        PersistentDataContainer playerContainer;
        if (itemOnChest.hasItemMeta()) {
            playerContainer = itemOnChest.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_GOLEM_CHEST, PersistentDataType.BYTE)) {
                Armour.golemChestFunc(player);
                return;
            }
        }
        if (itemOnFeet.hasItemMeta()) {
            playerContainer = itemOnFeet.getItemMeta().getPersistentDataContainer();
            if (playerContainer.has(Keys.CUSTOM_JET_BOOTS, PersistentDataType.BYTE)) {
                Armour.jetBootsFunc(player);
                return;
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
}
