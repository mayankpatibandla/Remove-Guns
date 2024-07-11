package com.mayankpatibandla.removeguns.mixins;

import static com.mayankpatibandla.removeguns.Config.synchronizeConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.launchwrapper.Launch;

import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import com.mayankpatibandla.removeguns.RemoveGuns;

public class RemoveGunsMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        File configFile = new File(Launch.minecraftHome, "config" + File.separator + RemoveGuns.MODID + ".cfg");
        RemoveGuns.LOG.info("Loading configuration from " + configFile.getAbsolutePath());
        synchronizeConfiguration(configFile);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        List<String> mixins = new ArrayList<>();

        mixins.add("CreativeTabMixin");

        return mixins;
    }

    @Override
    public void preApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }

    @Override
    public void postApply(String s, ClassNode classNode, String s1, IMixinInfo iMixinInfo) {

    }
}
