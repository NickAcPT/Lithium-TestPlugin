package net.nickac.testing.lithium.exampleElements;

/**
 * Created by ysl3000
 */
public abstract class AbstractButtonAnswer implements ButtonAnswer{

    private String label;

    protected AbstractButtonAnswer(String label) {
        this.label = label;
    }

    @Override
    public String getButtonLabel() {
        return label;
    }
}
