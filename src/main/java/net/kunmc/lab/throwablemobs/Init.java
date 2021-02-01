package net.kunmc.lab.throwablemobs;


import net.kunmc.lab.throwablemobs.mobs.*;
import net.kunmc.lab.throwablemobs.mobs.Bat;
import net.kunmc.lab.throwablemobs.mobs.Chicken;
import net.kunmc.lab.throwablemobs.mobs.Cow;
import net.kunmc.lab.throwablemobs.mobs.Creeper;
import net.kunmc.lab.throwablemobs.mobs.Enderman;
import net.kunmc.lab.throwablemobs.mobs.Fish;
import net.kunmc.lab.throwablemobs.mobs.Horse;
import net.kunmc.lab.throwablemobs.mobs.Llama;
import net.kunmc.lab.throwablemobs.mobs.Pig;
import net.kunmc.lab.throwablemobs.mobs.Sheep;
import net.kunmc.lab.throwablemobs.mobs.Skeleton;
import net.kunmc.lab.throwablemobs.mobs.Slime;
import net.kunmc.lab.throwablemobs.mobs.Spider;
import net.kunmc.lab.throwablemobs.mobs.Squid;
import net.kunmc.lab.throwablemobs.mobs.Villager;
import net.kunmc.lab.throwablemobs.mobs.Witch;
import net.kunmc.lab.throwablemobs.mobs.Zombie;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Consumer;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Init {
    public static void initThrowable(Map<EntityType,IThrowable> map){
        registerThrowable(EntityType.CREEPER, new Creeper());
        registerThrowable(EntityType.SKELETON, new Skeleton());
        registerThrowable(EntityType.STRAY, new Skeleton());
        registerThrowable(EntityType.ENDERMAN, new Enderman());
        registerThrowable(EntityType.ENDERMITE, new Enderman());
        registerThrowable(EntityType.ZOMBIE, new Zombie());
        registerThrowable(EntityType.ZOMBIE_HORSE, new Zombie());
        registerThrowable(EntityType.ZOMBIE_VILLAGER, new Zombie());
        registerThrowable(EntityType.DROWNED, new Zombie());
        registerThrowable(EntityType.HUSK, new Zombie());
        registerThrowable(EntityType.SPIDER, new Spider());
        registerThrowable(EntityType.CAVE_SPIDER, new Spider());
        registerThrowable(EntityType.WITCH, new Witch());
        registerThrowable(EntityType.HORSE, new Horse());
        registerThrowable(EntityType.DONKEY, new Horse());
        registerThrowable(EntityType.MULE, new Horse());
        registerThrowable(EntityType.LLAMA, new Llama());
        registerThrowable(EntityType.CHICKEN, new Chicken());
        registerThrowable(EntityType.BAT, new Bat());
        registerThrowable(EntityType.COW, new Cow());
        registerThrowable(EntityType.MUSHROOM_COW, new Cow());
        registerThrowable(EntityType.COD, new Fish());
        registerThrowable(EntityType.SALMON, new Fish());
        registerThrowable(EntityType.PUFFERFISH, new Fish());
        registerThrowable(EntityType.TROPICAL_FISH, new Fish());
        registerThrowable(EntityType.GUARDIAN, new Fish());
        registerThrowable(EntityType.ELDER_GUARDIAN, new Fish());
        registerThrowable(EntityType.DOLPHIN, new Fish());
        registerThrowable(EntityType.SHEEP, new Sheep());
        registerThrowable(EntityType.SLIME, new Slime());
        registerThrowable(EntityType.SQUID, new Squid());
        registerThrowable(EntityType.WITHER_SKELETON, new Nether());
        registerThrowable(EntityType.GHAST, new Nether());
        registerThrowable(EntityType.PIG_ZOMBIE, new Nether());
        registerThrowable(EntityType.BLAZE, new Nether());
        registerThrowable(EntityType.MAGMA_CUBE, new Nether());
        registerThrowable(EntityType.VILLAGER, new Villager());
        registerThrowable(EntityType.PIG,new Pig());
    }

    public static void registerThrowable(EntityType entityType, IThrowable throwable){
        ThrowableMobs.throwables.put(entityType,throwable);
    }
}
