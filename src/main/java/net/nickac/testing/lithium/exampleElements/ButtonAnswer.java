package net.nickac.testing.lithium.exampleElements;


import net.nickac.lithium.backend.controls.impl.LButton;
import net.nickac.lithium.backend.controls.impl.events.PropertyChangedHandler;

/**
 * Created by ysl3000
 */
public interface ButtonAnswer extends PropertyChangedHandler<LButton> {
    String getButtonLabel();
}
