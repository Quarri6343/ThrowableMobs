package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.entity.LivingEntity;

public class Creeper implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        entity.getWorld().createExplosion(entity.getLocation(),4);
    }
}
