package net.nickac.testing.lithium.exampleElements;

import net.nickac.lithium.backend.controls.impl.LButton;
import net.nickac.lithium.frontend.LithiumPlugin;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Created by ysl3000
 */
public class CloseAnswer extends AbstractButtonAnswer {
    public CloseAnswer() {
        super("Cancel");
    }

    @Override
    public void handleEvent(LButton lButton, UUID uuid) {
        LithiumPlugin.getInstance().getPlayerManager().getPlayer(Bukkit.getPlayer(uuid)).closeInterface();
    }
}
