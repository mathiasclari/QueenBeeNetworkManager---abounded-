package net.acticraft.plugins.queenbeenetwork;

import com.google.common.io.ByteStreams;
import net.acticraft.plugins.queenbeenetwork.AdminCommands.CheckOnlineCommand;
import net.acticraft.plugins.queenbeenetwork.AdminCommands.MaintenanceCommand;
import net.acticraft.plugins.queenbeenetwork.BuilderCommands.BuildCommand;
import net.acticraft.plugins.queenbeenetwork.FriendsCommand.FriendCommand;
import net.acticraft.plugins.queenbeenetwork.FriendsCommand.FriendsCommand;
import net.acticraft.plugins.queenbeenetwork.MOTD.BungeeSettings;
import net.acticraft.plugins.queenbeenetwork.PlayerSenderSystem.HubCommand;
import net.acticraft.plugins.queenbeenetwork.data.FData;
import net.acticraft.plugins.queenbeenetwork.data.FInfo;
import net.acticraft.plugins.queenbeenetwork.data.MySQL;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.*;
import java.util.List;
import java.util.UUID;

public final class QueenBeeNetwork extends Plugin {

    public static boolean maintenance = false;

    public static Map<UUID, FInfo> listFriendsInfos = new HashMap<UUID, FInfo>(); //uuid, info
    public static Map<UUID, FData> listFriendData = new HashMap<UUID, FData>(); //server, info

    Configuration config;

    private static QueenBeeNetwork plugin;

    public static QueenBeeNetwork getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        ProxyServer.getInstance().getPluginManager().registerListener(this, new BungeeSettings());
        ProxyServer.getInstance().getPluginManager().registerListener(this, new EventListener(this));
//Commands
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HubCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new CheckOnlineCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new MaintenanceCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BuildCommand());
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new FriendCommand("f"));
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new FriendCommand("friend"));
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new FriendsCommand());
        LoadData();
    }

    private void LoadData() {
        GetDefaultConfig();
        String host = config.getString("mysql.host");
        String database = config.getString("mysql.databse");
        String username = config.getString("mysql.username");
        String password = config.getString("mysql.password");
        int port = config.getInt("mysql.port");
        MySQL.Login(host, database, username, password, port);
        MySQL.CreateTables();

    }

    void GetDefaultConfig() {
        File file = new File(getDataFolder(), "config.yml");
        try {
            if (!file.exists()) {
                if (!getDataFolder().exists())
                    getDataFolder().mkdir();
                file.createNewFile();
                InputStream is = getResourceAsStream("config.yml");
                Throwable localThrowable6 = null;
                try {
                    OutputStream os = new FileOutputStream(file);
                    try {
                        ByteStreams.copy(is, os);
                    } catch (Throwable localThrowable1) {
                        throw localThrowable1;
                    }
                } catch (Throwable localThrowable4) {
                    localThrowable6 = localThrowable4;
                    throw localThrowable4;
                } finally {
                    if (is != null)
                        if (localThrowable6 != null) {
                            try {
                                is.close();
                            } catch (Throwable localThrowable5) {
                                localThrowable6.addSuppressed(localThrowable5);
                            }
                        } else {
                            is.close();
                        }
                }
            }
            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AddFriendConnection(UUID uuid, String name, boolean update) {
        FData fd = listFriendData.get(uuid);
        if(fd != null) {
            fd.AddConnectionCount();
            if(update) {
                fd.UpdateName(name);
            }
        } else {
            fd = new FData(uuid, name);
            listFriendData.put(uuid, fd);
        }
    }

    public static void RemoveFriendConnections(List<UUID> uuids) {
        for(UUID uuid : uuids) {
            RemoveFriendConnections(uuid);
        }
    }

    public static void RemoveFriendConnections(UUID uuid) {
        listFriendData.get(uuid).RemoveConnectionCount();
    }


}
