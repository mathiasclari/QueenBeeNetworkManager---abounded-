package net.acticraft.plugins.queenbeenetwork.FriendsCommand;

import net.acticraft.plugins.queenbeenetwork.QueenBeeNetwork;
import net.acticraft.plugins.queenbeenetwork.data.FInfo;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class FriendsCommand extends Command {

    public FriendsCommand() {
        super("friends");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            int len = args.length;
            if (len == 0) {
                FInfo fi = QueenBeeNetwork.listFriendsInfos.get(player.getUniqueId());
                if (fi != null) {
                    fi.DisplayFriendList(player, 0);
                }
            }
        }
    }
}