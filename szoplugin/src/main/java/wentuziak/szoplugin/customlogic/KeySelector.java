package wentuziak.szoplugin.customlogic;

import java.util.Set;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;


public class KeySelector {

    private static String[] meleeKeys = new String[]{
        "ExplosiveSword", "Weapons.explosiveSwordEffect(33, hitEntity);",
        "AngelSword", "Weapons.angelSwordEffect(44, player);",
        "DaemonSword", "Weapons.daemonSwordEffect(40, hitEntity)",
        "ThunderHammer", "Weapons.thunderHammerEffect(40, hitEntity);",
    };

    private static String[] rangedKeys = new String[] {
        "GravityBow",
        "RatBow",
        "Grenade",
        "SmokeBomb",
        "MagneticTrident",
        "BouncyCrossbow",
        "ThrowingFirework",
    };

    // "GravityBow"
    // "RatBow"
    // "Grenade"
    // "SmokeBomb"
    // "MagneticTrident"
    // "BouncyCrossbow"
    // "ThrowingFirework"

    public static String[] entityKeyList(Entity entity, PersistentDataContainer dataContainer){
        
        Set<NamespacedKey> keySet = dataContainer.getKeys();
        String[] keysAsStringArray = new String[keySet.size()];

        // Loop through and add each key to the array as a string
        int i = 0;
        for (NamespacedKey key : keySet) {
            keysAsStringArray[i] = key.toString();
            i++;
        }

        return keysAsStringArray;
    }

    public static String getMatchingKey(String[] keyArray, int whichArray){
        
        String matchingKey = null;
        String[] selectedArray = arraySelector(whichArray);
        // Loop through each key in the selectedArray
        for (String key : selectedArray) {
            for (String inputKey : keyArray) {
                if (key.toLowerCase().equals(inputKey.replace("szoplugin:", ""))) {
                    matchingKey = key;
                    break;
                }
                System.out.println(key + " :: " + inputKey);
            }
            if (matchingKey != null) {
                break;
            }
        }

        return matchingKey;
    }

    // public static void functionInvoker(String key, int whichArray){
    //     System.out.println(getFunction(key, whichArray));
    //     // TODO : implement
    // }

    // public static String getFunction(String key, int whichArray){
    //     String[] selectedArray = arraySelector(whichArray);
    //     for (int i = 0; i < selectedArray.length; i += 2) { // Iterate by step of 2
    //         if (selectedArray[i].equals(key)) {
    //             return selectedArray[i + 1]; // Return the corresponding function
    //         }
    //     }
    //     return null; // Return null if the key is not found
    // }

    public static String[] arraySelector(int whichArray){
        String[] selectedArray = null;
        switch (whichArray) {
            case 1:
                selectedArray = meleeKeys;
                break;
        
            case 2:
                selectedArray = rangedKeys;
                break;
        
            default:
                break;
        }

        return selectedArray;
    }
}
