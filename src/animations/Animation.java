package animations;

import biuoop.DrawSurface;


/**
 * charge on the different animations of the game.
 */
public interface Animation {
    /**
     * do one frame of the animation.
     * @param d the draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * in which conditions the animation should stop.
     * @return true if should stop and false if not.
     */
    boolean shouldStop();
}
