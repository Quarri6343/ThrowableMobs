package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.LingeringPotion;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Horse implements IThrowable {
    @Override
    public void onLanding(LivingEntity entity) {
        int radius = 5;
        entity.getWorld().spawn(entity.getLocation(), AreaEffectCloud.class,areaEffectCloud -> {
            areaEffectCloud.setColor(Color.fromBGR(53,72,104));
            areaEffectCloud.setRadius(5);
            areaEffectCloud.setDuration(200);
            areaEffectCloud.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION,50,1),true);
        });
        IThrowable.sphere((location, r) -> {
            Material block = location.getBlock().getType();
            /*if(block == Material.DIRT ){
                IThrowable.runLater(o -> {
                    location.getBlock().setType(Material.COARSE_DIRT);
                    location.getWorld().playSound(location, Sound.BLOCK_GRASS_BREAK, (float) 0.1,1);
                },(long) (r*3));
            }*/
            if(block == Material.GRASS_BLOCK) {
                IThrowable.runLater(o->{
                    entity.getWorld().spawnParticle(Particle.BLOCK_CRACK,location,3, 1,-1,1,Material.GRASS.createBlockData());
                    location.getBlock().setType(Material.PODZOL);
                    location.getWorld().playSound(location,Sound.BLOCK_GRASS_BREAK, (float) 0.1,1);
                },(long) (r*3));
            }
            if(block == Material.GRASS || block == Material.TALL_GRASS || block==Material.ALLIUM ||
                    block == Material.AZURE_BLUET || block == Material.BLUE_ORCHID || block == Material.CORNFLOWER ||
                    block == Material.DANDELION || block == Material.LILAC || block == Material.LILY_OF_THE_VALLEY ||
                    block == Material.ORANGE_TULIP || block == Material.POPPY || block == Material.PINK_TULIP ||
                    block == Material.RED_TULIP || block == Material.ROSE_BUSH || block == Material.SUNFLOWER ||
                    block == Material.WHITE_TULIP) {
                IThrowable.runLater(o -> {
                    entity.getWorld().spawnParticle(Particle.BLOCK_CRACK,location,3, block.createBlockData());
                    location.getBlock().setType(Material.DEAD_BUSH);
                    location.getWorld().playSound(location,Sound.BLOCK_GRASS_BREAK, (float) 0.1,1);
                },(long) (r*3));
            }
            if(block == Material.OAK_LEAVES || block == Material.ACACIA_LEAVES || block == Material.BIRCH_LEAVES || block == Material.DARK_OAK_LEAVES || block == Material.JUNGLE_LEAVES || block == Material.SPRUCE_LEAVES){
                IThrowable.runLater(o -> {
                    entity.getWorld().spawnParticle(Particle.BLOCK_CRACK,location,3, block.createBlockData());
                    location.getBlock().setType(Material.AIR);
                    location.getWorld().playSound(location,Sound.BLOCK_GRASS_BREAK, (float) 0.3,1);
                },(long) (r*3));
            }
        }, entity.getLocation(),radius);
    }
}
