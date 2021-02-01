package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Consumer;

public class Spider implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        Consumer<Location> placeWeb = location -> {
            if(location.getBlock().getType() == Material.AIR) {
                location.getBlock().setType(Material.COBWEB);
            }
        };

        entity.getWorld().spawnParticle(Particle.SPIT,entity.getLocation(),100,1,0,0,0.5,null,true);
        IThrowable.cube(placeWeb,entity.getLocation(),1);
        placeWeb.accept(entity.getLocation().clone().add(2,0,0));
        placeWeb.accept(entity.getLocation().clone().add(-2,0,0));
        placeWeb.accept(entity.getLocation().clone().add(0,2,0));
        placeWeb.accept(entity.getLocation().clone().add(0,-2,0));
        placeWeb.accept(entity.getLocation().clone().add(0,0,2));
        placeWeb.accept(entity.getLocation().clone().add(0,0,-2));

        placeWeb.accept(entity.getLocation().clone().add(2,2,2));
        placeWeb.accept(entity.getLocation().clone().add(-2,2,2));
        placeWeb.accept(entity.getLocation().clone().add(2,-2,2));
        placeWeb.accept(entity.getLocation().clone().add(2,2,-2));
        placeWeb.accept(entity.getLocation().clone().add(-2,-2,2));
        placeWeb.accept(entity.getLocation().clone().add(2,-2,-2));
        placeWeb.accept(entity.getLocation().clone().add(-2,2,-2));
        placeWeb.accept(entity.getLocation().clone().add(-2,-2,-2));
    }
}
