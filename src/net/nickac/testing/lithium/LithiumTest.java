package net.nickac.testing.lithium;

import net.nickac.lithium.backend.controls.LControl;
import net.nickac.lithium.backend.controls.impl.*;
import net.nickac.lithium.backend.other.objects.Point;
import net.nickac.lithium.frontend.LithiumPlugin;
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
				if (overlayPlayers.containsKey(e.getPlayer().getUniqueId())) {
					overlayPlayers.get(e.getPlayer().getUniqueId()).updateOverlay();
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

						LPanel screen = new LPanel();
						LPanel screen2 = new LPanel();

						LTextBox txtName = new LTextBox();
						LTextLabel label = new LTextLabel("Registration");

						LCheckBox checkBox = new LCheckBox("Test");



						screen.addControl(label, 0, 10, 100, 20);

						screen2.addControl(new LTextLabel("Name: "), 5, 5, 200, 20);
						screen2.addControl(txtName, 45, 0, 155, 20);


						screen2.setLocation(new Point(0, 30));
						screen2.setCentered(LControl.CenterOptions.HORIZONTAL);

						checkBox.setCentered(LControl.CenterOptions.HORIZONTAL);

						screen.setCentered(LControl.CenterOptions.HORIZONTAL_VERTICAL);
						label.setCentered(LControl.CenterOptions.HORIZONTAL);
						screen.addControl(screen2);
						txtName.setPasswordField(true);

						LButton btnSave = new LButton("Save");
						screen.addControl(btnSave, 0, 100, 50, 20);
						btnSave.setCentered(LControl.CenterOptions.HORIZONTAL);
						final boolean[] added = {false};
						btnSave.onButtonClick((sender, invoker) -> {
							//w.close();
							if (!added[0]) {
								screen2.addControl(checkBox, 5, 40, 100, 20);
								btnSave.dispose();
								added[0] = true;
							}
							Bukkit.getPlayer(invoker).sendMessage("§4§lText from textbox: §6" + txtName.getText());
							Bukkit.getPlayer(invoker).sendMessage("§4§lCheckBox checked ? §6" + checkBox.isChecked());
						});

						screen.addControl(new LSlider(60).setCentered(LControl.CenterOptions.HORIZONTAL), 5, 120, 100, 20);

						w.addControl(screen);
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
