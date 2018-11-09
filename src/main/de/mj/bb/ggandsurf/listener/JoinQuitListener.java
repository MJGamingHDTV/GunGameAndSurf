package main.de.mj.bb.ggandsurf.listener;

import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import main.de.mj.bb.ggandsurf.utils.GameType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class JoinQuitListener implements Listener {

    private final GunGameAndSurf gunGameAndSurf;

    public JoinQuitListener(@NotNull GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
        gunGameAndSurf.registerListener(this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent joinEvent) {
        Player player = joinEvent.getPlayer();
        joinEvent.setJoinMessage("§7[§a+§7] §b" + player.getName());
        Map<String, String> prow = new HashMap<>();
        prow.put("UUID", player.getUniqueId().toString());
        prow.put("KILLS", null);
        prow.put("DEATHS", null);
        prow.put("LEVEL", null);
        if (gunGameAndSurf.getGameType().equals(GameType.GUN_GAME)) {
            if (gunGameAndSurf.getCoreSpigot().getGameAPI().getMySQL().checkPlayer("GunGame", player.getUniqueId()))
                gunGameAndSurf.getCoreSpigot().getGameAPI().getMySQL().createPlayer("GunGame", player.getUniqueId(), prow);
        } else {
            if (gunGameAndSurf.getCoreSpigot().getGameAPI().getMySQL().checkPlayer("Surf", player.getUniqueId()))
                gunGameAndSurf.getCoreSpigot().getGameAPI().getMySQL().createPlayer("Surf", player.getUniqueId(), prow);
        }
        gunGameAndSurf.getScoreBoardManager().setScoreboard(player);
    }

}
