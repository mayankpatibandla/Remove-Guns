package com.mayankpatibandla.removeguns;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Config {

    private static final String[] DEFAULT_ITEMS_ARRAY =
        new String[]{"Wooden Gun", "Wooden Bullet", "Stone Gun", "Stone Bullet", "Iron Gun", "Iron Bullet",
                     "Golden Gun", "Golden Bullet", "Diamond Gun", "Diamond Bullet", "Bronze Gun", "Bronze Bullet",
                     "Emerald Gun", "Emerald Bullet", "Machine Gun", "Black Bullet", "Kunai", "Reverse Kunai",
                     "Shuriken", "Giant Shuriken", "Musket", "GunChainsaw"};
    private static final String[] DEFAULT_ITEM_CLASSES_ARRAY =
        new String[]{"ItemGun", "ItemMachineGun", "ItemBullet", "ItemKunai", "ItemKunaiReversed",
                     "ItemThrowingShuriken", "ItemThrowingWeapon", "ItemMusket", "ItemGunChainsaw"};
    public static String greeting = "Remove Guns is Enabled!";
    public static Set<String> items;
    public static Set<String> itemClasses;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "Startup message");
        items = new HashSet<>(Arrays.asList(configuration.getStringList("item_names", "items", DEFAULT_ITEMS_ARRAY,
                                                                        "Add the display names of the items you want " +
                                                                        "to remove from the " +
                                                                        "game, one per line (case sensitive)")));
        itemClasses = new HashSet<>(Arrays.asList(configuration.getStringList("item_classes", "items",
                                                                            DEFAULT_ITEM_CLASSES_ARRAY,
                                                                            "Add the class names of the items you want " +
                                                                            "to remove from the " +
                                                                            "game, one per line (case sensitive)")));

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
