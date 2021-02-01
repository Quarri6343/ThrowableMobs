package net.kunmc.lab.throwablemobs;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Consumer;

import java.util.function.BiConsumer;

public interface IThrowable {
    public default void onLanding(LivingEntity entity){

    }
    public default void onLaunching(LivingEntity entity, Player player){

    }
    public default void onLifting(LivingEntity entity, Player player){

    }

    public default double getAttackDamage(LivingEntity entity){
        if(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null){
            return entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue();
        }
        return 8;
    }

    public static void sphere(BiConsumer<Location, Double> consumer, Location base, int radius){
        for (int x = -radius; x < radius+1; x++) {
            for (int y = -radius; y < radius+1; y++) {
                for (int z = -radius; z < radius+1; z++) {
                    Location location1 = base.clone().add(x,y,z);
                    double sqrt = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
                    if(sqrt > radius) continue;
                    consumer.accept(location1,sqrt);
                }
            }
        }
    }

    public static void cube(org.bukkit.util.Consumer<Location> consumer, Location base, int radius){
        for (int x = -radius; x < radius+1; x++) {
            for (int y = -radius; y < radius+1; y++) {
                for (int z = -radius; z < radius+1; z++) {
                    Location location1 = base.clone().add(x,y,z);
                    consumer.accept(location1);
                }
            }
        }
    }

    public static void runLater(Consumer<Object> consumer, long time){
        new BukkitRunnable() {
            @Override
            public void run() {
                consumer.accept(new Object());
            }
        }.runTaskLater(ThrowableMobs.plugin, time);
    }
}
