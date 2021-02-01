package net.kunmc.lab.throwablemobs;


import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class ThrowableMobs extends JavaPlugin implements Listener {

    public static Map<EntityType,IThrowable> throwables = new HashMap<>();
    public static final String NMS_VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(23);
    public static final String THROWING = "ThrowableMobs:throwing";
    public static final String LIFTING = "ThrowableMobs:isLifting";

    public static final String PLIFT = "ThrowableMobs:isPlayerLifting";
    public static final String LEID = "ThrowableMobs:liftingEntityId";
    public static final String HORSEFLAG = "ThrowableMobs:horse";
    public static Config config;

    public static ThrowableMobs plugin;
    public static Server server;
    //public static final double slamDamage = 4;
    public static final double baseAttackDamage = 8;
    public static final double speed = 0.0;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        new Thread(getServer()).runTaskTimer(this, 1, 1);
        Init.initThrowable(throwables);
        config = new Config(this);
        plugin = this;

    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void liftMob(PlayerInteractEntityEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR || event.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR) return;
        if(!(event.getRightClicked() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getRightClicked();
        if(entity.getMetadata(PLIFT).size()!=0 && entity.getMetadata(PLIFT).get(0).asBoolean())return;
        if(entity.getMetadata(THROWING).size()!=0){
            if(entity.getMetadata(THROWING).get(0).asBoolean()){
                entity.setMetadata(HORSEFLAG,new FixedMetadataValue(plugin,false));
            }
        }
        if(entity.getMetadata(LIFTING).size()!=0 && entity.getMetadata(LIFTING).get(0).asBoolean()){
            event.setCancelled(true);
            return;
        }
        Player player = event.getPlayer();

        player.setMetadata(LEID,new FixedMetadataValue(this,entity.getUniqueId().toString()));
        entity.setAI(false);
        //entity.setInvulnerable(true);
        //entity.setCollidable(false);
        entity.setMetadata(THROWING,new FixedMetadataValue(this,true));
        entity.setMetadata(LIFTING,new FixedMetadataValue(this,true));

        player.setMetadata(PLIFT,new FixedMetadataValue(this,true));


        event.setCancelled(true);
    }

    public static Location getLiftingLocation(Player player){
        Location location = player.getLocation().add(player.getLocation().getDirection().multiply(2)).add(0,1,0);
        return location;
    }

    @EventHandler
    public void cancelAttack(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getEntity();
        if(entity.getMetadata(LIFTING).size()!=0 && entity.getMetadata(LIFTING).get(0).asBoolean()){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void throwMob(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR || event.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR) return;
        if(event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if(player.getMetadata(PLIFT).isEmpty())return;
        if(!player.getMetadata(PLIFT).get(0).asBoolean())return;
        getLiftingEntity(player).ifPresent(entity -> {
            entity.setVelocity(player.getLocation().getDirection().multiply(3).add(player.getVelocity()));
            entity.setAI(true);
            onLaunching(player,entity);
            player.setMetadata(PLIFT,new FixedMetadataValue(this,false));
            entity.setMetadata(LIFTING,new FixedMetadataValue(this,false));
            /*System.out.println("-----------------------------");

            System.out.println(player.getMetadata(PLIFT).get(0).asBoolean());
            System.out.println(player.getMetadata(LEID).get(0).asString());
            System.out.println(entity.getMetadata(HORSEFLAG).get(0).asBoolean());
            System.out.println(entity.getMetadata(THROWING).get(0).asBoolean());
            System.out.println(entity.getMetadata(LIFTING).get(0).asBoolean());*/

            event.setCancelled(true);
        });
    }

    public static Optional<LivingEntity> getLiftingEntity(Player player){
        return Optional.ofNullable((LivingEntity) player.getWorld().getEntity(UUID.fromString(player.getMetadata(LEID).get(0).asString())));
    }

    public static Optional<IThrowable> getThrowable(LivingEntity entity){
        if(!throwables.containsKey(entity.getType())) return Optional.empty();
        return Optional.of(throwables.get(entity.getType()));
    }

    public static double getAttackDamage(LivingEntity entity){
        AtomicReference<Double> damage = new AtomicReference<>((double) 0);
        if(getThrowable(entity).isPresent()){
            getThrowable(entity).ifPresent(iThrowable -> {
                damage.set(iThrowable.getAttackDamage(entity));
            });
        }else{
            damage.set(baseAttackDamage);
        }
        return  damage.get();
    }

    public static void onLaunching(Player player, LivingEntity entity){
        getThrowable(entity).ifPresent(iThrowable -> {
            iThrowable.onLaunching(entity, player);
        });
    }

    public static void onLanding(LivingEntity entity){
        getThrowable(entity).ifPresent(iThrowable -> {
            iThrowable.onLanding(entity);
        });
    }

    public static void onLifting(LivingEntity entity, Player player){
        getThrowable(entity).ifPresent(iThrowable -> {
            iThrowable.onLifting(entity, player);
        });
    }

    public static void onGround(LivingEntity entity){
        entity.setMetadata(THROWING,new FixedMetadataValue(plugin,false));
        if(entity.getVelocity().length()>=speed) {
            onLanding(entity);
            entity.damage(config.getSlamDamage());
            entity.setMetadata(HORSEFLAG,new FixedMetadataValue(plugin,false));
        }
    }

    public static void onAir(LivingEntity entity){
        if (entity.getVelocity().length() >= speed) {
            double damage = getAttackDamage(entity);
            entity.getWorld().getNearbyEntities(entity.getBoundingBox(), entity1 -> entity1 instanceof LivingEntity && entity1.getType() != EntityType.PLAYER && entity1 != entity).stream().map(entity1 -> (LivingEntity) entity1)
                    .filter(entity1 -> entity1.getType() != EntityType.PLAYER)
                    .forEach(entity1 -> {
                        entity.setMetadata(THROWING,new FixedMetadataValue(plugin,false));
                        //entity.setInvulnerable(false);
                        onLanding(entity);
                        entity.damage(config.getSlamDamage());
                        entity1.damage(damage);
                    });
        }
    }

    public static class Thread extends BukkitRunnable {
        Server server;
        public Thread(Server server){
            this.server = server;
        }

        @Override
        public void run(){
            server.getOnlinePlayers().stream().filter(player -> !player.getMetadata(PLIFT).isEmpty())
                    .filter(player -> player.getMetadata(PLIFT).get(0).asBoolean())
                    .forEach(player -> {
                        if(!getLiftingEntity(player).isPresent()){
                            //System.out.println("ounnnnnn");

                            player.setMetadata(PLIFT,new FixedMetadataValue(plugin,false));
                        }else {
                            //System.out.println("hfgjhfgjhgfjhgf");

                            LivingEntity entity = getLiftingEntity(player).get();

                            entity.teleport(getLiftingLocation(player));
                            onLifting(entity, player);
                            entity.setFallDistance(0);
                            entity.setMetadata(HORSEFLAG,new FixedMetadataValue(plugin,true));
                        }
                    });


            server.getWorlds().stream().flatMap(world -> world.getEntitiesByClass(LivingEntity.class).stream()).filter(entity -> entity.getMetadata(THROWING).size() != 0)
                    .filter(entity -> entity.getMetadata(THROWING).get(0).asBoolean() && !isRiding(entity))
                    .forEach(entity -> {
                        //System.out.println("--------------");

                        //System.out.println(entity.getVelocity());

                        entity.setFallDistance(0);
                        onAir(entity);
                        if (isOnGround(entity)) {
                            onGround(entity);
                        }
                        //System.out.println(entity.getVelocity());

                    });
        }
    }

    public static boolean isOnGround(LivingEntity entity){
        return entity.isOnGround() && entity.getLocation().add(0,-0.1,0).getBlock().getType() != Material.AIR;
    }

    public static boolean isRiding(LivingEntity entity){
        if(entity.getMetadata(LIFTING).isEmpty()) return false;
        return entity.getMetadata(LIFTING).get(0).asBoolean();
    }
}
