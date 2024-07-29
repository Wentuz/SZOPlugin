package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public final class CustomRecipes {

    private static List<NamespacedKey> recipeKeys = new ArrayList<>();

    public static List<NamespacedKey> getRecipeKeys() {
        return recipeKeys;
    }

    public static void register(){
        NamespacedKey key;

        //
        //      Soul Fragment Recipe
        //
        
        ItemStack soulFragment = CreateCustomItem.createSoulFragment();
        key = new NamespacedKey(SzoPlugin.getInstance(), "SoulFragmentRecipe");
        recipeKeys.add(key);
        
        ShapelessRecipe soulFragmentRecipe = new ShapelessRecipe(key, soulFragment);
        soulFragmentRecipe.addIngredient(1, Material.HEART_OF_THE_SEA);
        soulFragmentRecipe.addIngredient(1, Material.GHAST_TEAR);
        soulFragmentRecipe.addIngredient(1, Material.ENDER_EYE);
        soulFragmentRecipe.addIngredient(1, Material.ECHO_SHARD);
        soulFragmentRecipe.addIngredient(2, Material.LAPIS_LAZULI);
        Bukkit.addRecipe(soulFragmentRecipe);

        
        //
        //      Mechanical Parts Recipe
        //
        ItemStack mechanicalParts = CreateCustomItem.createMechanicalParts();
        key = new NamespacedKey(SzoPlugin.getInstance(), "MechanicalPartsRecipe");
        recipeKeys.add(key);

        ShapedRecipe mechanicalPartsRecipe = new ShapedRecipe(key, mechanicalParts);
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
        key = new NamespacedKey(SzoPlugin.getInstance(), "AngelSwordRecipe");
        recipeKeys.add(key);

        ShapedRecipe angelSwordRecipe = new ShapedRecipe(key, angelSword);
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
        key = new NamespacedKey(SzoPlugin.getInstance(), "DaemonSwordRecipe");
        recipeKeys.add(key);

        ShapedRecipe daemonSwordRecipe = new ShapedRecipe(key, daemonSword);
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
        key = new NamespacedKey(SzoPlugin.getInstance(), "PyromancerSwordRecipe");
        recipeKeys.add(key);

        ShapedRecipe pyromancerSwordRecipe = new ShapedRecipe(key, pyromancerSword);
        pyromancerSwordRecipe.shape(
            " D ",
            " D ",
            "TST");
            pyromancerSwordRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            pyromancerSwordRecipe.setIngredient('T', Material.TNT);
            pyromancerSwordRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(pyromancerSwordRecipe);


        //
        //      ScurvyTrident Recipe
        //
        ItemStack scurvyTrident = CreateCustomItem.createScurvyTrident();
        key = new NamespacedKey(SzoPlugin.getInstance(), "ScurvyTridentRecipe");
        recipeKeys.add(key);

        ShapedRecipe scurvyTridentRecipe = new ShapedRecipe(key, scurvyTrident);
        scurvyTridentRecipe.shape(
            " M ",
            "DTD",
            "   ");
            scurvyTridentRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            scurvyTridentRecipe.setIngredient('T', Material.TRIDENT);
            scurvyTridentRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(scurvyTridentRecipe);
        
        
        //
        //      Þrumuhamar Recipe
        //
        ItemStack thunderHammer = CreateCustomItem.createThunderHammer();
        key = new NamespacedKey(SzoPlugin.getInstance(), "ThunderHammerRecipe");
        recipeKeys.add(key);

        ShapedRecipe thunderHammerRecipe = new ShapedRecipe(key , thunderHammer);
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
        key = new NamespacedKey(SzoPlugin.getInstance(), "IronBreakerShieldRecipe");
        recipeKeys.add(key);

        ShapedRecipe ironBreakerShieldRecipe = new ShapedRecipe(key , ironBreakerShield);
        ironBreakerShieldRecipe.shape(
            "I I",
            "IMI",
            " I ");
            ironBreakerShieldRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            ironBreakerShieldRecipe.setIngredient('I', Material.IRON_INGOT);
        Bukkit.addRecipe(ironBreakerShieldRecipe);
        
        
        //
        //      BerserkerShield Recipe
        //
        ItemStack berserkerShield = CreateCustomItem.createBerserkerShield();
        key = new NamespacedKey(SzoPlugin.getInstance(), "BerserkerShieldRecipe");
        recipeKeys.add(key);

        ShapedRecipe berserkerShieldRecipe = new ShapedRecipe(key, berserkerShield);
        berserkerShieldRecipe.shape(
            "E E",
            "WSW",
            " W ");
            berserkerShieldRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            berserkerShieldRecipe.setIngredient('W', Material.OAK_PLANKS);
            berserkerShieldRecipe.setIngredient('E', Material.SPIDER_EYE);
        Bukkit.addRecipe(berserkerShieldRecipe);
        
        
        //
        //      stinkyStick Recipe
        //
        ItemStack stinkyStick = CreateCustomItem.createStinkyStick();
        key = new NamespacedKey(SzoPlugin.getInstance(), "stinkyStickRecipe");
        recipeKeys.add(key);

        ShapedRecipe stinkyStickRecipe = new ShapedRecipe(key, stinkyStick);
        stinkyStickRecipe.shape(
            " P ",
            " S ",
            "   ");
            stinkyStickRecipe.setIngredient('P', Material.PISTON);
            stinkyStickRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(stinkyStickRecipe);
        
        
        //
        //      gravityBow Recipe
        //
        ItemStack gravityBow = CreateCustomItem.createGravityBow();
        key = new NamespacedKey(SzoPlugin.getInstance(), "GravityBowRecipe");
        recipeKeys.add(key);

        ShapedRecipe gravityBowRecipe = new ShapedRecipe(key, gravityBow);
        gravityBowRecipe.shape(
            "SW ",
            "S M",
            "SW ");
            gravityBowRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            gravityBowRecipe.setIngredient('S', Material.STRING);
            gravityBowRecipe.setIngredient('W', Material.STICK);
        Bukkit.addRecipe(gravityBowRecipe);
        
        
        //
        //      gravityBow Recipe
        //
        ItemStack ratBow = CreateCustomItem.createRatBow();
        key = new NamespacedKey(SzoPlugin.getInstance(), "RatBowRecipe");
        recipeKeys.add(key);

        ShapedRecipe ratBowRecipe = new ShapedRecipe(key, ratBow);
        ratBowRecipe.shape(
            "SW ",
            "S F",
            "SW ");
            ratBowRecipe.setIngredient('F', new RecipeChoice.ExactChoice(soulFragment));
            ratBowRecipe.setIngredient('S', Material.STRING);
            ratBowRecipe.setIngredient('W', Material.STICK);
        Bukkit.addRecipe(ratBowRecipe);
        
        
        //
        //      repeaterCrossbow Recipe
        //
        ItemStack repeaterCrossbow = CreateCustomItem.createRepeaterCrossbow();
        key = new NamespacedKey(SzoPlugin.getInstance(), "RepeaterCrossbowRecipe");
        recipeKeys.add(key);

        ShapedRecipe repeaterCrossbowRecipe = new ShapedRecipe(key, repeaterCrossbow);
        repeaterCrossbowRecipe.shape(
            " M ",
            "RCR",
            "   ");
            repeaterCrossbowRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            repeaterCrossbowRecipe.setIngredient('C', Material.CROSSBOW);
            repeaterCrossbowRecipe.setIngredient('R', Material.REPEATER);
        Bukkit.addRecipe(repeaterCrossbowRecipe);


        //
        //      IronCladHelmet Recipe
        //
        ItemStack ironCladHelmet = CreateCustomItem.createIronCladHelmet();
        key = new NamespacedKey(SzoPlugin.getInstance(), "IronCladHelmetRecipe");
        recipeKeys.add(key);

        ShapedRecipe ironCladHelmetRecipe = new ShapedRecipe(key, ironCladHelmet);
        ironCladHelmetRecipe.shape(
            "IMI",
            "A A",
            "   ");
        ironCladHelmetRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
        ironCladHelmetRecipe.setIngredient('I', Material.IRON_INGOT);
        ironCladHelmetRecipe.setIngredient('A', Material.AMETHYST_SHARD);
        Bukkit.addRecipe(ironCladHelmetRecipe);
        
        //
        //      Hermes's boots Recipe
        //
        ItemStack hermesBoots = CreateCustomItem.createHermesBoots();
        key = new NamespacedKey(SzoPlugin.getInstance(), "HermesBootsRecipe");
        recipeKeys.add(key);

        ShapedRecipe hermesBootsRecipe = new ShapedRecipe(key, hermesBoots);
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
        key = new NamespacedKey(SzoPlugin.getInstance(), "JetBootsRecipe");
        recipeKeys.add(key);

        ShapedRecipe jetBootsRecipe = new ShapedRecipe(key, jetBoots);
        jetBootsRecipe.shape(
            "   ",
            "DMD",
            "P P");
            jetBootsRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            jetBootsRecipe.setIngredient('P', Material.PHANTOM_MEMBRANE);
            jetBootsRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(jetBootsRecipe);
                
        
        //
        //      IronCladBoots Recipe
        //
        ItemStack ironCladBoots = CreateCustomItem.createIronCladBoots();
        key = new NamespacedKey(SzoPlugin.getInstance(), "IronCladBootsRecipe");
        recipeKeys.add(key);

        ShapedRecipe ironCladBootsRecipe = new ShapedRecipe(key, ironCladBoots);
        ironCladBootsRecipe.shape(
            "M  ",
            "P P",
            "I I");
        ironCladBootsRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
        ironCladBootsRecipe.setIngredient('I', Material.IRON_INGOT);
        ironCladBootsRecipe.setIngredient('P', Material.PISTON);
        Bukkit.addRecipe(ironCladBootsRecipe);
                
        
        //
        //      IronCladChestplate Recipe
        //
        ItemStack ironCladChestplate = CreateCustomItem.createIronCladChestplate();
        key = new NamespacedKey(SzoPlugin.getInstance(), "IronCladChestplateRecipe");
        recipeKeys.add(key);

        ShapedRecipe ironCladChestplateRecipe = new ShapedRecipe(key, ironCladChestplate);
        ironCladChestplateRecipe.shape(
            "I I",
            "PMP",
            "IRI");
        ironCladChestplateRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
        ironCladChestplateRecipe.setIngredient('I', Material.IRON_INGOT);
        ironCladChestplateRecipe.setIngredient('R', Material.REDSTONE_BLOCK);
        ironCladChestplateRecipe.setIngredient('P', Material.PISTON);
        Bukkit.addRecipe(ironCladChestplateRecipe);
        
        
        //
        //      Golem Chest Recipe
        //
        ItemStack golemChest = CreateCustomItem.createGolemChest();
        key = new NamespacedKey(SzoPlugin.getInstance(), "GolemChestRecipe");
        recipeKeys.add(key);

        ShapedRecipe golemChestRecipe = new ShapedRecipe(key, golemChest);
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
        key = new NamespacedKey(SzoPlugin.getInstance(), "ExplosiveChestRecipe");
        recipeKeys.add(key);

        ShapedRecipe explosiveChestRecipe = new ShapedRecipe(key, explosiveChest);
        explosiveChestRecipe.shape(
            "D D",
            "TST",
            "DDD");
            explosiveChestRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            explosiveChestRecipe.setIngredient('T', Material.TNT);
            explosiveChestRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(explosiveChestRecipe);
        
        
        //
        //      MermaidTail Recipe
        //
        ItemStack mermaidTail = CreateCustomItem.createMermaidTail();
        key = new NamespacedKey(SzoPlugin.getInstance(), "MermaidTailRecipe");
        recipeKeys.add(key);

        ShapedRecipe mermaidTailRecipe = new ShapedRecipe(key, mermaidTail);
        mermaidTailRecipe.shape(
            "PSP",
            "D D",
            "D D");
            mermaidTailRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            mermaidTailRecipe.setIngredient('P', Material.PRISMARINE_CRYSTALS);
            mermaidTailRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(mermaidTailRecipe);
        
        
        //
        //      IronCladLegs Recipe
        //
        ItemStack ironCladLeggings = CreateCustomItem.createIronCladLeggings();
        key = new NamespacedKey(SzoPlugin.getInstance(), "IronCladLeggingsRecipe");
        recipeKeys.add(key);

        ShapedRecipe ironCladLeggingsRecipe = new ShapedRecipe(key, ironCladLeggings);
        ironCladLeggingsRecipe.shape(
            "IMI",
            "P P",
            "I I");
        ironCladLeggingsRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
        ironCladLeggingsRecipe.setIngredient('I', Material.IRON_INGOT);
        ironCladLeggingsRecipe.setIngredient('P', Material.PISTON);
        Bukkit.addRecipe(ironCladLeggingsRecipe);
        
        
        //
        //      GluttonyPants Recipe
        //
        ItemStack gluttonyPants = CreateCustomItem.createGluttonyPants();
        key = new NamespacedKey(SzoPlugin.getInstance(), "GluttonyPantsRecipe");
        recipeKeys.add(key);

        ShapedRecipe gluttonyPantsRecipe = new ShapedRecipe(key, gluttonyPants);
        gluttonyPantsRecipe.shape(
            "GSG",
            "D D",
            "D D");
            gluttonyPantsRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            gluttonyPantsRecipe.setIngredient('G', Material.GOLDEN_APPLE);
            gluttonyPantsRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(gluttonyPantsRecipe);
        
        
        //
        //      NightHelmet Recipe
        //
        ItemStack nightHelmet = CreateCustomItem.createNightHelmet();
        key = new NamespacedKey(SzoPlugin.getInstance(), "nightHelmetRecipe");
        recipeKeys.add(key);

        ShapedRecipe nightHelmetRecipe = new ShapedRecipe(key, nightHelmet);
        nightHelmetRecipe.shape(
            "GSG",
            "D D",
            "   ");
        nightHelmetRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
        nightHelmetRecipe.setIngredient('G', Material.GLOWSTONE_DUST);
        nightHelmetRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(nightHelmetRecipe);
        
        
        //
        //      StrigaVeil Recipe
        //
        ItemStack strigaVeil = CreateCustomItem.createStrigaVeil();
        key = new NamespacedKey(SzoPlugin.getInstance(), "strigaVeilRecipe");
        recipeKeys.add(key);

        ShapedRecipe strigaVeilRecipe = new ShapedRecipe(key, strigaVeil);
        strigaVeilRecipe.shape(
            "GSG",
            "D D",
            "   ");
        strigaVeilRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
        strigaVeilRecipe.setIngredient('G', Material.GHAST_TEAR);
        strigaVeilRecipe.setIngredient('D', Material.LIGHT_GRAY_DYE);
        Bukkit.addRecipe(strigaVeilRecipe);


        //
        //      Teleport Spell recipe
        //
        ItemStack teleportSpell = CreateCustomItem.createTeleportSpell();
        key = new NamespacedKey(SzoPlugin.getInstance(), "teleportSpellRecipe");
        recipeKeys.add(key);

        ShapedRecipe teleportSpellRecipe = new ShapedRecipe(key, teleportSpell);
        teleportSpellRecipe.shape(
            " P ",
            "PSP",
            " P ");
            teleportSpellRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            teleportSpellRecipe.setIngredient('P', Material.ENDER_PEARL);
        Bukkit.addRecipe(teleportSpellRecipe);


        //
        //      Spirit Leech Spell recipe
        //
        ItemStack leechSpell = CreateCustomItem.createSpiritLeech();
        key = new NamespacedKey(SzoPlugin.getInstance(), "leechSpellRecipe");
        recipeKeys.add(key);

        ShapedRecipe leechSpellRecipe = new ShapedRecipe(key, leechSpell);
        leechSpellRecipe.shape(
            " L ",
            "LSL",
            " L ");
            leechSpellRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            leechSpellRecipe.setIngredient('L', Material.SOUL_LANTERN);
        Bukkit.addRecipe(leechSpellRecipe);


        //
        //      OBLITERATE Spell recipe
        //
        ItemStack obliterateSpell = CreateCustomItem.createObliterateSpell();
        key = new NamespacedKey(SzoPlugin.getInstance(), "obliterateSpellRecipe");
        recipeKeys.add(key);

        ShapedRecipe obliterateSpellRecipe = new ShapedRecipe(key, obliterateSpell);
        obliterateSpellRecipe.shape(
            "TTT",
            "TST",
            "TTT");
        obliterateSpellRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
        obliterateSpellRecipe.setIngredient('T', Material.TNT_MINECART);
        Bukkit.addRecipe(obliterateSpellRecipe);


        //
        //      Spider Yeet Spell recipe
        //
        ItemStack spiderYeetSpell = CreateCustomItem.createSpiderYeetSpell();
        key = new NamespacedKey(SzoPlugin.getInstance(), "spiderYeetRecipe");
        recipeKeys.add(key);

        ShapedRecipe spiderYeetSpellRecipe = new ShapedRecipe(key, spiderYeetSpell);
        spiderYeetSpellRecipe.shape(
            "CCC",
            "CSC",
            "CCC");
        spiderYeetSpellRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
        spiderYeetSpellRecipe.setIngredient('C', Material.COBWEB);
        Bukkit.addRecipe(spiderYeetSpellRecipe);


        //
        //      Hasty Shovel recipe
        //
        ItemStack hastyShovel = CreateCustomItem.createHastyShovel();
        key = new NamespacedKey(SzoPlugin.getInstance(), "hastyShovelRecipe");
        recipeKeys.add(key);

        ShapedRecipe hastyShovelRecipe = new ShapedRecipe(key, hastyShovel);
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
        key = new NamespacedKey(SzoPlugin.getInstance(), "hastyAxeRecipe");
        recipeKeys.add(key);

        ShapedRecipe hastyAxeRecipe = new ShapedRecipe(key, hastyAxe);
        hastyAxeRecipe.shape(
            "DM ",
            "DS ",
            " S ");
            hastyAxeRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            hastyAxeRecipe.setIngredient('D', Material.DIAMOND);
            hastyAxeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(hastyAxeRecipe);


        //
        //      RichAxe recipe
        //
        ItemStack richAxe = CreateCustomItem.createRichAxe();
        key = new NamespacedKey(SzoPlugin.getInstance(), "richAxeRecipe");
        recipeKeys.add(key);

        ShapedRecipe richAxeRecipe = new ShapedRecipe(key, richAxe);
        richAxeRecipe.shape(
            "DS ",
            "DM ",
            " W ");
            richAxeRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            richAxeRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            richAxeRecipe.setIngredient('D', Material.DIAMOND);
            richAxeRecipe.setIngredient('W', Material.STICK);
        Bukkit.addRecipe(richAxeRecipe);


        //
        //      RichShovel recipe
        //
        ItemStack richShovel = CreateCustomItem.createRichShovel();
        key = new NamespacedKey(SzoPlugin.getInstance(), "richShovelRecipe");
        recipeKeys.add(key);

        ShapedRecipe richShovelRecipe = new ShapedRecipe(key, richShovel);
        richShovelRecipe.shape(
            " D ",
            " MS",
            " W ");
            richShovelRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            richShovelRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            richShovelRecipe.setIngredient('D', Material.DIAMOND);
            richShovelRecipe.setIngredient('W', Material.STICK);
        Bukkit.addRecipe(richShovelRecipe);


        //
        //      Hasty Pickaxe recipe
        //
        ItemStack hastyPickaxe = CreateCustomItem.createHastyPickaxe();
        key = new NamespacedKey(SzoPlugin.getInstance(), "hastyPickRecipe");
        recipeKeys.add(key);

        ShapedRecipe hastyPickaxeRecipe = new ShapedRecipe(key, hastyPickaxe);
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
        key = new NamespacedKey(SzoPlugin.getInstance(), "dwarfPickaxeRecipe");
        recipeKeys.add(key);
        
        ShapedRecipe dwarfPickaxeRecipe = new ShapedRecipe(key, dwarfPickaxe);
        dwarfPickaxeRecipe.shape(
            "DFD",
            " M ",
            " S ");
            dwarfPickaxeRecipe.setIngredient('F', new RecipeChoice.ExactChoice(soulFragment));
            dwarfPickaxeRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mechanicalParts));
            dwarfPickaxeRecipe.setIngredient('D', Material.DIAMOND);
            dwarfPickaxeRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(dwarfPickaxeRecipe);


        //
        //      Treasure Fishing Rod recipe
        //
        ItemStack treasureFish = CreateCustomItem.createTreasureFishingRod();
        key = new NamespacedKey(SzoPlugin.getInstance(), "treasureFishRecipe");
        recipeKeys.add(key);

        ShapedRecipe treasureFishRecipe = new ShapedRecipe(key, treasureFish);
        treasureFishRecipe.shape(
            "F  ",
            "TS ",
            "T S");
            treasureFishRecipe.setIngredient('F', new RecipeChoice.ExactChoice(soulFragment));
            treasureFishRecipe.setIngredient('T', Material.STRING);
            treasureFishRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(treasureFishRecipe);


        //
        //      Super Shears recipe
        //
        ItemStack superShears = CreateCustomItem.createSuperShears();
        key = new NamespacedKey(SzoPlugin.getInstance(), "superShearsRecipe");
        recipeKeys.add(key);

        ShapedRecipe superShearsRecipe = new ShapedRecipe(key, superShears);
        superShearsRecipe.shape(
            " N ",
            "NSN",
            " N ");
            superShearsRecipe.setIngredient('N', Material.QUARTZ);
            superShearsRecipe.setIngredient('S', Material.SHEARS);
        Bukkit.addRecipe(superShearsRecipe);


        //
        //      Homecoming Compass  recipe
        //
        ItemStack homecomingCompass = CreateCustomItem.createHomecomingCompass();
        key = new NamespacedKey(SzoPlugin.getInstance(), "homecomingCompassRecipe");
        recipeKeys.add(key);

        ShapedRecipe homecomingCompassRecipe = new ShapedRecipe(key, homecomingCompass);
        homecomingCompassRecipe.shape(
            " E ",
            "ECE",
            " E ");
            homecomingCompassRecipe.setIngredient('C', Material.COMPASS);
            homecomingCompassRecipe.setIngredient('E', Material.ENDER_PEARL);
        Bukkit.addRecipe(homecomingCompassRecipe);


        //
        //      Race book recipe
        //
        ItemStack raceBook = CreateCustomItem.createRaceBook();
        key = new NamespacedKey(SzoPlugin.getInstance(), "raceBookRecipe");
        recipeKeys.add(key);

        ShapelessRecipe raceBookRecipe = new ShapelessRecipe(key, raceBook);
        raceBookRecipe.addIngredient(3, Material.PAPER);
        raceBookRecipe.addIngredient(1, Material.AMETHYST_SHARD);
        Bukkit.addRecipe(raceBookRecipe);
    }
}
