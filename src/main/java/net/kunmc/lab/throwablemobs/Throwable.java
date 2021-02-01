package net.kunmc.lab.throwablemobs;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Throwable implements IThrowable{
    private Consumer<LivingEntity> landing;
    private Consumer<LivingEntity> lifting;
    private BiConsumer<LivingEntity,Player> launching;
    public Throwable(Consumer<LivingEntity> landing,Consumer<LivingEntity> lifting, BiConsumer<LivingEntity,Player> launching){
        this.landing = landing;
        this.lifting = lifting;
        this.launching = launching;
    }
    public void onLanding(LivingEntity entity){
        landing.accept(entity);
    }
    public void onLifting(LivingEntity entity){
        lifting.accept(entity);
    }
    public void onLaunching(LivingEntity entity, Player player){
        launching.accept(entity, player);
    }

    public static Throwable getInstance(Consumer<LivingEntity> landing, Consumer<LivingEntity> lifting,BiConsumer<LivingEntity,Player > launching){
        return new Throwable(landing,lifting, launching);
    }
}
