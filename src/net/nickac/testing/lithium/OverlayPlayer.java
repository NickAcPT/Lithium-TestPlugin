/*
 * MIT License
 *
 * Copyright (c) 2017 NickAc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.nickac.testing.lithium;

import net.nickac.lithium.backend.controls.impl.LOverlay;
import net.nickac.lithium.backend.controls.impl.LTextLabel;
import net.nickac.lithium.backend.other.objects.Point;
import net.nickac.lithium.frontend.LithiumPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Criado por NickAc em 09:11 AM 18/10/2017
 */
public class OverlayPlayer {
	private Player handle;
	private LOverlay o = new LOverlay();
	private final int numberLines = 4;
	private LTextLabel[] labels = new LTextLabel[numberLines];

	public OverlayPlayer(Player handle) {
		this.handle = handle;
		if (LithiumPlugin.getInstance().getPlayerManager().getPlayer(handle).isUsingLithium())
			startOverlaying();
	}

	public Player getHandle() {
		return handle;
	}

	public LTextLabel[] getLabels() {
		return labels;
	}

	private String getOverlayLine(int number) {
		switch (number) {
			case 1:
				return ChatColor.GRAY + "[" + ChatColor.GOLD + "X" + ChatColor.GRAY + "] " + handle.getLocation().getBlockX();
			case 2:
				return ChatColor.GRAY + "[" + ChatColor.GOLD + "Y" + ChatColor.GRAY + "] " + handle.getLocation().getBlockY();
			case 3:
				return ChatColor.GRAY + "[" + ChatColor.GOLD + "Z" + ChatColor.GRAY + "] " + handle.getLocation().getBlockZ();
		}
		return "";
	}

	private void startOverlaying() {
		labels[0] = new LTextLabel(ChatColor.GOLD + "Server Overlay Mod");
		labels[1] = (LTextLabel) new LTextLabel(getOverlayLine(1)).setLocation(new Point(0, 20));
		labels[2] = (LTextLabel) new LTextLabel(getOverlayLine(2)).setLocation(new Point(0, 30));
		labels[3] = (LTextLabel) new LTextLabel(getOverlayLine(3)).setLocation(new Point(0, 40));
		for (int i = 0; i < labels.length; i++) {
			o.addControl(labels[i]);
		}
		LithiumPlugin.getInstance().getPlayerManager().getPlayer(handle).openOverlay(o);
	}

	public void updateOverlay() {
		labels[0].setText(ChatColor.GOLD + "Server Overlay Mod");
		labels[1].setText(getOverlayLine(1));
		labels[2].setText(getOverlayLine(2));
		labels[3].setText(getOverlayLine(3));
	}
}
