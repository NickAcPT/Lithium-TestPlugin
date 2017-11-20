package net.nickac.testing.lithium.exampleElements;

import net.nickac.lithium.backend.controls.LControl;
import net.nickac.lithium.backend.controls.impl.LButton;
import net.nickac.lithium.backend.controls.impl.LPanel;

/**
 * Created by ysl3000
 */
public class OkCancelPanel extends LPanel {


    private LButton okButton;
    private LButton cancelButton;


    public OkCancelPanel(ButtonAnswer positiveAnswer, ButtonAnswer negativeAnswer) {
        super();

        this.okButton = new LButton(positiveAnswer.getButtonLabel());
        this.okButton.onButtonClick(positiveAnswer);
        this.cancelButton = new LButton(negativeAnswer.getButtonLabel());
        this.cancelButton.onButtonClick(negativeAnswer);

        this.addControl(this.okButton, 0, 100, 50, 20);
        this.addControl(this.cancelButton, 55, 100, 50, 20);

        this.setCentered(LControl.CenterOptions.HORIZONTAL);

        this.refresh();


    }


}
