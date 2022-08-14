package sprites;

import Effects.Images;
import biuoop.DrawSurface;

/**
 * create the background for level 1 (target).
 */
public class Background implements Sprite  {
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(10, 30, Images.getImage(d.getWidth() - 20, d.getHeight() - 30,
                "background"));
    }
    @Override
    public void timePassed() {
    }
}
