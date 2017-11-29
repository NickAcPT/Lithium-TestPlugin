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

import net.nickac.lithium.backend.controls.LControl;
import net.nickac.lithium.backend.controls.impl.LImage;
import net.nickac.lithium.backend.controls.impl.LOverlay;
import net.nickac.lithium.backend.controls.impl.LTextLabel;
import net.nickac.lithium.backend.other.objects.Point;
import net.nickac.lithium.frontend.LithiumPlugin;
import net.nickac.testing.lithium.exampleElements.textlabels.UpdateableText;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Criado por NickAc em 09:11 AM 18/10/2017
 */
public class OverlayPlayer {
    private Player handle;
    private LOverlay o = new LOverlay();
    private List<UpdateableText> labels = new ArrayList<>();

    OverlayPlayer(Player handle) {
        this.handle = handle;
        if (LithiumPlugin.getInstance().getPlayerManager().isUsingLithium(handle))
            startOverlaying();
    }

    private void startOverlaying() {

        labels.add(UpdateableText.wrap(()-> ChatColor.GRAY + "[" + ChatColor.GOLD + "X" + ChatColor.GRAY + "] " + handle.getLocation().getBlockX()));
        labels.add(UpdateableText.wrap(()-> ChatColor.GRAY + "[" + ChatColor.GOLD + "Y" + ChatColor.GRAY + "] " + handle.getLocation().getBlockY()));
        labels.add(UpdateableText.wrap(()-> ChatColor.GRAY + "[" + ChatColor.GOLD + "Z" + ChatColor.GRAY + "] " + handle.getLocation().getBlockZ()));


        LTextLabel viewName = new LTextLabel(ChatColor.GOLD + "Server Overlay Mod");
        o.addControl(viewName,0,5,20,20);
        LImage img = new LImage("https://minotar.net/helm/" + handle.getName() + "/100.png");
        o.addControl(img, 5, 30, 20, 20);

        final int[] index = {0};
        labels.forEach(updatableText -> o.addControl((LControl) updatableText.getlTextLabel().setLocation(new Point(0, img.getBottom() + 5 + index[0]++ * 10))));

        LithiumPlugin.getInstance().getPlayerManager().getPlayer(handle).openContainer(o);
    }

    public Player getHandle() {
        return handle;
    }

    public void updateOverlay() {
        labels.forEach(UpdateableText::updateText);
    }
}
