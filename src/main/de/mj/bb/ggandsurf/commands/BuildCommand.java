package main.de.mj.bb.ggandsurf.commands;

import lombok.Getter;
import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

@Getter
public class BuildCommand implements CommandExecutor {

    private final GunGameAndSurf gunGameAndSurf;
    private Set<Player> players = new HashSet<>();

    public BuildCommand(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
        gunGameAndSurf.registerCommand("build", this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("ggas.build")) {
                if (players.contains(player)) {
                    players.remove(player);
                    //TODO SendMessage
                } else {
                    players.add(player);
                    //TODO SendMessage
                }
            } else player.sendMessage(""); //TODO SendNoPerm
        } else commandSender.sendMessage(""); // TODO onlyPlayer
        return false;
    }
}
