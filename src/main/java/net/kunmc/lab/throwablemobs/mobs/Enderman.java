package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

public class Enderman implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        int radius = 5;
        Location location = entity.getLocation();
        IThrowable.sphere((location1, r) -> {
            location1.getBlock().setType(Material.AIR);
            entity.getWorld().spawnParticle(Particle.PORTAL,location1, 10);
        },location,radius);
        entity.remove();
        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT,2.0f,1.0f);
    }
}
