package animations;
import biuoop.DrawSurface;
import collections.SpriteCollection;
import sprites.Sprite;

import java.awt.*;
import java.util.List;

/**
 * the screen showed when the player want to pause the game
 * and show properly massage.
 */
public class PauseScreen implements Animation {
    private SpriteCollection sprites;

    public PauseScreen(SpriteCollection sprites) {
        this.sprites = sprites;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        d.setColor(Color.white);
        d.fillRectangle(275, 275, 20, 50);
        d.fillRectangle(305, 275, 20, 50);
        d.setColor(Color.black);
        d.drawRectangle(275, 275, 20, 50);
        d.drawRectangle(305, 275, 20, 50);
    }
    @Override
    public boolean shouldStop() {
        return true;
    }
}
