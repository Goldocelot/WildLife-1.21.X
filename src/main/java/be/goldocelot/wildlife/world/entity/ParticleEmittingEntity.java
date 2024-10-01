package be.goldocelot.wildlife.world.entity;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.cache.object.BakedGeoModel;

public interface ParticleEmittingEntity<T extends LivingEntity & GeoAnimatable> {
    public void emitParticles(BakedGeoModel model);
}
