package com.mayankpatibandla.removeguns.mixins;

import com.mayankpatibandla.removeguns.RemoveGuns;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class CreativeTabMixin {
    private static final String[] ITEM_CLASSES =
        new String[]{"ItemGun", "ItemMachineGun", "ItemBullet", "ItemKunai", "ItemKunaiReversed",
                     "ItemThrowingShuriken", "ItemThrowingWeapon", "ItemMusket", "ItemGunChainsaw"};
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

        RemoveGuns.LOG.info("self: " + self);


        //        String displayName =
        //            StatCollector.translateToLocal(StatCollector.translateToLocal(self.getUnlocalizedName()) + "
        //            .name").trim();

        //        ItemStack stack = new ItemStack(self);
        //        String displayName = stack.getDisplayName();
        //        RemoveGuns.LOG.info("stack: " + stack);
        //        RemoveGuns.LOG.info("Display name: " + displayName);
        //        RemoveGuns.LOG.info("Unlocalized name: " + self.getUnlocalizedName());
        //        String displayName = "";
        //        RemoveGuns.LOG.info(Item.itemRegistry.getIDForObject(self));
        //        RemoveGuns.LOG.info(Item.itemRegistry.getObjectById(Item.itemRegistry.getIDForObject(self)));
        //        RemoveGuns.LOG.info(Item.itemRegistry.getNameForObject(Item.itemRegistry.getObjectById(Item
        //        .itemRegistry.getIDForObject(self))));

        if (tab == null) {
            RemoveGuns.LOG.info("Tab is null");
            return self;
        }

        this.tabToDisplayOn = tab;

        RemoveGuns.LOG.info("Tab: " + tab.getTabLabel());

        for (String item_name : ITEM_CLASSES) {
            if (self.getClass().getSimpleName().contains(item_name)) {
                this.tabToDisplayOn = null;
                RemoveGuns.LOG.info("Removed " + item_name + " from the creative tab");
            }
        }

        return self;
    }
}
