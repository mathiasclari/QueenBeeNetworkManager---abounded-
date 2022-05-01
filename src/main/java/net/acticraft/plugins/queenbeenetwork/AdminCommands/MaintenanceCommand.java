package net.acticraft.plugins.queenbeenetwork.AdminCommands;

import net.acticraft.plugins.queenbeenetwork.QueenBeeNetwork;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MaintenanceCommand extends Command {

    public MaintenanceCommand() {
        super("maintenance");
    }

    public void execute(CommandSender sender, String[] args) {
        if ((sender instanceof ProxiedPlayer)) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if (p.hasPermission("acs.staff.superadmin")) {
                QueenBeeNetwork.maintenance = !QueenBeeNetwork.maintenance;
                if (QueenBeeNetwork.maintenance == true) {
                    p.sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§8§l[§c§lACTICRAFT§8§l] §7§l» §cMaintenance has been enabled").create());
                }else {
                    p.sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§8§l[§c§lACTICRAFT§8§l] §7§l» §cMaintenance has been disabled").create());
                }
            }


        }
    }
}