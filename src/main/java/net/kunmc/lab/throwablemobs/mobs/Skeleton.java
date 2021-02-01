package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Skeleton implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        for (int i = 0; i < 10; i++) {
            entity.getWorld().spawn(entity.getLocation(), Arrow.class, arrow -> {
                arrow.setShooter(entity);
                arrow.setVelocity(Vector.getRandom().add(new Vector(-0.5,0,-0.5)).multiply(1));
            });
        }
    }
}
