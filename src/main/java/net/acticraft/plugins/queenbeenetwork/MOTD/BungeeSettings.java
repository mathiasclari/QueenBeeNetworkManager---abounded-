package net.acticraft.plugins.queenbeenetwork.MOTD;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class BungeeSettings implements Listener {
    @EventHandler
    public void onPing(ProxyPingEvent e){
        ServerPing serverPing = e.getResponse();
        serverPing.setDescription(ChatColor.of("#FFE29F")+"       Welcome to the"+ChatColor.BOLD+" ActiCraft Network"+ChatColor.of("#FFE29F")+" !"+"\n"+ChatColor.of("#d4b94e")+"                "+ChatColor.BOLD+"play.acticraft.net");
        serverPing.getPlayers().setMax(2000);
        e.setResponse(serverPing);


    }

}
