package com.mayankpatibandla.removeguns.mixins;

import com.mayankpatibandla.removeguns.Config;
import com.mayankpatibandla.removeguns.RemoveGuns;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

        RemoveGuns.LOG.info("self: " + self);


//        String displayName =
//            StatCollector.translateToLocal(StatCollector.translateToLocal(self.getUnlocalizedName()) + ".name").trim();

//        ItemStack stack = new ItemStack(self);
//        String displayName = stack.getDisplayName();
//        RemoveGuns.LOG.info("stack: " + stack);
//        RemoveGuns.LOG.info("Display name: " + displayName);
        RemoveGuns.LOG.info("Unlocalized name: " + self.getUnlocalizedName());
        String displayName = "";

        if (tab == null) {
            RemoveGuns.LOG.info("Tab is null");
            return self;
        }

        this.tabToDisplayOn = tab;

        RemoveGuns.LOG.info("Tab: " + tab.getTabLabel());

        for (String item_name : Config.DEFAULT_ITEMS) {
            if (displayName.equals(item_name)) {
                RemoveGuns.LOG.info("Item name: " + item_name);
                RemoveGuns.LOG.info("tabRedstone is null? " + (CreativeTabs.tabRedstone == null));
                this.tabToDisplayOn = CreativeTabs.tabRedstone;
                RemoveGuns.LOG.info("Removed " + item_name + " from the creative tab");
            }
        }

//        RemoveGuns.LOG.info("Finished removing "+ displayName + " from the creative tab");
        return self;
    }
}
