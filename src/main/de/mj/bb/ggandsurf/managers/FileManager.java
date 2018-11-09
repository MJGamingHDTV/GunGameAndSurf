package main.de.mj.bb.ggandsurf.managers;

import lombok.Getter;
import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import main.de.mj.bb.ggandsurf.utils.GameType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

@Getter
public class FileManager {

    private final GunGameAndSurf gunGameAndSurf;
    private final String path = "plugins/GunGameAndSurf/";
    private File locationFile = new File(path, "locations.yml");
    private YamlConfiguration locationConfiguration = YamlConfiguration.loadConfiguration(locationFile);

    public FileManager(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
    }

    public void loadDefaultConfig() {
        gunGameAndSurf.getConfig().options().copyDefaults(true);
        gunGameAndSurf.saveDefaultConfig();
        if (gunGameAndSurf.getConfig().getBoolean("GunGame")) {
            gunGameAndSurf.getData().setPrefix("GunGame ");
            gunGameAndSurf.setGameType(GameType.GUN_GAME);
        } else {
            gunGameAndSurf.getData().setPrefix("Surf ");
            gunGameAndSurf.setGameType(GameType.SURF);
        }
    }

    public void loadLocationFile() {
        try {
            Location location = new Location(Bukkit.getWorld(locationConfiguration.getString("Spawn.World")), locationConfiguration.getDouble("Spawn.X"), locationConfiguration.getDouble("Spawn.Y"), locationConfiguration.getDouble("Spawn.Z"), (float) locationConfiguration.getDouble("Spawn.Yaw"), (float) locationConfiguration.getDouble("Spawn.Pitch"));
            gunGameAndSurf.getData().setSpawn(location);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}
