package com.spaceniklas.aiswear;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Plugin extends JavaPlugin {

    private Swear swear;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new CoreListener(this), this);
        System.out.println(getBlacklist());
        System.out.println(getApikey());
        System.out.println(getSwearmessage());
        System.out.println(getSwearaction());
        saveDefaultConfig();
        swear = new Swear(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Swear getSwear() {
        return swear;
    }

    public List<String> getBlacklist() {
        return getConfig().getStringList("black-listed-words");
    }

    public String getApikey() {
        return getConfig().getString("api-key");
    }

    public String getSwearmessage() {
        return getConfig().getString("swear-message");
    }

    public String getSwearaction() {
        return getConfig().getString("swear-action");
    }

}
