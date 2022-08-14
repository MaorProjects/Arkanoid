package levels;

import animations.GameLevel;
import sprites.Background;
import geomtry.Point;
import geomtry.Rectangle;
import other.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the information of level 3.
 */
public class Level3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        ArrayList<Velocity> velocities = new ArrayList<>();
        int angle = Math.abs(new Random().nextInt() % 90) - 45;
            velocities.add(Velocity.fromAngleAndSpeed(angle, 6));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        if (GameLevel.onePlayer) {
            return 100;
        }
        if (GameLevel.twoPlayers) {
            return 80;
        }
        return 0;
    }

    @Override
    public String levelName() {
        return "Pyramid";
    }

    @Override
    public Sprite getBackground() {
        return new Background();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        for (int j = 0; j < 12; j++) {
            String[] rowColor = {"lightBlue", "blue", "green", "yellow", "red", "orange", "pink"};
            for (int i = 0; i < j + 1; i++) {
                blocks.add(new Block(new Rectangle(new Point(
                        10 + 44.6 * i, 50 + 25 * j), 44.6, 25), rowColor[j % 7]));
            }
        }
        for (int j = 3; j > 0; j--) {
            for (int i = 0; i < 13; i++) {
                blocks.add(new Block(new Rectangle(new Point(
                        10 + 44.6 * i, 350), 44.6, 25), "gray"));
            }
        }
        return  blocks;
    }

    @Override
    public List<Block> unremovableBlocks() {
        return new ArrayList<>();
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public int numberOfBallsBonus() {
        return 2;
    }

    @Override
    public int numberOfLiveBonus() {
        return 1;
    }

    @Override
    public int numberOfBiggerPaddleBonus() {
        return 1;
    }
}
