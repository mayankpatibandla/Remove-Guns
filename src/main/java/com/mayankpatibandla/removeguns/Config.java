package com.mayankpatibandla.removeguns;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String greeting = "Remove Guns is Enabled!";
    public static String[] items;

    private static final String[] DEFAULT_ITEMS = new String[] { "Wooden Gun", "Wooden Bullet", "Stone Gun",
        "Stone Bullet", "Iron Gun", "Iron Bullet", "Golden Gun", "Golden Bullet", "Diamond Gun", "Diamond Bullet",
        "Bronze Gun", "Bronze Bullet", "Emerald Gun", "Emerald Bullet", "Machine Gun", "Black Bullet", "Kunai",
        "Reverse Kunai", "Shuriken", "Giant Shuriken", "Musket", "GunChainsaw" };

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "Startup message");
        items = configuration.getStringList(
            "item_names",
            "items",
            DEFAULT_ITEMS,
            "Add the display names of the items you want to remove from the " + "game, one per line (case sensitive)");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
