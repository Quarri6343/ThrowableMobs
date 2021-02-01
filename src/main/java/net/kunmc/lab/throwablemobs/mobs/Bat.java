package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Bat implements IThrowable {
    @Override
    public void onLifting(LivingEntity entity, Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,10,1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,10,2));
    }
}
