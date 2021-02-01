package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.Color;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Squid implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        entity.getWorld().spawn(entity.getLocation(), AreaEffectCloud.class,areaEffectCloud -> {
            areaEffectCloud.setColor(Color.BLACK);
            areaEffectCloud.setRadius(5);
            areaEffectCloud.setDuration(200);
            areaEffectCloud.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS,50,1),true);
        });
    }
}
