package main.de.mj.bb.ggandsurf;

import lombok.Getter;
import lombok.Setter;
import main.de.mj.bb.core.CoreSpigot;
import main.de.mj.bb.core.mysql.ColumnType;
import main.de.mj.bb.ggandsurf.commands.BuildCommand;
import main.de.mj.bb.ggandsurf.commands.SetSpawnCommand;
import main.de.mj.bb.ggandsurf.commands.SpawnCommand;
import main.de.mj.bb.ggandsurf.listener.BuildListener;
import main.de.mj.bb.ggandsurf.listener.DeathListener;
import main.de.mj.bb.ggandsurf.listener.JoinQuitListener;
import main.de.mj.bb.ggandsurf.listener.WaterListener;
import main.de.mj.bb.ggandsurf.managers.FileManager;
import main.de.mj.bb.ggandsurf.managers.ScoreBoardManager;
import main.de.mj.bb.ggandsurf.utils.Data;
import main.de.mj.bb.ggandsurf.utils.GameType;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GunGameAndSurf extends JavaPlugin {

    private CoreSpigot coreSpigot;
    private Data data;
    private GameType gameType;
    private FileManager fileManager;
    private ScoreBoardManager scoreBoardManager;
    private BuildCommand buildCommand;

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[GunGameAndSurf] is starting...");
        Bukkit.getConsoleSender().sendMessage("[GunGameAndSurf] by " + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("[GunGameAndSurf] version: " + this.getDescription().getVersion());
        if (Bukkit.getPluginManager().getPlugin("BBCoreSpigot") != null)
            this.coreSpigot = (CoreSpigot) Bukkit.getPluginManager().getPlugin("BBCoreSpigot");
        else Bukkit.getPluginManager().disablePlugin(this);
        init();
        Bukkit.getConsoleSender().sendMessage("[GunGameAndSurf] detected GameType " + gameType.toString());
    }

    private void init() {
        data = new Data();
        fileManager = new FileManager(this);
        fileManager.loadDefaultConfig();
        fileManager.loadLocationFile();
        buildCommand = new BuildCommand(this);
        new BuildListener(this);
        createTables();
        new SetSpawnCommand(this);
        new SpawnCommand(this);
        new DeathListener(this);
        new JoinQuitListener(this);
        new WaterListener(this);
        scoreBoardManager = new ScoreBoardManager(this);
    }

    public void onDisable() {

    }

    public void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void registerCommand(String command, CommandExecutor commandExecutor) {
        getCommand(command).setExecutor(commandExecutor);
    }

    private void createTables() {
        if (gameType.equals(GameType.GUN_GAME)) {
            Map<String, ColumnType> rows = new HashMap<>();
            rows.put("UUID", ColumnType.VARCHAR);
            rows.put("DEATHS", ColumnType.INTEGER);
            rows.put("KILLS", ColumnType.INTEGER);
            rows.put("LEVEL", ColumnType.INTEGER);
            try {
                if (gameType.equals(GameType.GUN_GAME))
                    coreSpigot.getGameAPI().getMySQL().createTable("GunGame", rows);
                else
                    coreSpigot.getGameAPI().getMySQL().createTable("Surf", rows);
            } catch (SQLException sql) {
                sql.printStackTrace();
            }
        }
    }
}
