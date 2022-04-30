package net.acticraft.plugins.queenbeenetwork;

import net.acticraft.plugins.queenbeenetwork.MOTD.BungeeSettings;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class QueenBeeNetwork extends Plugin {

    @Override
    public void onEnable() {

        ProxyServer.getInstance().getPluginManager().registerListener(this, new BungeeSettings());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
