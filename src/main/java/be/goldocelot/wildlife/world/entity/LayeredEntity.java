package be.goldocelot.wildlife.world.entity;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import java.util.List;

public interface LayeredEntity<T extends LivingEntity & GeoAnimatable> {

    public List<GeoRenderLayer<T>> getRenderLayers(GeoRenderer<T> entityRenderer);
}
