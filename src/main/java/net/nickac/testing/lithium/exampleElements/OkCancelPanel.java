package net.nickac.testing.lithium.exampleElements;

import net.nickac.lithium.backend.controls.CenterOptions;
import net.nickac.lithium.backend.controls.impl.LButton;
import net.nickac.lithium.backend.controls.impl.LPanel;

/**
 * Created by ysl3000
 */
public class OkCancelPanel {


    private LPanel okCancelPanel;
    private LButton okButton;
    private LButton cancelButton;


    OkCancelPanel(ButtonAnswer positiveAnswer, ButtonAnswer negativeAnswer) {
            this.okCancelPanel = new LPanel();

        this.okButton = new LButton(positiveAnswer.getButtonLabel());
        this.okButton.onButtonClick(positiveAnswer);
        this.cancelButton = new LButton(negativeAnswer.getButtonLabel());
        this.cancelButton.onButtonClick(negativeAnswer);

        okCancelPanel.addControl(this.okButton, 0, 100, 50, 20);
        okCancelPanel.addControl(this.cancelButton, 55, 100, 50, 20);

        okCancelPanel.setCentered(CenterOptions.HORIZONTAL);

    }

    public LPanel getOkCancelPanel() {
        return okCancelPanel;
    }
}
