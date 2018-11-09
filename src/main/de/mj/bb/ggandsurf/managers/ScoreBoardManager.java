package main.de.mj.bb.ggandsurf.managers;

import main.de.mj.bb.ggandsurf.GunGameAndSurf;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ScoreBoardManager {
    private final GunGameAndSurf gunGameAndSurf;

    public ScoreBoardManager(GunGameAndSurf gunGameAndSurf) {
        this.gunGameAndSurf = gunGameAndSurf;
    }

    private static void sendPacket(Packet packet, Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void setScoreboard(Player player) {
        Scoreboard scoreboard = new Scoreboard();
        ScoreboardObjective objective = scoreboard.registerObjective("score", IScoreboardCriteria.b);
        objective.setDisplayName(gunGameAndSurf.getData().getPrefix());
        PacketPlayOutScoreboardObjective createPacket = new PacketPlayOutScoreboardObjective(objective, 0);
        PacketPlayOutScoreboardDisplayObjective displayObjective = new PacketPlayOutScoreboardDisplayObjective(1, objective);
        ScoreboardScore score1 = new ScoreboardScore(scoreboard, objective, "Deine Coins");


        PacketPlayOutScoreboardObjective removeObjective = new PacketPlayOutScoreboardObjective(objective, 1);
        PacketPlayOutScoreboardScore play1 = new PacketPlayOutScoreboardScore(score1);
        sendPacket(removeObjective, player);
        sendPacket(createPacket, player);
        sendPacket(displayObjective, player);
        sendPacket(play1, player);
    }

}
