package wentuziak.szoplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public final class CustomRecipes {

    public static void register()
    {
        //
        //      Soul Fragment Recipe
        //
        
        ItemStack soulFragment = CreateCustomItem.createSoulFragment();
        
        ShapelessRecipe soulFragmentRecipe = new ShapelessRecipe(new NamespacedKey(SzoPlugin.getInstance(), "SoulFragmentRecipe"), soulFragment);
        soulFragmentRecipe.addIngredient(2, Material.DRAGON_BREATH);
        soulFragmentRecipe.addIngredient(1, Material.NETHER_STAR);
        Bukkit.addRecipe(soulFragmentRecipe);

        
        //
        //      Mechanical Parts Recipe
        //
        ItemStack mechanicalParts = CreateCustomItem.createMechanicalParts();

        ShapedRecipe mechanicalPartsRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "MechanicalPartsRecipe"), mechanicalParts);
        mechanicalPartsRecipe.shape(
            " R ",
            "CNC",
            "   ");
            mechanicalPartsRecipe.setIngredient('N', Material.NETHERITE_SCRAP);
            mechanicalPartsRecipe.setIngredient('R', Material.REPEATER);
            mechanicalPartsRecipe.setIngredient('C', Material.COPPER_INGOT);
        Bukkit.addRecipe(mechanicalPartsRecipe);

        
        //
        //      Angel Sword Recipe
        //
        ItemStack angelSword = CreateCustomItem.createAngelSword();

        ShapedRecipe angelSwordRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "AngelSwordRecipe"), angelSword);
        angelSwordRecipe.shape(
        " D ",
            " D ",
            "GSG");
            angelSwordRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            angelSwordRecipe.setIngredient('G', Material.GOLDEN_APPLE);
            angelSwordRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(angelSwordRecipe);

        
        //
        //      Daemon Sword Recipe
        //
        ItemStack daemonSword = CreateCustomItem.createDaemonSword();

        ShapedRecipe daemonSwordRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "DaemonSwordRecipe"), daemonSword);
        daemonSwordRecipe.shape(
            " D ",
            " D ",
            "BSB");
            daemonSwordRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            daemonSwordRecipe.setIngredient('B', Material.BLAZE_POWDER);
            daemonSwordRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(daemonSwordRecipe);


        //
        //      Pyromancer's Sword Recipe
        //
        ItemStack pyromancerSword = CreateCustomItem.createPyromancerSword();

        ShapedRecipe pyromancerSwordRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "PyromancerSwordRecipe"), pyromancerSword);
        pyromancerSwordRecipe.shape(
            " D ",
            " D ",
            "TST");
            pyromancerSwordRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            pyromancerSwordRecipe.setIngredient('T', Material.TNT);
            pyromancerSwordRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(pyromancerSwordRecipe);
        
        
        //
        //      Þrumuhamar Recipe
        //
        ItemStack thunderHammer = CreateCustomItem.createThunderHammer();

        ShapedRecipe thunderHammerRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "ThunderHammerRecipe"), thunderHammer);
        thunderHammerRecipe.shape(
            "DS ",
            "DC ",
            " B ");
            thunderHammerRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            thunderHammerRecipe.setIngredient('B', Material.BONE);
            thunderHammerRecipe.setIngredient('C', Material.COPPER_INGOT);
            thunderHammerRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(thunderHammerRecipe);
        
        
        //
        //      IronBreakerShield Recipe
        //
        ItemStack ironBreakerShield = CreateCustomItem.createIronBreakerShield();

        ShapedRecipe ironBreakerShieldRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "IronBreakerShieldRecipe"), ironBreakerShield);
        ironBreakerShieldRecipe.shape(
            "I I",
            "IMI",
            " I ");
            ironBreakerShieldRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            ironBreakerShieldRecipe.setIngredient('I', Material.IRON_INGOT);
        Bukkit.addRecipe(ironBreakerShieldRecipe);
        
        
        //
        //      Hermes's boots Recipe
        //
        ItemStack hermesBoots = CreateCustomItem.createHermesBoots();

        ShapedRecipe hermesBootsRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "HermesBootsRecipe"), hermesBoots);
        hermesBootsRecipe.shape(
            "   ",
            "FMF",
            "D D");
            hermesBootsRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            hermesBootsRecipe.setIngredient('F', Material.FEATHER);
            hermesBootsRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(hermesBootsRecipe);
        
        
        //
        //      Jet boots Recipe
        //
        ItemStack jetBoots = CreateCustomItem.createJetBoots();

        ShapedRecipe jetBootsRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "JetBootsRecipe"), jetBoots);
        jetBootsRecipe.shape(
            "   ",
            "DMD",
            "P P");
            jetBootsRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            jetBootsRecipe.setIngredient('P', Material.PHANTOM_MEMBRANE);
            jetBootsRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(jetBootsRecipe);
        
        
        //
        //      Golem Chest Recipe
        //
        ItemStack golemChest = CreateCustomItem.createGolemChest();

        ShapedRecipe golemChestRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "GolemChestRecipe"), golemChest);
        golemChestRecipe.shape(
            "I I",
            "IMI",
            "IDI");
            golemChestRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            golemChestRecipe.setIngredient('I', Material.IRON_INGOT);
            golemChestRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.addRecipe(golemChestRecipe);
        
        
        //
        //      Explosive Chest Recipe
        //
        ItemStack explosiveChest = CreateCustomItem.createExplosiveChest();

        ShapedRecipe explosiveChestRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "ExplosiveChestRecipe"), explosiveChest);
        explosiveChestRecipe.shape(
            "D D",
            "TST",
            "DDD");
            explosiveChestRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            explosiveChestRecipe.setIngredient('T', Material.TNT);
            explosiveChestRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(explosiveChestRecipe);


        //
        //      Teleport Spell recipe
        //
        ItemStack teleportSpell = CreateCustomItem.createTeleportSpell();

        ShapedRecipe teleportSpellRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "teleportSpellRecipe"), teleportSpell);
        teleportSpellRecipe.shape(
            " P ",
            "PSP",
            " P ");
            teleportSpellRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            teleportSpellRecipe.setIngredient('P', Material.ENDER_PEARL);
        Bukkit.addRecipe(teleportSpellRecipe);


        //
        //      Hasty Shovel recipe
        //
        ItemStack hastyShovel = CreateCustomItem.createHastyShovel();
        ShapedRecipe hastyShovelRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "hastyShovelRecipe"), hastyShovel);
        hastyShovelRecipe.shape(
            " D ",
            " M ",
            " S ");
            hastyShovelRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            hastyShovelRecipe.setIngredient('D', Material.DIAMOND);
            hastyShovelRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(hastyShovelRecipe);


        //
        //      Hasty Axe recipe
        //
        ItemStack hastyAxe = CreateCustomItem.createHastyAxe();
        ShapedRecipe hastyAxeRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "hastyAxeRecipe"), hastyAxe);
        hastyAxeRecipe.shape(
            "DM ",
            "DS ",
            " S ");
            hastyAxeRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            hastyAxeRecipe.setIngredient('D', Material.DIAMOND);
            hastyAxeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(hastyAxeRecipe);


        //
        //      Hasty Pickaxe recipe
        //
        ItemStack hastyPickaxe = CreateCustomItem.createHastyPickaxe();
        ShapedRecipe hastyPickaxeRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "hastyPickRecipe"), hastyPickaxe);
        hastyPickaxeRecipe.shape(
            "DMD",
            " S ",
            " S ");
            hastyPickaxeRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            hastyPickaxeRecipe.setIngredient('D', Material.DIAMOND);
            hastyPickaxeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(hastyPickaxeRecipe);


        //
        //      Dwarf Pickaxe recipe
        //
        ItemStack dwarfPickaxe = CreateCustomItem.createDwarfPickaxe();
        ShapedRecipe dwarfPickaxeRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "dwarfPickaxeRecipe"), dwarfPickaxe);
        dwarfPickaxeRecipe.shape(
            "DFD",
            " M ",
            " S ");
            dwarfPickaxeRecipe.setIngredient('F', new RecipeChoice.ExactChoice(soulFragment));
            dwarfPickaxeRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            dwarfPickaxeRecipe.setIngredient('D', Material.DIAMOND);
            dwarfPickaxeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(dwarfPickaxeRecipe);
    }
}
