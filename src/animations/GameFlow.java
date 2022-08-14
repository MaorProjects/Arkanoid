package animations;

import Effects.Sound;
import levels.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import other.Counter;
import java.util.List;

/**
 * charge to run all levels in the game in sequence.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private Counter lives;
    private GUI gui;
    private boolean win;

    /**
     * constructor.
     * @param ar the animation runner.
     * @param ks the keyboard
     * @param gui tool for present the animation.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
       animationRunner = ar;
       keyboardSensor = ks;
       this.gui = gui;
       score = new Counter(0);
       lives = new Counter(3);
       win = true;
    }

    /**
     * run all levels in the game in sequence.
     * @param levels the information of all levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        GameLevel gameLevel = new GameLevel(animationRunner, keyboardSensor);
        gameLevel.howManyPlayers();
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, score, lives);
            level.initialize();
            Sound.playSound("NewLevel");
            level.run();
            if (level.getCounterBalls() == 0) {
                Sound.playSound("GameOver");
                win = false;
                break;
            }
        }
        if (win) {
            Sound.playSound("Win");
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY, new EndScreen(win, score, lives, gui)));
    }
}
