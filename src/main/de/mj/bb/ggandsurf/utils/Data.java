package main.de.mj.bb.ggandsurf.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
@Setter
public class Data {

    private String prefix;
    private String onlyPlayer = prefix + "Error";

    private Location spawn;

}
