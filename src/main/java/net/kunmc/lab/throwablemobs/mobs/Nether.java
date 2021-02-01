package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class Nether implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        IThrowable.sphere((location, aDouble) -> {
            if(location.getBlock().getType()!= Material.AIR) return;
            location.getBlock().setType(Material.FIRE);
        },entity.getLocation(),5);
    }
}
