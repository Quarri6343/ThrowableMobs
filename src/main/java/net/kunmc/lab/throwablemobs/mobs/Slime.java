package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.Random;

public class Slime implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        org.bukkit.entity.Slime slime = (org.bukkit.entity.Slime) entity;
        int size = slime.getSize();
        if(size ==1) return;
        Random rnd = new Random();
        for (int i = 0; i < rnd.nextInt(10)+2; i++) {
            slime.getWorld().spawn(entity.getLocation(),slime.getType().getEntityClass(),entity1 ->{
               org.bukkit.entity.Slime slime1 = (org.bukkit.entity.Slime) entity1;
               int newsize = rnd.nextInt(size);
               if(newsize <= 0) newsize =1;
               slime1.setSize(newsize);
               slime1.setVelocity(Vector.getRandom().add(new Vector(-0.5,0,-0.5)).multiply(1.5));
            });
        }
        slime.setSize(slime.getSize()-1);
    }
}
