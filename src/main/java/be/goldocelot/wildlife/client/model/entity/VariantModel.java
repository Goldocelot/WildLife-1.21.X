package be.goldocelot.wildlife.client.model.entity;

import be.goldocelot.wildlife.WildLife;
import be.goldocelot.wildlife.world.entity.VariantEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;

public class VariantModel<T extends LivingEntity & GeoAnimatable & VariantEntity> extends BaseModel<T> {
    public VariantModel(String id) {
        super(id);
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(WildLife.MOD_ID, "textures/entity/"+this.getId()+"/"+animatable.getVariantId()+".png");
    }

}
