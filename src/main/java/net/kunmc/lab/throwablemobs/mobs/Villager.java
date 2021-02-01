package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Villager implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        org.bukkit.entity.Villager villager = (org.bukkit.entity.Villager) entity;
        villager.getRecipes().forEach(merchantRecipe ->{
            villager.getWorld().spawn(villager.getLocation(), Item.class,item -> {
                item.setItemStack(merchantRecipe.getResult());
                item.setVelocity(Vector.getRandom().add(new Vector(-0.5,0,-0.5)).multiply(0.1));
            });
        });
        villager.damage(9999);
    }
}
