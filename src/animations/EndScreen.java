package animations;
import Effects.Images;
import Effects.Sound;
import biuoop.DrawSurface;
import biuoop.GUI;
import other.Counter;

import java.awt.*;

/**
 * the screen showed when the game over if you lose or win
 * and show properly massage.
 */
public class EndScreen implements Animation {
    private boolean win;
    private Counter score;
    private Counter lives;
    private GUI gui;

    /**
     * constructor.
     * @param win true if win false if lose.
     * @param score how many score the player got.
     * @param gui tool for present the animation.
     */
    public EndScreen(boolean win, Counter score, Counter lives, GUI gui) {
        this.win = win;
        this.score = score;
        this.lives = lives;
        this.gui = gui;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (win) {
            int newScore;
            d.drawImage(0, 0, Images.getImage(d.getWidth(), d.getHeight(), "YouWin"));
            newScore = score.getValue() + lives.getValue() * 500;
            d.setColor(Color.white);
            d.drawText(180, 450, "Your score is: " + newScore, 32);
        } else {
            d.drawImage(0, 0, Images.getImage(d.getWidth(), d.getHeight(), "GameOver"));
            d.setColor(Color.white);
            d.drawText(180, 450, "Your score is: " + score.getValue(), 32);
        }
    }
    @Override
    public boolean shouldStop() {
        gui.close();
        return true;
    }
}
