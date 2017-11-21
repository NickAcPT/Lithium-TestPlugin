package net.nickac.testing.lithium.exampleElements;

import net.nickac.lithium.backend.controls.impl.LPanel;
import net.nickac.lithium.backend.controls.impl.LTextBox;
import net.nickac.lithium.backend.controls.impl.LTextLabel;

/**
 * Created by ysl3000
 */
public class InputPanel{

    private LPanel inputPanel;
    private LTextBox textBox;

    public InputPanel(String label){
        this.inputPanel= new LPanel();
        this.textBox= new LTextBox();
        inputPanel.addControl(new LTextLabel(label+": "), 5, 5, 10, 20);
        inputPanel.addControl(this.textBox, 45, 0, 155, 20);
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
