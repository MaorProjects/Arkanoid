// ID:211390877


import biuoop.GUI;
import animations.AnimationRunner;
import animations.GameFlow;
import levels.*;

import java.util.ArrayList;

/**
 * the class charge to run the game.
 */

public class Arkanoid {
    /**
     * @param args gets nothing.
     */
    public static void main(String[] args) {
        ArrayList<LevelInformation> levels = new ArrayList<>();
        GUI gui = new GUI("Arkanoid", 600, 600);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui, 60);
        GameFlow gameFlow = new GameFlow(ar, keyboard, gui);
        ArrayList<String> levelsNumbers = new ArrayList<>();
        //save the valid arguments.
        for (String arg : args) {
            if (arg.equals("1") || arg.equals("2") || arg.equals("3") || arg.equals("4") ||
                    arg.equals("5") || arg.equals("6")) {
                levelsNumbers.add(arg);
            }
        }
        if (levelsNumbers.size() != 0) {
            for (String levelNumber : levelsNumbers) {
                if (levelNumber.equals("1")) {
                    levels.add(new Level1());
                }
                if (levelNumber.equals("2")) {
                    levels.add(new Level2());
                }
                if (levelNumber.equals("3")) {
                    levels.add(new Level3());
                }
                if (levelNumber.equals("4")) {
                    levels.add(new Level4());
                }
                if (levelNumber.equals("5")) {
                    levels.add(new Level5());
                }
                if (levelNumber.equals("6")) {
                    levels.add(new Level6());
                }
            }
            //if all the arguments not valid play levels 1 - 6.
        } else {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
            levels.add(new Level5());
            levels.add(new Level6());

        }
        gameFlow.runLevels(levels);
    }
}
