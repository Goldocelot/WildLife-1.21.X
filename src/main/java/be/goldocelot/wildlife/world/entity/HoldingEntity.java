package be.goldocelot.wildlife.world.entity;

import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;

public interface HoldingEntity<T extends LivingEntity & GeoAnimatable> {
    public String getHoldingBone();
}
