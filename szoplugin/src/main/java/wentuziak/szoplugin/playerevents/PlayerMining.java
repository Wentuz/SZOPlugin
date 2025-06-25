package wentuziak.szoplugin.playerevents;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.customcrafting.CreateCustomItem;
import wentuziak.szoplugin.customitems.CustomTools;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.customlogic.RandomLoot;


public class PlayerMining implements Listener{
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
            if(playerContainer.has(Keys.CUSTOM_DWARF_UPGRADE, PersistentDataType.BYTE)) luckLvl += 1; // dwarf upgrade +1 luck

            if (playerContainer.has(Keys.CUSTOM_DWARF_PICK, PersistentDataType.BYTE)) {
                int isDumb = itemInMainHand.getEnchantmentLevel(Enchantment.SILK_TOUCH);
                if (isDumb == 1) {
                    System.out.println(isDumb);
                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 10);
                }
                CustomTools.dwarfPickaxeEffect(8 * luckLvl, player, luckLvl - 1, brokenBlock);
            }
            else if (playerContainer.has(Keys.CUSTOM_RICH_AX, PersistentDataType.BYTE)) {
                CustomTools.richAxeEffect(luckLvl, brokenBlock);
            }
            else if (playerContainer.has(Keys.CUSTOM_SUPER_HOE, PersistentDataType.BYTE) || player.getPersistentDataContainer().has(Keys.RACE_WITCH)) {
                CustomTools.superHoeEffect(luckLvl, brokenBlock);
        
            }
            //  Bootleg netherite pickaxe soul essence from diamonds
            else if (playerContainer.has(Keys.CUSTOM_ESSENCE_PICKER, PersistentDataType.BYTE)) {
                if (LogicHolder.critRoll((luckLvl + 1) * 25)) {
                    if ((brokenBlock.getType() == Material.DEEPSLATE_DIAMOND_ORE || brokenBlock.getType() == Material.DIAMOND_ORE)) {
                        for(int i = 3 - luckLvl; i < 4 + luckLvl; i++){
                            if (LogicHolder.critRoll((luckLvl * 20))) {
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
                CustomTools.dwarfPickaxeEffect(4 * luckLvl, player, luckLvl - 2, brokenBlock);
            }
        }

        if (player.getPersistentDataContainer().has(Keys.RACE_DWARF)) {
            CustomTools.dwarfPickaxeEffect(4 * luckLvl, player, luckLvl, brokenBlock);
            if(LogicHolder.critRoll(luckLvl + 1)) brokenBlock.getLocation().getWorld().dropItemNaturally(brokenBlock.getLocation(), CreateCustomItem.createSoulEssence());
            if(LogicHolder.critRoll(luckLvl)) LogicHolder.rollTreasure(luckLvl, brokenBlock.getLocation(), "Ore");
        }
        if ((brokenBlock.getType() == Material.SHORT_GRASS || brokenBlock.getType() == Material.TALL_GRASS
        || brokenBlock.getType() == Material.FERN || brokenBlock.getType() == Material.LARGE_FERN 
        || brokenBlock.getType() == Material.BUSH || brokenBlock.getType() == Material.SHORT_DRY_GRASS
        || brokenBlock.getType() == Material.TALL_DRY_GRASS)) {
            if (LogicHolder.critRoll(15 * (luckLvl + 1)) && player.getPersistentDataContainer().has(Keys.RACE_WITCH)) {
                LogicHolder.rollTreasure(luckLvl, brokenBlock.getLocation(), "Plant");
                CustomTools.superHoeEffect(luckLvl, brokenBlock);
            }
            }
        }
    }
}
