package wentuziak.szoplugin;


import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.Location;





public class InteractionListener implements Listener{


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();

        Material getMainHandMaterial = itemInMainHand.getType();
        Material getOffHandMaterial = itemInOffHand.getType();

        // if (itemInMainHand == itemInOffHand && itemInMainHand == null) {
        //     return;
        // }
        
        Block targetBlock = player.getTargetBlock(null, 50);

        //PersistentDataContainer entityContainer = entity.getPersistentDataContainer();
        //PersistentDataContainer playerContainer = player.getItemInHand().getItemMeta().getPersistentDataContainer();


        boolean clickedRightButton;

        // Detect left-click (can be left-clicking air or a block)
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            clickedRightButton = false;
        }   else{
            clickedRightButton = true;
        }


        //System.out.println("CLICKED BUTTON RIGHT: " + clickedRightButton);
        //System.out.println("CLICKED: " + usedItem);
        //System.out.println("LOOK DIRECTION: " + lookDirection);

        
        //
        //      Usable items
        //


        if ((getMainHandMaterial == Material.FIRE_CHARGE || getOffHandMaterial == Material.FIRE_CHARGE) && clickedRightButton) {
            LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20, 3);
            player.getWorld().createExplosion(player.getLocation(), 4F, false, false);

            if (getMainHandMaterial != Material.FIRE_CHARGE) {
                LogicHolder.removeItem(player, itemInOffHand);
            }else{
                LogicHolder.removeItem(player, itemInMainHand);
            }
        }

        if ((getMainHandMaterial == Material.MAGMA_CREAM || getOffHandMaterial == Material.MAGMA_CREAM) && clickedRightButton) {
            LogicHolder.givePotionEffect(player, "FIRE_RESISTANCE", 400, 0);
            LogicHolder.givePotionEffect(player, "POISON", 200, 1);

            player.playSound(player.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 10, 10);
            if (getMainHandMaterial != Material.MAGMA_CREAM) {
                LogicHolder.removeItem(player, itemInOffHand);
            }else{
                LogicHolder.removeItem(player, itemInMainHand);
            }
        }

        if ((getMainHandMaterial == Material.PHANTOM_MEMBRANE || getOffHandMaterial == Material.PHANTOM_MEMBRANE) && clickedRightButton) {
            LogicHolder.givePotionEffect(player, "SLOW_FALLING", 200, 0);
           
            player.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_HURT, 10, 10);
            if (getMainHandMaterial != Material.PHANTOM_MEMBRANE) {
                LogicHolder.removeItem(player, itemInOffHand);
            }else{
                LogicHolder.removeItem(player, itemInMainHand);
            }
        }

        //
        //      META ITEMS
        //

        if (itemInMainHand != null && itemInMainHand.hasItemMeta()) {
            PersistentDataContainer playerContainer = itemInMainHand.getItemMeta().getPersistentDataContainer();

            Location blockLocation = targetBlock.getLocation();

            int blockX = blockLocation.getBlockX();
            int blockY = blockLocation.getBlockY();
            int blockZ = blockLocation.getBlockZ();

            if (playerContainer.has(Keys.CUSTOM_TELEPORT_SPELL, PersistentDataType.BYTE) && clickedRightButton) {
                //player.sendMessage("U are lookin : " + targetBlock.getLocation().toString());

                LogicHolder.givePotionEffect(player, "HUNGER", 200, 9);
                Location targetLocation = new Location(player.getWorld(), blockX, blockY + 1, blockZ);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 10);
                player.teleport(targetLocation);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 10);

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
        Material consumedItemType = consumedItem.getType();
        //System.out.println("Consumed item: " + consumedItem);

        SpecialFood.effectFoodFunc(player, consumedItemType);

        // if (consumedItemType == Material.GLOW_BERRIES) {
        //     LogicHolder.givePotionEffect(player, "GLOWING", 400, 0);
        //     LogicHolder.givePotionEffect(player, "NIGHT_VISION", 400, 0);
        // }

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
            }

            if (playerContainer.has(Keys.CUSTOM_THUNDER_HAMMER, PersistentDataType.BYTE)) {
                Weapons.thunderHammerFunc(40, hitEntity);
            }


            //player.sendMessage("You hit an entity: " + hitEntity + itemInHand);
        }
    }

}
