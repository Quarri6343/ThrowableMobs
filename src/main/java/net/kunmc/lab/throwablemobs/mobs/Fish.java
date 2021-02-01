package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;

public class Fish implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        IThrowable.sphere((location, aDouble) -> {
            if(location.getBlock().getType() != Material.AIR) return;
            IThrowable.runLater(o -> {
                location.getBlock().setType(Material.WATER);
            }, (long) (aDouble*2));
        },entity.getLocation(),2);
    }
}
