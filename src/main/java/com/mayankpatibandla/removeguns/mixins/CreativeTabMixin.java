package com.mayankpatibandla.removeguns.mixins;

import com.mayankpatibandla.removeguns.Config;
import com.mayankpatibandla.removeguns.RemoveGuns;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = CreativeTabs.class)
public abstract class CreativeTabMixin {
    @Inject(at = @At("HEAD"), method = "displayAllReleventItems(Ljava/util/List;)V", cancellable = true)
    private void displayAllReleventItems(List<ItemStack> stacks, CallbackInfo info) {
        // Remove guns from the creative tab
        RemoveGuns.LOG.info("Removing guns from the creative tab");
        RemoveGuns.LOG.info("Stacks size: " + stacks.size());
        for (String item_name : Config.items) {
//            RemoveGuns.LOG.info("Item name: " + item_name);
            for (ItemStack stack : stacks) {
                RemoveGuns.LOG.info("Item name: " + stack.getDisplayName() + " | Config item name: " + item_name);
                if (stack.getDisplayName().equals(item_name)) {
                    stacks.remove(stack);
                    RemoveGuns.LOG.info("Removed " + stack.getDisplayName() + " from the creative tab");
                } else {
                    RemoveGuns.LOG.info("Did not remove " + stack.getDisplayName() + " from the creative tab");
                }
            }
        }

        //        Iterator iterator = Item.itemRegistry.iterator();
        //        CreativeTabs self = null;
        //
        //        RemoveGuns.LOG.info("Iterating through items1");
        //        while(iterator.hasNext()) {
        //            RemoveGuns.LOG.info("Iterating through items2");
        //            Item item = (Item) iterator.next();
        //
        //            if (item == null) {
        //                continue;
        //            }
        //
        //            RemoveGuns.LOG.info("Item: " + item.getUnlocalizedName());
        //
        //            for (String item_name : Config.items) {
        //                String name =
        //                    StatCollector.translateToLocal(StatCollector.translateToLocal(item.getUnlocalizedName()
        //                    ) + ".name").trim();
        //                RemoveGuns.LOG.info("Item name: " + name + " | Config item name: " + item_name);
        //                if (name.equals(item_name)) {
        //                    iterator.remove();
        //                    RemoveGuns.LOG.info("Removed " + item.getUnlocalizedName() + " from the creative tab");
        //                }
        //            }
        //
        //            for (CreativeTabs tab : item.getCreativeTabs()) {
        //                if (tab == self) {
        //                    item.getSubItems(item, tab, stacks);
        //                }
        //            }
        //
        //            if(self.func_111225_m() != null) {
        //                self.addEnchantmentBooksToList(stacks, self.func_111225_m());
        //            }
        //        }
        //
//        info.cancel();
        RemoveGuns.LOG.info("Finished removing guns from the creative tab");
    }
}
