package net.acticraft.plugins.queenbeenetwork;

import net.acticraft.plugins.queenbeenetwork.AdminCommands.CheckOnlineCommand;
import net.acticraft.plugins.queenbeenetwork.AdminCommands.MaintenanceCommand;
import net.acticraft.plugins.queenbeenetwork.BuilderCommands.BuildCommand;
import net.acticraft.plugins.queenbeenetwork.MOTD.BungeeSettings;
import net.acticraft.plugins.queenbeenetwork.PlayerSenderSystem.HubCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class QueenBeeNetwork extends Plugin {

    public static boolean maintenance = false;

    @Override
    public void onEnable() {

        ProxyServer.getInstance().getPluginManager().registerListener(this, new BungeeSettings());


        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HubCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CheckOnlineCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MaintenanceCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BuildCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
