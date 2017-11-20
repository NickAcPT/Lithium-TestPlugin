package net.nickac.testing.lithium.exampleElements;

import net.nickac.lithium.backend.controls.LControl;
import net.nickac.lithium.backend.controls.impl.LButton;
import net.nickac.lithium.backend.controls.impl.LPanel;
import net.nickac.lithium.backend.controls.impl.LSlider;
import net.nickac.lithium.backend.controls.impl.LTextLabel;
import org.bukkit.Bukkit;

import java.util.UUID;

/**
 * Created by ysl3000
 */
public class TextPanel extends LPanel{


    private InputPanel inputPanel;

    public TextPanel(){
        super();


        LTextLabel label = new LTextLabel("Registration");

        this.addControl(label, 0, 10, 100, 20);

        this.inputPanel = new InputPanel();

        label.setCentered(LControl.CenterOptions.HORIZONTAL);
        this.addControl(inputPanel);
        this.inputPanel.setPasswordPanel(true);

        LSlider lSlider = new LSlider(60);
        this.addControl(lSlider.setCentered(LControl.CenterOptions.HORIZONTAL), 5, 130, 100, 20);


        LPanel okCancelPanel = new OkCancelPanel(new AbstractButtonAnswer("Save") {
            @Override
            public void handleEvent(LButton lButton, UUID uuid) {
                Bukkit.getPlayer(uuid).sendMessage("§4§lText from textbox: §6" + TextPanel.this.inputPanel.getText());
                Bukkit.getPlayer(uuid).sendMessage("§4§lSlider Value ? §6" + lSlider.getValue());
            }
        }, new CloseAnswer());

        this.addControl(okCancelPanel, 0, 0, 155, 40);
        this.refresh();

    }
}
