package com.mayankpatibandla.removeguns.mixins;

import com.mayankpatibandla.removeguns.Config;
import com.mayankpatibandla.removeguns.RemoveGuns;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class CreativeTabMixin {


    Item self = (Item) (Object) this;

    @Shadow
    private CreativeTabs tabToDisplayOn;

    /**
     * @author Mayank Patibandla
     * @reason Remove guns from the creative tab
     */
    @Overwrite
    public Item setCreativeTab(CreativeTabs tab) {
        // Remove guns from the creative tab
        if (tab == null) {
            RemoveGuns.LOG.debug("Tab is null");
            return self;
        }

        this.tabToDisplayOn = tab;

        for (String item_name : Config.itemClasses) {
            if (self.getClass().getSimpleName().contains(item_name)) {
                this.tabToDisplayOn = null;
                RemoveGuns.LOG.info("Removed " + item_name + " from the creative tab");
                return self;
            }
        }

        return self;
    }
}
