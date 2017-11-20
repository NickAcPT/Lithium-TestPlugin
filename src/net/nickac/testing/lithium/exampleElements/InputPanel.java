package net.nickac.testing.lithium.exampleElements;

import net.nickac.lithium.backend.controls.impl.LPanel;
import net.nickac.lithium.backend.controls.impl.LTextBox;
import net.nickac.lithium.backend.controls.impl.LTextLabel;

/**
 * Created by ysl3000
 */
public class InputPanel extends LPanel{

    private LTextBox textBox;

    public InputPanel(){
        super();
        this.textBox= new LTextBox();
        this.addControl(new LTextLabel("Name: "), 5, 5, 10, 20);
        this.addControl(this.textBox, 45, 0, 155, 20);
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

}
