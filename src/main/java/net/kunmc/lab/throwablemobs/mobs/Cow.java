package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import net.kunmc.lab.throwablemobs.ThrowableMobs;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Collection;

public class Cow implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
//        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_COW_MILK, 3, 1);
//        entity.getWorld().spawnParticle(Particle.SPIT,entity.getLocation(),1000,1,0,0,1,null,true);
        entity.getWorld().getNearbyEntitiesByType(LivingEntity.class,entity.getLocation(),10).forEach(entity1-> {
            Collection<PotionEffect> effects = entity1.getActivePotionEffects();
            Collection<PotionEffect> neweffects = new ArrayList<>();
            effects.forEach(potionEffect -> {
                neweffects.add(potionEffect);
            });
            neweffects.forEach(potionEffect -> {
                entity1.removePotionEffect(potionEffect.getType());
            });
        });
    }
}
