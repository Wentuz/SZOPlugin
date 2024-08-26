package wentuziak.szoplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RaceCrafting {

    // FOSSIL
    public static void fossilCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.NAUTILUS_SHELL && offHandMaterial == Material.PRISMARINE_CRYSTALS) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createAncientShell());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
    }

    // MEWCHANT
    public static void mewchantCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.CLOCK && itemInOffHand.isSimilar(CreateCustomItem.createMechanicalParts())) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createLuckyClock());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if (mainHandMaterial == Material.SPYGLASS && offHandMaterial == Material.REDSTONE_TORCH) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createMarkingSpyglass());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if ((mainHandMaterial == Material.GUNPOWDER && itemInMainHand.getAmount() >= 4) && offHandMaterial == Material.BLACK_DYE){
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createSmokeBomb());
            
            LogicHolder.removeItem(player, itemInOffHand);
            for(int i = 0; i < 4; i++){
                LogicHolder.removeItem(player, itemInMainHand);
            }
            return;
        }
        else if ((itemInMainHand.isSimilar(CreateCustomItem.createSoulEssence()) && itemInMainHand.getAmount() >= 8) && offHandMaterial == Material.ARROW){
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createParalyzingArrow());
            
            LogicHolder.removeItem(player, itemInOffHand);
            for(int i = 0; i < 8; i++){
                LogicHolder.removeItem(player, itemInMainHand);
            }
            return;
        }
    }
       
    // CARA
    public static void caraCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.FEATHER && itemInOffHand.isSimilar(CreateCustomItem.createSoulEssence())) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createWindCharm());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
    }
       
    // SANGUINITE
    public static void sanguiniteCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.PAPER && itemInOffHand.isSimilar(CreateCustomItem.createSoulEssence()) && itemInOffHand.getAmount() >= 4) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createSanguiniteScroll());
            
            for(int i = 0; i < 8; i++){
                LogicHolder.removeItem(player, itemInOffHand);
            }
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
    }

    // WITCH
    public static void witchCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.SUSPICIOUS_STEW && offHandMaterial == Material.NETHER_WART) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createWitchSoup());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.FERMENTED_SPIDER_EYE && offHandMaterial == Material.ARROW && itemInOffHand.getAmount() >= 4) {
            for(int i = 0; i < 4; i++){
                player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createCursedArrow());
                LogicHolder.removeItem(player, itemInOffHand);
            }
            player.sendMessage("HERE");

            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.GHAST_TEAR && offHandMaterial == Material.GLISTERING_MELON_SLICE) {
            player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createSuperHealingPot());
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.PUFFERFISH && offHandMaterial == Material.FERMENTED_SPIDER_EYE) {
            player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createToxicPot());
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.IRON_INGOT && itemInOffHand.isSimilar(CreateCustomItem.createSoulEssence())) {
            player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createIronHide());
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.SUGAR && itemInOffHand.isSimilar(CreateCustomItem.createSoulEssence())) {
            player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createGepardPotion());
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.PUFFERFISH && itemInOffHand.isSimilar(CreateCustomItem.createSoulEssence())) {
            player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createParalyzingGas());
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
    }

    // DWARF
    
    public static void dwarfCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.HONEY_BOTTLE && offHandMaterial == Material.GOLDEN_APPLE) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createDwarfHoney());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if (mainHandMaterial == Material.SPYGLASS && offHandMaterial == Material.REDSTONE_TORCH) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createMarkingSpyglass());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if (mainHandMaterial == Material.STICK && itemInOffHand.isSimilar(CreateCustomItem.createMechanicalParts())) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createEssencePicker());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if (mainHandMaterial == Material.LAPIS_LAZULI && itemInOffHand.isSimilar(CreateCustomItem.createSoulEssence())) {
            ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE, 6);

            player.getWorld().dropItem(dropLocation, item);
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if (mainHandMaterial == Material.CLOCK && itemInOffHand.isSimilar(CreateCustomItem.createMechanicalParts())) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createLuckyClock());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if ((mainHandMaterial == Material.GUNPOWDER && itemInMainHand.getAmount() >= 8) && offHandMaterial == Material.STRING){
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createGrenade());
            
            LogicHolder.removeItem(player, itemInOffHand);
            for(int i = 0; i < 8; i++){
                LogicHolder.removeItem(player, itemInMainHand);
            }
            return;
        }
        else if (itemInMainHand.isSimilar(CreateCustomItem.createGrenade()) 
        && (itemInOffHand.isSimilar(CreateCustomItem.createSoulEssence()) && itemInOffHand.getAmount() >= 6)){
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createBreachCharge());
            
            LogicHolder.removeItem(player, itemInMainHand);
            for(int i = 0; i < 6; i++){
                LogicHolder.removeItem(player, itemInOffHand);
            }
            return;
        }
    }
}
