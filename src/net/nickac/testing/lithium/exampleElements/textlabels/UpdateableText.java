package net.nickac.testing.lithium.exampleElements.textlabels;

import net.nickac.lithium.backend.controls.impl.LTextLabel;

/**
 * Created by ysl3000
 */
public class UpdateableText {

    private TextUpdater textUpdater;
    private LTextLabel lTextLabel;

    private UpdateableText(TextUpdater textUpdater) {
        this.textUpdater = textUpdater;
        this.lTextLabel = new LTextLabel(textUpdater.getText());
    }

    public static UpdateableText wrap(TextUpdater textUpdater) {
        return new UpdateableText(textUpdater);
    }

    public void updateText() {
        lTextLabel.setText(textUpdater.getText());
    }

    public LTextLabel getlTextLabel() {
        return lTextLabel;
    }

    @FunctionalInterface
    public static interface TextUpdater {
        String getText();
    }

}
