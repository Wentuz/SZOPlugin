package wentuziak.szoplugin;
import org.bukkit.Material;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;


public class EntityListener implements Listener {


    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event)
    {

        if (event.getHand() == EquipmentSlot.OFF_HAND) {
             return;
        }
        
        //System.out.println("Right clicked: " + event.getRightClicked().getClass());

        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        System.out.println("Item in hand: " + itemInHand.getType());


        if (entity instanceof Cow && entity.hasMetadata("SzoPlugin") == true && itemInHand.getType() == Material.BUCKET) {
            Cow cowClicked = (Cow) event.getRightClicked();
            ItemStack itemToDrop = new ItemStack(Material.GUNPOWDER);
            int amountToDrop = (int)(Math.random() * 10 + 1);
            itemToDrop.setAmount(amountToDrop);

            cowClicked.getWorld().createExplosion(cowClicked.getLocation(), 2.5F);
            cowClicked.getWorld().dropItem(cowClicked.getLocation(), itemToDrop);

        }

        if (event.getRightClicked().getType() == EntityType.CHICKEN) {
            LivingEntity chickenClicked = (LivingEntity) event.getRightClicked();
            ItemStack itemToDrop = new ItemStack(Material.FEATHER);
            double damageAmount = 2.0;


            chickenClicked.damage(damageAmount);
            if (!chickenClicked.isDead()) {
                chickenClicked.getWorld().dropItem(chickenClicked.getLocation(), itemToDrop);
            }
        }
    }
}
