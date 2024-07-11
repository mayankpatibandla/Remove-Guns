package com.mayankpatibandla.removeguns.mixins;

import com.mayankpatibandla.removeguns.Config;
import com.mayankpatibandla.removeguns.RemoveGuns;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class CreativeTabMixin {
    @Shadow
    private CreativeTabs tabToDisplayOn;

    Item self = (Item) (Object) this;

    /**
     * @author Mayank Patibandla
     * @reason Remove guns from the creative tab
     */
    @Overwrite
    public Item setCreativeTab(CreativeTabs tab) {
        // Remove guns from the creative tab
        RemoveGuns.LOG.info("Removing guns from the creative tab");

        RemoveGuns.LOG.info("self: " + self);
        RemoveGuns.LOG.info("Self is null? " + (self == null));


        String displayName =
            StatCollector.translateToLocal(StatCollector.translateToLocal(self.getUnlocalizedName()) + ".name").trim();

        if (tab == null) {
            RemoveGuns.LOG.info("Tab is null");
            return self;
        }

        this.tabToDisplayOn = tab;

        RemoveGuns.LOG.info("Tab: " + tab.getTabLabel());
        RemoveGuns.LOG.info("Config.items: " + Config.items);
        RemoveGuns.LOG.info("Config.items is null? " + (Config.items == null));
//        RemoveGuns.LOG.info("Config.items size: " + Config.items.length);

        if (Config.items == null) {
            RemoveGuns.LOG.info("Config.items is null");
            return self;
        }

        for (String item_name : Config.items) {
            if (displayName.equals(item_name)) {
                RemoveGuns.LOG.info("Item name: " + item_name);
                RemoveGuns.LOG.info("tabRedstone is null? " + (CreativeTabs.tabRedstone == null));
                this.tabToDisplayOn = CreativeTabs.tabRedstone;
                RemoveGuns.LOG.info("Removed " + item_name + " from the creative tab");
            }
        }

        RemoveGuns.LOG.info("Finished removing guns from the creative tab");
        return self;
    }
}
