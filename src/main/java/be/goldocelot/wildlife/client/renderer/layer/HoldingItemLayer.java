package be.goldocelot.wildlife.client.renderer.layer;

import be.goldocelot.wildlife.world.entity.HoldingEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;

import javax.annotation.Nullable;

public class HoldingItemLayer<T extends LivingEntity & GeoAnimatable> extends BlockAndItemGeoLayer<T>{

    private String boneId;

    public HoldingItemLayer(GeoRenderer<T> renderer, String boneId) {
        super(renderer);
    }

    @Nullable
    @Override
    protected ItemStack getStackForBone(GeoBone bone, T animatable) {
        if(bone.getName().equals(boneId)) return animatable.getMainHandItem();
        return super.getStackForBone(bone, animatable);
    }

    @Override
    protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, T animatable) {
        if(bone.getName().equals(boneId)) return  ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
        return super.getTransformTypeForStack(bone, stack, animatable);
    }

    @Override
    protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, T animatable,
                                      MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
        poseStack.translate(1/8f,1/8f,-1f/8);

        poseStack.mulPose(Axis.XP.rotationDegrees(90));
        poseStack.mulPose(Axis.ZP.rotationDegrees(45));

        super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
    }
}
