package net.kunmc.lab.throwablemobs.mobs;

import net.kunmc.lab.throwablemobs.IThrowable;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sheep implements IThrowable {
    public static List<Material> wools;
    static {
        Material materials[] ={Material.WHITE_WOOL,Material.BLACK_WOOL,Material.BLUE_WOOL, Material.BROWN_WOOL,Material.CYAN_WOOL,Material.GRAY_WOOL, Material.GREEN_WOOL,Material.LIGHT_BLUE_WOOL,Material.LIGHT_GRAY_WOOL,Material.LIME_WOOL,Material.MAGENTA_WOOL, Material.ORANGE_WOOL, Material.PINK_WOOL,Material.PURPLE_WOOL,Material.RED_WOOL,Material.YELLOW_WOOL};
        wools = Arrays.asList(materials);
    }

    @Override
    public void onLanding(LivingEntity entity) {
        IThrowable.sphere((location, aDouble) -> {
            if(location.getBlock().getType() != Material.AIR) return;
            int i = new Random().nextInt(wools.size());
            location.getBlock().setType(wools.get(i));
            location.getWorld().playSound(location, Sound.BLOCK_WOOD_PLACE,1,1);
        },entity.getLocation(),3);
    }
}
