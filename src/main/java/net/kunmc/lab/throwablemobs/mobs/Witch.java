package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class Witch implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        entity.getWorld().spawn(entity.getLocation(), ThrownPotion.class, thrownPotion -> {
            thrownPotion.setItem(new Potion(PotionType.POISON,2, true).toItemStack(1));
        });
        entity.getWorld().spawn(entity.getLocation(),ThrownPotion.class, thrownPotion -> {
            thrownPotion.setItem(new Potion(PotionType.WEAKNESS,1, true).toItemStack(1));
        });
        entity.getWorld().spawn(entity.getLocation(),ThrownPotion.class, thrownPotion -> {
            thrownPotion.setItem(new Potion(PotionType.INSTANT_DAMAGE,2, true).toItemStack(1));
        });
    }
}
