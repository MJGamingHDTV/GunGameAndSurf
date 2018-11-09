package main.de.mj.bb.ggandsurf.listener;

import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import main.de.mj.bb.ggandsurf.utils.GameType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class WaterListener implements Listener {

    private final GunGameAndSurf gunGameAndSurf;

    public WaterListener(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
        gunGameAndSurf.registerListener(this);
    }

    @EventHandler
    public void onWater(PlayerMoveEvent moveEvent) {
        Player player = moveEvent.getPlayer();
        Block block = player.getLocation().add(0, -1, 0).getBlock();
        if (block.getType().equals(Material.WATER) || block.getType().equals(Material.STATIONARY_WATER)) {
            if (gunGameAndSurf.getGameType().equals(GameType.GUN_GAME))
                player.spigot().respawn();
            else
                player.setVelocity(new Vector(0, 10, 0));
        }

    }


}
