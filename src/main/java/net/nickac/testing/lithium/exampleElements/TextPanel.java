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
public class TextPanel {

    private LPanel rootPane;
    private InputPanel inputPanel;
    private InputPanel name;

    public TextPanel() {
        this.rootPane = new LPanel();

        LTextLabel label = new LTextLabel("Registration");

        rootPane.addControl(label, 0, 10, 100, 20);

        label.setCentered(LControl.CenterOptions.HORIZONTAL);

        this.name = new InputPanel("Name");
        this.name.getInputPanel().setCentered(LControl.CenterOptions.HORIZONTAL);
        rootPane.addControl(name.getInputPanel(), 0, label.getBottom() + 5, 80, 20);

        this.inputPanel = new InputPanel("Password");
        this.inputPanel.getInputPanel().setCentered(LControl.CenterOptions.HORIZONTAL);
        this.inputPanel.setPasswordPanel(true);
        rootPane.addControl(inputPanel.getInputPanel(),0,name.getInputPanel().getBottom()+5,80,20);

        LSlider lSlider = new LSlider(60);
        rootPane.addControl(lSlider.setCentered(LControl.CenterOptions.HORIZONTAL), 5, inputPanel.getInputPanel().getBottom() + 5, 100, 20);

        OkCancelPanel okCancelPanel = new OkCancelPanel(new AbstractButtonAnswer("Register") {
            @Override
            public void handleEvent(LButton lButton, UUID uuid) {
                Bukkit.getPlayer(uuid).sendMessage("§4§lText from textbox: §6" + TextPanel.this.inputPanel.getText());
                Bukkit.getPlayer(uuid).sendMessage("§4§lSlider Value ? §6" + lSlider.getValue());
            }
        }, new CloseAnswer());

        rootPane.addControl(okCancelPanel.getOkCancelPanel(), 0, lSlider.getBottom()+5, 155, 40);

    }

    public LPanel getRootPane() {
        return rootPane;
    }
}
