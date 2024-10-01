package be.goldocelot.wildlife.registeries;

import be.goldocelot.wildlife.WildLife;
import be.goldocelot.wildlife.utils.CreativeTabFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WildLife.MOD_ID);

    public static <T extends Mob> DeferredHolder<Item, Item> registerEntityEgg(String entityId, DeferredHolder<EntityType<?>, EntityType<T>> entityTypeRegistryObject, int primaryEggColour, int secondaryEggColour){
        DeferredHolder<Item, Item> egg = ITEMS.register(entityId+"_spawn_egg", () -> new DeferredSpawnEggItem(entityTypeRegistryObject, primaryEggColour, secondaryEggColour, new Item.Properties()));
        CreativeTabFiller.setItemCreativeTabs(egg, CreativeModeTabs.SPAWN_EGGS);
        return egg;
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
