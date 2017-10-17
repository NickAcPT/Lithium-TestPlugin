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
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by NickAc!
 */
public class LithiumTest extends JavaPlugin {

	Map<UUID, LTextLabel> lbls = new HashMap<>();

	@Override
	public void onEnable() {

		Bukkit.getPluginManager().registerEvents(new Listener() {

			@EventHandler
			public void on(PlayerMoveEvent e) {
				if (e.getFrom().getX() != e.getTo().getX() || e.getFrom().getZ() != e.getTo().getZ()) {
					for (Map.Entry<UUID, LTextLabel> u : lbls.entrySet()) {
						u.getValue().setText(ChatColor.GOLD + "X:" + Bukkit.getPlayer(u.getKey()).getLocation().getBlockX());
					}
				}
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

						screen.addControl(label, 0, 10, 100, 20);

						screen2.addControl(new LTextLabel("Name: "), 5, 5, 200, 20);
						screen2.addControl(txtName, 45, 0, 155, 20);

						LCheckBox checkBox = new LCheckBox("Test");
						//screen2.addControl(checkBox, 5, 40, 100, 20);

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

						w.addControl(screen);
						LithiumPlugin.getInstance().getPlayerManager().getPlayer(e.getPlayer()).openInterface(w);
					}
				} else if (e.getMessage().equals("/overlaytest")) {
					e.setCancelled(true);
					if (LithiumPlugin.getInstance().getPlayerManager().isUsingLithium(e.getPlayer())) {
						LOverlay o = new LOverlay();

						o.addControl(new LTextLabel(ChatColor.GOLD + "NickAc OverlayMod"));
						LTextLabel c = new LTextLabel(ChatColor.GOLD + "X: ");
						lbls.put(e.getPlayer().getUniqueId(), c);
						o.addControl(c, 0, 20, 0, 0);
						LithiumPlugin.getInstance().getPlayerManager().getPlayer(e.getPlayer()).openOverlay(o);
					}
				}
			}

		}, this);
	}
}
