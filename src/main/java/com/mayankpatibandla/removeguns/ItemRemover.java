package com.mayankpatibandla.removeguns;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ItemRemover {

    @SubscribeEvent
    public void removeFromInventory(TickEvent.PlayerTickEvent event) {
        // Remove guns from the player's inventory
        EntityPlayer player = event.player;

        for (Object x : player.inventoryContainer.getInventory()) {
            if (x instanceof ItemStack) {
                ItemStack item = (ItemStack) x;

                if (Config.items.contains(item.getDisplayName())) {
                    player.inventoryContainer.putStackInSlot(
                        player.inventoryContainer.getInventory()
                            .indexOf(item),
                        null);

                    RemoveGuns.LOG
                        .info("Removed " + item.getDisplayName() + " from " + player.getDisplayName() + "'s inventory");
                    player.addChatMessage(
                        (IChatComponent) new ChatComponentText(
                            "§cRemoved " + item
                                .getDisplayName() + " from " + player.getDisplayName() + "'s " + "inventory"));
                }
            }
        }
    }

    @SubscribeEvent
    public void removeFromWorld(TickEvent.WorldTickEvent event) {
        // Remove guns from the world
        for (Object x : event.world.loadedEntityList) {
            if (x instanceof EntityItem) {
                EntityItem item = (EntityItem) x;
                if (Config.items.contains(
                    item.getEntityItem()
                        .getDisplayName())) {
                    event.world.removeEntity(item);
                    RemoveGuns.LOG.info(
                        "Removed " + item.getEntityItem()
                            .getDisplayName() + " from the world");
                }
            }
        }
    }
}
