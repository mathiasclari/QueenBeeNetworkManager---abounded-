package net.acticraft.plugins.queenbeenetwork.BuilderCommands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BuildCommand extends Command {

    public BuildCommand() {
        super("build");
    }

    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (p.hasPermission("acs.builder.buildsrv")) {
                p.connect(ProxyServer.getInstance().getServerInfo("build"));
                p.sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§8§l[§e§lACTICRAFT§8§l] §7§l» §eConnecting to Build Server").create());

            }else{
                p.sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§cYou do not have required rank to use this Command").create());
            }




        }
    }


}
