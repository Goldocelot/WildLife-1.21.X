package be.goldocelot.wildlife.utils;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreativeTabFiller  {
    private static Map<DeferredHolder<? extends Item, ? extends Item>, ResourceKey<CreativeModeTab>> toRegister = new HashMap<>();

    public static void setItemCreativeTabs(DeferredHolder<? extends Item, ? extends Item> registryObject, ResourceKey<CreativeModeTab> tab) {
        toRegister.put(registryObject, tab);
    }

    public static void addItemCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        List<DeferredHolder<? extends Item, ? extends Item>> populatedItems = new ObjectArrayList<>();
        for(DeferredHolder<? extends Item, ? extends Item> item : toRegister.keySet()) {
            if(event.getTabKey() != toRegister.get(item)) continue;
            event.accept(item.get());
            populatedItems.add(item);
        }

        for(DeferredHolder<? extends Item, ? extends Item> item : populatedItems) {
            toRegister.remove(item);
        }
    }
}