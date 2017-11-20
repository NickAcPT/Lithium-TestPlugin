package net.nickac.testing.lithium;

import net.nickac.lithium.backend.controls.impl.LImage;
import net.nickac.lithium.backend.controls.impl.LPanel;
import net.nickac.lithium.backend.controls.impl.LWindow;
import net.nickac.lithium.frontend.LithiumPlugin;
import net.nickac.testing.lithium.exampleElements.TextPanel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by NickAc!
 */
public class LithiumTest extends JavaPlugin {

    private Map<UUID, OverlayPlayer> overlayPlayers = new HashMap<>();

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void on(PlayerMoveEvent e) {
                try {
                    overlayPlayers.get(e.getPlayer().getUniqueId()).updateOverlay();
                } catch (NullPointerException ignored) {
                }
            }


            @EventHandler
            public void on(PlayerQuitEvent e) {
                overlayPlayers.remove(e.getPlayer().getUniqueId());
            }


        }, this);


        Bukkit.getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void on(PlayerCommandPreprocessEvent e) {
                if (e.getMessage().equals("/lithiumtest")) {
                    e.setCancelled(true);
                    if (LithiumPlugin.getInstance().getPlayerManager().isUsingLithium(e.getPlayer())) {
                        LWindow w = new LWindow();

                        LPanel textPanel = new TextPanel();


                        LImage img = new LImage("https://minotar.net/helm/" + e.getPlayer().getName() + "/100.png");
                        w.addControl(img, 0, 0, 50, 50);


                        w.addControl(textPanel);
                        LithiumPlugin.getInstance().getPlayerManager().getPlayer(e.getPlayer()).openInterface(w);
                    }
                } else if (e.getMessage().equals("/overlaytest")) {
                    e.setCancelled(true);
                    if (LithiumPlugin.getInstance().getPlayerManager().isUsingLithium(e.getPlayer())) {
                        if (overlayPlayers.containsKey(e.getPlayer().getUniqueId())) {
                            e.getPlayer().sendMessage(ChatColor.RED + "You already have the overlay!");
                            return;
                        }
                        OverlayPlayer pl = new OverlayPlayer(e.getPlayer());
                        overlayPlayers.put(e.getPlayer().getUniqueId(), pl);
                    } else {
                        e.getPlayer().sendMessage(ChatColor.RED + "You must be using Lithium in order to see the overlay!");
                    }
                }
            }

        }, this);
    }
}
