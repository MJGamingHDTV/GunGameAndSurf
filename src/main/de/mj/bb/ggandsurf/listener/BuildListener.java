package main.de.mj.bb.ggandsurf.listener;

import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {

    private final GunGameAndSurf gunGameAndSurf;

    public BuildListener(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
        gunGameAndSurf.registerListener(this);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent breakEvent) {
        if (!gunGameAndSurf.getBuildCommand().getPlayers().contains(breakEvent.getPlayer())) {
            breakEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent placeEvent) {
        if (!gunGameAndSurf.getBuildCommand().getPlayers().contains(placeEvent.getPlayer())) {
            placeEvent.setCancelled(true);
        }
    }
}
