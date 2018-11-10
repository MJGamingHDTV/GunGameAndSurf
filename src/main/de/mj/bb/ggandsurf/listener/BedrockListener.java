package main.de.mj.bb.ggandsurf.listener;

import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BedrockListener implements Listener {

    private final GunGameAndSurf gunGameAndSurf;
    private Set<UUID> playerInCountdown = new HashSet<>();

    public BedrockListener(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
        gunGameAndSurf.registerListener(this);
    }

    @EventHandler
    public void onBedrock(PlayerMoveEvent moveEvent) {
        Player player = moveEvent.getPlayer();
        Material block = player.getLocation().add(0, -1, 0).getBlock().getType();
        if (block == Material.BEDROCK) {
            if (!playerInCountdown.contains(player.getUniqueId())) {
                playerInCountdown.add(player.getUniqueId());
                new BukkitRunnable() {
                    int counter = 15;

                    @Override
                    public void run() {
                        if (!isOnBedrock(player)) {
                            playerInCountdown.remove(player.getUniqueId());
                            cancel();
                            return;
                        }
                        if (counter == 0) {
                            int random = (int) (Math.random() * 4);
                            //TODO Items
                            playerInCountdown.remove(player.getUniqueId());
                            cancel();
                        }
                        counter--;
                    }
                }.runTaskTimer(gunGameAndSurf, 0L, 20L);
            }
        }
    }

    private boolean isOnBedrock(Player player) {
        Material material = player.getLocation().add(0, -1, 0).getBlock().getType();
        return (material.equals(Material.BEDROCK));
    }
}
