package me.jesusmx.hubcore.util.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.jesusmx.hubcore.TacoHubLite;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class BungeeListener implements PluginMessageListener {

    public static int PLAYER_COUNT;

    public static void updateCount(Player player) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerCount");
        output.writeUTF("ALL");
        player.sendPluginMessage(TacoHubLite.getInstance(), "BungeeCord", output.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equalsIgnoreCase("BungeeCord"))
            return;

        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subchannel = input.readUTF();
        if (subchannel.equals("PlayerCount") && input.readUTF().equalsIgnoreCase("ALL")) {
            BungeeListener.PLAYER_COUNT = input.readInt();
        }
    }

    public static void sendPlayerToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(TacoHubLite.getInstance(), "BungeeCord", out.toByteArray());
    }

    static {
        BungeeListener.PLAYER_COUNT = 0;
    }

}
