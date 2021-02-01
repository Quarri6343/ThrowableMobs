package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import net.kunmc.lab.throwablemobs.ThrowableMobs;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;
public class Pig implements IThrowable {
    public static AtomicInteger i=new AtomicInteger(0);
    @Override
    public void onLanding(LivingEntity entity) {
        entity.getWorld().getNearbyEntitiesByType(LivingEntity.class,entity.getLocation(),4).forEach(entity1 ->{
            entity1.damage(2,entity);
        });
        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR,2,1);

        IThrowable.sphere((location, aDouble) -> {
            Block block = location.getBlock();
            i.getAndIncrement();

            sendPacket(ThrowableMobs.server,getPacket(block.getX(), block.getY()-1, block.getZ(), 8, i.get()));
            if(location.add(0,1,0).getBlock().getType()!= Material.AIR) return;
            entity.getWorld().spawnParticle(Particle.BLOCK_CRACK,location,10, 1,0.01,1, block.getType().createBlockData());

        },entity.getLocation(),4);
    }

    public static void sendPacket(Server server, Object packet){
        try {
            Class craftserverclass = Class.forName("org.bukkit.craftbukkit." + ThrowableMobs.NMS_VERSION + ".CraftServer");
            Class packetclass = Class.forName("net.minecraft.server." + ThrowableMobs.NMS_VERSION + ".Packet");
            Object craftserver = craftserverclass.cast(server);
            Object playerlist = craftserverclass.getMethod("getHandle").invoke(craftserver);
            Class.forName("net.minecraft.server." + ThrowableMobs.NMS_VERSION + ".PlayerList").getMethod("sendAll",packetclass).invoke(playerlist,packet);
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static Object getPacket(int x, int y, int z,int level, int i) {
        try {
            Class blockpositionclass = Class.forName("net.minecraft.server." + ThrowableMobs.NMS_VERSION + ".BlockPosition");
            Object pos = blockpositionclass.getConstructor(int.class,int.class,int.class).newInstance(x,y,z);
            Object packet =Class.forName("net.minecraft.server." + ThrowableMobs.NMS_VERSION + ".PacketPlayOutBlockBreakAnimation").getConstructor(int.class,blockpositionclass,int.class).newInstance(i,pos,level);
            return packet;
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException e) {
            throw new Error(e);
        }
    }

    @Override
    public double getAttackDamage(LivingEntity entity) {
        return 40;
    }
}
