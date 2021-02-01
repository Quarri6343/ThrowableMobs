package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.util.Vector;

public class Llama implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        entity.getWorld().getNearbyEntitiesByType(LivingEntity.class,entity.getLocation(),10).forEach(entity1 -> {
            if(entity == entity1) return;
            entity.getWorld().spawn(entity.getEyeLocation(), LlamaSpit.class,llamaSpit -> {

                Vector vec = entity1.getLocation().toVector().subtract(entity.getLocation().toVector());
                Vector vec2 = vec.multiply(1/vec.length());
                llamaSpit.setVelocity(vec2);
                llamaSpit.setGravity(false);
                llamaSpit.setShooter(entity);
            });
        });

    }
}
