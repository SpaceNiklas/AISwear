package com.spaceniklas.aiswear;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CoreListener implements Listener {

    Plugin plugin;

    public CoreListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e) {
        e.getPlayer().sendMessage("<" + e.getPlayer().getName() + "> " + e.getMessage());
        e.setCancelled(true);
        if (plugin.getSwear().check(e.getMessage())) {
            if (!plugin.getSwearmessage().equalsIgnoreCase("-"))
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getSwearmessage()).replace("%player%", e.getPlayer().getName()).replace("%message%", e.getMessage()));
            if (!plugin.getSwearaction().equalsIgnoreCase("-"))
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), plugin.getSwearaction().replace("%player%", e.getPlayer().getName()).replace("%message%", e.getMessage()));

            LogFile.put(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName(), e.getMessage(), plugin);
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.getUniqueId().equals(e.getPlayer().getUniqueId())) {
                    p.sendMessage("<" + e.getPlayer().getName() + "> " + e.getMessage());
                }
            }
        }
    }

}
