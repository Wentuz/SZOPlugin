package wentuziak.szoplugin.customlogic;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Parrot.Variant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.bukkit.Particle;

import wentuziak.szoplugin.Keys;
import java.util.List;
import java.util.Map;




public class LogicHolder {
    
    public static void removeItem(Player player, ItemStack itemUsed){
        int amount = itemUsed.getAmount() > 1 ? itemUsed.getAmount() - 1 : 0;
        itemUsed.setAmount(amount);
    }

    public static void givePotionEffect(LivingEntity player,String effect,int duration,int amplifier){
        PotionEffectType typeOfEffect = PotionEffectType.getByName(effect);
        player.addPotionEffect(new PotionEffect(typeOfEffect , duration, amplifier));
    }

    public static boolean critRoll(int critChance){   
        return (Math.random() * 100 + 1) <= critChance;
    }

    public static boolean isPlayerInWater(Player player){
        return player.isInWater();
    }

    public static void entityPotionEffectTimer(LivingEntity entity, Float duration, Integer strength){
        for (PotionEffect effect : entity.getActivePotionEffects()) {
            PotionEffectType effectType = effect.getType(); // Get the potion effect type
            int newAmplifier = effect.getAmplifier() + strength;
            int newDuration = (int) (effect.getDuration() * duration);

            // Remove the old effect
            entity.removePotionEffect(effectType);

            // Apply the new effect with modified strength and duration
            PotionEffect newEffect = new PotionEffect(effectType, newDuration, newAmplifier);
            entity.addPotionEffect(newEffect);
        }
    }

    public static void throwSnowball(LivingEntity livingEntity, NamespacedKey key, int velocity){
        Snowball snowball = livingEntity.launchProjectile(Snowball.class);

        Map<NamespacedKey, String> customTags = Map.of(
            Keys.CUSTOM_SPIRIT_LEECH, "spiritLeech",
            Keys.CUSTOM_SPIDER_YEET, "spiderYeet",
            Keys.CUSTOM_GRENADE, "grenade",
            Keys.CUSTOM_SMOKE_BOMB, "smokeBomb",
            Keys.CUSTOM_THROWING_FIREWORK, "throwingFirework",
            Keys.CUSTOM_WEB_TRAP, "webTrap"
        );
        
        if (customTags.containsKey(key)) {
            snowball.getPersistentDataContainer().set(key, PersistentDataType.STRING, customTags.get(key));
        }

        snowball.setVelocity(livingEntity.getLocation().getDirection().multiply(velocity));
        snowball.setShooter(livingEntity);
        }

    public static boolean isPlayerAboveGround(LivingEntity player, double minDistance) {
        Block blockBelow = player.getLocation().subtract(0, minDistance, 0).getBlock();
        return !blockBelow.getType().isSolid();
    }

    public static RayTraceResult rayTrace(int maxDistance, float radius, Player player){
        RayTraceResult result = player.getWorld().rayTraceEntities(
            player.getEyeLocation(), 
            player.getEyeLocation().getDirection(), 
            maxDistance,
            radius,
            entity -> entity != player
            );
        return result;
    }
    
    public static boolean isDaytime(World world) {
        long time = world.getTime();
        return time >= 0 && time < 12000;
    }
    public static boolean isRaining(World world) {
        return world.isClearWeather();
    }

    public static void modifyCurrentHeatlhPoints(LivingEntity targetEntity, Double amount){
        double currentHealth = targetEntity.getHealth();
        double maxHealth = targetEntity.getAttribute(Attribute.MAX_HEALTH).getValue();

        double newHealth = currentHealth + amount;
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }else if(newHealth < 0){
            newHealth = 0;
        }

