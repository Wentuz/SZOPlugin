package wentuziak.szoplugin;
import org.bukkit.Material;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EntityListener implements Listener {

    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent event)
    {
        //System.out.println("Right clicked: " + event.getRightClicked().getClass());

        if (event.getRightClicked() instanceof Cow /*&& entity.hasMetadata("SzoPlugin") && player.getItemInHand().getType() == Material.BUCKET*/) {
            Cow cow = (Cow) event.getRightClicked();

            cow.getWorld().createExplosion(cow.getLocation(), 2.5F);
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
