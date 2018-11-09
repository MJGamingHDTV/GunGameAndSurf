package main.de.mj.bb.ggandsurf.commands;

import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final GunGameAndSurf gunGameAndSurf;

    public SpawnCommand(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
        gunGameAndSurf.registerCommand("spawn", this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.teleport(gunGameAndSurf.getData().getSpawn());
        }
        return false;
    }
}
