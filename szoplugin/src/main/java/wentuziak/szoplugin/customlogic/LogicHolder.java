package wentuziak.szoplugin.customlogic;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
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
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;

import wentuziak.szoplugin.Keys;
import java.util.List;



public class LogicHolder {
    
    public static void removeItem(Player player, ItemStack itemUsed){
        if (itemUsed.getAmount() > 1) {
            itemUsed.setAmount(itemUsed.getAmount() - 1);
        } else{
            itemUsed.setAmount(0);
        }
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

    public static void throwSnowball(Player player, PersistentDataContainer playerContainer){
        Snowball snowball = player.launchProjectile(Snowball.class);
        if (playerContainer.has(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.STRING, "spiritLeech");
        }
        else if (playerContainer.has(Keys.CUSTOM_SPIDER_YEET, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_SPIDER_YEET, PersistentDataType.STRING, "spiderYeet");
        }
        else if (playerContainer.has(Keys.CUSTOM_GRENADE, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_GRENADE, PersistentDataType.STRING, "grenade");
        }
        else if (playerContainer.has(Keys.CUSTOM_SMOKE_BOMB, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_SMOKE_BOMB, PersistentDataType.STRING, "smokeBomb");
        }
        else if (playerContainer.has(Keys.CUSTOM_THROWING_FIREWORK, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_THROWING_FIREWORK, PersistentDataType.STRING, "throwingFirework");
        }
        snowball.setVelocity(player.getLocation().getDirection().multiply(2)); // Adjust velocity as needed
        snowball.setShooter(player);
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
}
