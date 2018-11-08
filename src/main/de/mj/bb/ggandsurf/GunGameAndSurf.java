package main.de.mj.bb.ggandsurf;

import lombok.Getter;
import main.de.mj.bb.core.CoreSpigot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class GunGameAndSurf extends JavaPlugin {

    private CoreSpigot coreSpigot;

    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("CoreSpigot") != null) {
            this.coreSpigot = (CoreSpigot) Bukkit.getPluginManager().getPlugin("CoreSpigot");
        }
    }

    public void onDisable() {

    }
}