        targetEntity.setHealth(newHealth);
    }

    public static Firework randomFirework(int power, Location location){
        Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();

        meta.setPower(power);

        FireworkEffect effect = FireworkEffect.builder()
            .withColor(getRandomColor())
            .withFade(getRandomColor())
            .with(FireworkEffect.Type.BALL_LARGE)
            .build();
        meta.addEffect(effect);
        firework.setFireworkMeta(meta);

        return firework;
    }
    private static Color getRandomColor() {
        int red = (int)(Math.random() * 255 + 1);
        int green = (int)(Math.random() * 255 + 1);
        int blue = (int)(Math.random() * 255 + 1);

        return Color.fromRGB(red, green, blue);
    }

    public static Player findNearestPlayer(Location location) {
        Player nearestPlayer = null;
        double nearestDistanceSquared = Double.MAX_VALUE;

        for (Entity entity : location.getWorld().getNearbyEntities(location, 50, 50, 50)) {
            if (entity instanceof Player) {
                double distanceSquared = entity.getLocation().distanceSquared(location);
                if (distanceSquared < nearestDistanceSquared) {
                    nearestDistanceSquared = distanceSquared;
                    nearestPlayer = (Player) entity;
                }
            }
        }
        return nearestPlayer;
    }

    public static Item rollTreasure(int playerLuck, Location location, String typeOfLoot) {
        int whatLoot = 0;
        while (playerLuck > 0) {
            whatLoot += (int)(Math.random() * 35 + 1);
            playerLuck--;
        }
    
        int numberOfItems = (int)(Math.random() * 6 + 1);
        ItemStack item = RandomLoot.getLoot(whatLoot, numberOfItems, typeOfLoot);
        
        return location.getWorld().dropItemNaturally(location, item);
    }

    public static Parrot summonRandomParrot(Location location) {
        Parrot parrot = (Parrot) location.getWorld().spawnEntity(location, EntityType.PARROT);
        List<Parrot.Variant> variants = List.of(
            Parrot.Variant.BLUE,
            Parrot.Variant.CYAN,
            Parrot.Variant.GRAY,
            Parrot.Variant.GREEN,
            Parrot.Variant.RED
        );
        int random = (int)(Math.random() * 5);

        Variant randomVariant = variants.get(random);

        parrot.setVariant(randomVariant);
        return parrot;
    }

    public static Wolf summonRandomWolf(Location location) {
        Wolf wolf = (Wolf) location.getWorld().spawnEntity(location, EntityType.WOLF);
        List<Wolf.Variant> variants = List.of(
            Wolf.Variant.ASHEN,
            Wolf.Variant.BLACK,
            Wolf.Variant.CHESTNUT,
            Wolf.Variant.PALE,
            Wolf.Variant.RUSTY,
            Wolf.Variant.SNOWY,
            Wolf.Variant.SPOTTED,
            Wolf.Variant.STRIPED,
            Wolf.Variant.WOODS
        );
        int random = (int)(Math.random() * 9);

        org.bukkit.entity.Wolf.Variant randomVariant = variants.get(random);

        wolf.setVariant(randomVariant);
        return wolf;
    }

    public static Cat summonRandomCat(Location location) {
        Cat cat = (Cat) location.getWorld().spawnEntity(location, EntityType.CAT);
        List<Cat.Type> variants = List.of(
            Cat.Type.ALL_BLACK,
            Cat.Type.BLACK,
            Cat.Type.BRITISH_SHORTHAIR,
            Cat.Type.CALICO,
            Cat.Type.PERSIAN,
            Cat.Type.RAGDOLL,
            Cat.Type.RED,
            Cat.Type.SIAMESE,
            Cat.Type.TABBY,
            Cat.Type.WHITE
        );
        int random = (int)(Math.random() * 10);

        Cat.Type randomVariant = variants.get(random);

        cat.setCatType(randomVariant);
        return cat;
    }

    private static final Material[] helmets = new Material[] {
        Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, 
        Material.GOLDEN_HELMET, Material.DIAMOND_HELMET, Material.NETHERITE_HELMET
    };

    private static final Material[] chestplates = new Material[] {
        Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE,
        Material.GOLDEN_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE
    };

    private static final Material[] leggings = new Material[] {
        Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS,
        Material.GOLDEN_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS
    };

    private static final Material[] boots = new Material[] {
        Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS,
        Material.GOLDEN_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS
    };

    public static final String VerticalParticleLine = null;

    public static void equipRandomArmor(boolean isTiered, LivingEntity entity){
        int random = (int)(Math.random() * 5);

        Material generatedHelmet = isTiered ? helmets[random] : helmets[random];
        Material generatedChestplate = isTiered ? chestplates[random] : chestplates[(int)(Math.random() * 5)];
        Material generatedLeggings = isTiered ? leggings[random] : leggings[(int)(Math.random() * 5)];
        Material generatedBoots = isTiered ? boots[random] : boots[(int)(Math.random() * 5)];

         entity.getEquipment().setHelmet(new ItemStack(generatedHelmet));
         entity.getEquipment().setChestplate(new ItemStack(generatedChestplate));
         entity.getEquipment().setLeggings(new ItemStack(generatedLeggings));
         entity.getEquipment().setBoots(new ItemStack(generatedBoots));

         entity.getEquipment().setHelmetDropChance(0.05F);  // 5% chance
         entity.getEquipment().setChestplateDropChance(0.05F);
         entity.getEquipment().setLeggingsDropChance(0.05F);
         entity.getEquipment().setBootsDropChance(0.05F);
    }

    public static void lingeringPotionDrop(PotionType baseType ,PotionEffectType effectType, Entity entity){
                // Create a lingering healing potion
        ItemStack potion = new ItemStack(Material.LINGERING_POTION);
        PotionMeta meta = (PotionMeta) potion.getItemMeta();
        meta.setBasePotionData(new org.bukkit.potion.PotionData(baseType));
        meta.addCustomEffect(new PotionEffect(effectType, 1, 1), true);
        potion.setItemMeta(meta);

        // Throw the potion directly below the player
        ThrownPotion thrownPotion = entity.getWorld().spawn(entity.getLocation().subtract(0, 0, 0), ThrownPotion.class);
        thrownPotion.setItem(potion);
        thrownPotion.setVelocity(entity.getLocation().getDirection().multiply(0)); // Drop straight down
    }


    
}
