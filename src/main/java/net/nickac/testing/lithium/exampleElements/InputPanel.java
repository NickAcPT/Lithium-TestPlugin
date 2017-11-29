package net.nickac.testing.lithium.exampleElements;

import net.nickac.lithium.backend.controls.CenterOptions;
import net.nickac.lithium.backend.controls.impl.LPanel;
import net.nickac.lithium.backend.controls.impl.LTextBox;
import net.nickac.lithium.backend.controls.impl.LTextLabel;

/**
 * Created by ysl3000
 */
public class InputPanel{

    private LPanel inputPanel;
    private LTextBox textBox;
    private LTextLabel label;

    InputPanel(String label){
        this.inputPanel= new LPanel();
        this.textBox= new LTextBox();
        this.textBox.setCentered(CenterOptions.VERTICAL);
        this.label= new LTextLabel(label+":");
        this.label.setCentered(CenterOptions.VERTICAL);
        inputPanel.addControl(this.label, 0, 0, 20, 20);
        inputPanel.addControl(this.textBox, 25, 0, 155, 20);
    }

    public String getText(){
        return textBox.getText();
    }


    public boolean isPasswordPanel(){
        return textBox.isPasswordField();
    }

    public void setPasswordPanel(boolean isPasswordPanel){
        this.textBox.setPasswordField(isPasswordPanel);
    }

    public LPanel getInputPanel() {
        return inputPanel;
    }
}
