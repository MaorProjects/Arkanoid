package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * give the option of animation to stop by press on specific key.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor the keyboard sensor.
     * @param key the key that mean that the animation should stop.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        isAlreadyPressed = this.sensor.isPressed(key);
        animation.doOneFrame(d);
    }

    @Override
    public boolean shouldStop() {
        if (this.sensor.isPressed(key) && !isAlreadyPressed) {
            return animation.shouldStop();
        }
        return false;
    }
}
