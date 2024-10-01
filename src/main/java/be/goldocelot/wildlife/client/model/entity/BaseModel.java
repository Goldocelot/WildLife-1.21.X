package be.goldocelot.wildlife.client.model.entity;

import be.goldocelot.wildlife.WildLife;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BaseModel<T extends LivingEntity & GeoAnimatable> extends GeoModel<T> {

    private String id;

    public BaseModel(String id) {
        this.id = id;
    }

    @Override
    public ResourceLocation getModelResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(WildLife.MOD_ID, "geo/entity/"+this.id+".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(WildLife.MOD_ID, "textures/entity/"+this.id+".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return ResourceLocation.fromNamespaceAndPath(WildLife.MOD_ID,  "animations/entity/"+id+".animation.json");
    }

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        GeoBone head = getAnimationProcessor().getBone("Head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotZ(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

    public String getId() {
        return id;
    }
}
