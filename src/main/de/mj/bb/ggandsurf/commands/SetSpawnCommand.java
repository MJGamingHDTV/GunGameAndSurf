package main.de.mj.bb.ggandsurf.commands;

import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetSpawnCommand implements CommandExecutor {
    private final GunGameAndSurf gunGameAndSurf;

    public SetSpawnCommand(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
        gunGameAndSurf.registerCommand("setspawn", this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            gunGameAndSurf.getFileManager().getLocationConfiguration().set("Spawn.World", location.getWorld().getName());
            gunGameAndSurf.getFileManager().getLocationConfiguration().set("Spawn.X", location.getX());
            gunGameAndSurf.getFileManager().getLocationConfiguration().set("Spawn.Y", location.getY());
            gunGameAndSurf.getFileManager().getLocationConfiguration().set("Spawn.Z", location.getZ());
            gunGameAndSurf.getFileManager().getLocationConfiguration().set("Spawn.Yaw", location.getYaw());
            gunGameAndSurf.getFileManager().getLocationConfiguration().set("Spawn.Pitch", location.getPitch());
            try {
                gunGameAndSurf.getFileManager().getLocationConfiguration().save(gunGameAndSurf.getFileManager().getLocationFile());
                player.sendMessage(gunGameAndSurf.getData().getPrefix() + "§aDer Spawn wurde erfolgreich gesetzt.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else
            sender.sendMessage(gunGameAndSurf.getData().getOnlyPlayer());

        return false;
    }
}
