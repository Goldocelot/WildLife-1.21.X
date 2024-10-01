package be.goldocelot.wildlife.client.renderer;

import be.goldocelot.wildlife.client.renderer.layer.HoldingItemLayer;
import be.goldocelot.wildlife.world.entity.HoldingEntity;
import be.goldocelot.wildlife.world.entity.LayeredEntity;
import be.goldocelot.wildlife.world.entity.ParticleEmittingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class BaseEntityRenderer<T extends LivingEntity & GeoAnimatable> extends GeoEntityRenderer<T> {

    private int currentTick = -1;
    private float babyScaling;

    public BaseEntityRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model, float babyScaling) {
        super(renderManager, model);

        this.babyScaling = babyScaling;

        if(animatable instanceof LayeredEntity) {
            LayeredEntity<T> layeredAnimatable = (LayeredEntity<T>) animatable;

            for(GeoRenderLayer<T> layer : layeredAnimatable.getRenderLayers(this)) {
                addRenderLayer(layer);
            }
        }

        if(animatable instanceof HoldingEntity) {
            HoldingEntity<T> holdingAnimatable = (HoldingEntity<T>) animatable;

            addRenderLayer(new HoldingItemLayer<T>(this, holdingAnimatable.getHoldingBone()));
        }
    }

    @Override
    public void render(T entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(babyScaling, babyScaling, babyScaling);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }


    // Add some particles around the ear when rendering
    public void renderFinal(PoseStack poseStack, T animatable, BakedGeoModel model, MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay, int colour) {
        if (animatable instanceof ParticleEmittingEntity){
            ParticleEmittingEntity<T> particleEmittingAnimatable = (ParticleEmittingEntity<T>) animatable;

            if(this.currentTick < 0 || this.currentTick != animatable.tickCount){
                this.currentTick = animatable.tickCount;

                particleEmittingAnimatable.emitParticles(model);
            }

        }

        super.renderFinal(poseStack, animatable, model, bufferSource, buffer, partialTick, packedLight, packedOverlay, colour);
    }

}
