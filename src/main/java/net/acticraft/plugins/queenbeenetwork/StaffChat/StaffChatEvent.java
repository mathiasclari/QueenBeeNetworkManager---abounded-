package net.acticraft.plugins.queenbeenetwork.StaffChat;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;



public class StaffChatEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent event) {
        if (event.getMessage().startsWith("#")) {
            if(event.getSender() instanceof ProxiedPlayer) {
                ProxiedPlayer player = (ProxiedPlayer) event.getSender();
                if(player.hasPermission("staffchat.use")) {
                    TextComponent text = new TextComponent(ChatColor.of("#FFBF00")+""+ChatColor.BOLD+"SC " +ChatColor.of("#738291")+ ChatColor.BOLD +"» " +ChatColor.of("#F28C28") +player.getDisplayName()+ChatColor.of("#FAD5A5")+event.getMessage().replace("#", ""));
                    event.setCancelled(true);
                    for (ProxiedPlayer p : BungeeCord.getInstance().getPlayers()) {
                        if (p.hasPermission("staffchat.use")) {
                            p.sendMessage(text);



                        }
                    }
                }
            }
        }
    }
}
