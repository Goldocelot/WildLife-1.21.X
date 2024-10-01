package be.goldocelot.wildlife.registeries;

import be.goldocelot.wildlife.WildLife;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, WildLife.MOD_ID);

    private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> registerEntity(String id, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height, int eggPrimary, int eggSecondary){
        DeferredHolder<EntityType<?>, EntityType<T>> entity = ENTITY_TYPES.register(id, () -> EntityType.Builder.of(factory, category)
                .sized(width, height)
                .build(ResourceLocation.fromNamespaceAndPath(WildLife.MOD_ID, id).toString()));

        if(eggPrimary != -1 && eggSecondary != -1) ModItems.registerEntityEgg(id, entity, eggPrimary, eggSecondary);

        return entity;
    }

    private static <T extends  Mob> DeferredHolder<EntityType<?>, EntityType<T>> registerEntity(String id, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height) {
        return registerEntity(id, factory, category, width, height, -1, -1);
    }

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
