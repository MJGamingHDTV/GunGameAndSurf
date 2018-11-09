package main.de.mj.bb.ggandsurf.listener;

import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class DeathListener implements Listener {
    private final GunGameAndSurf gunGameAndSurf;

    public DeathListener(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
        gunGameAndSurf.registerListener(this);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent deathEvent) {
        Player player = deathEvent.getEntity().getPlayer();
        Player killer = player.getKiller();
        killer.setLevel(killer.getLevel() + 1);
        /*
        TODO
        save level
        get Killer -> change kills, change lvl
        get Death -> change deaths, change lvl
        send spawn
         */
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent respawnEvent) {
        Player player = respawnEvent.getPlayer();
        player.teleport(gunGameAndSurf.getData().getSpawn());
        player.setLevel(player.getLevel() / 2);
        //TODO MySQL
        //TODO für MJ -> Goody loben das er ganz toll ist >:)
        player.sendMessage("Neues Level: " + player.getLevel());
    }

}
