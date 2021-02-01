package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Zombie implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        for (int i = 0; i < 4; i++) {
            entity.getWorld().spawn(entity.getLocation().add(Vector.getRandom().multiply(0.1)), entity.getType().getEntityClass());
        }
        entity.getWorld().spawnParticle(Particle.BLOCK_CRACK, entity.getLocation().clone().add(0,1,0),500,0,0,0,2,Material.REDSTONE_BLOCK.createBlockData());
    }
}
