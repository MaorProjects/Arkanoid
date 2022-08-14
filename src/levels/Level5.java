package levels;

import animations.GameLevel;
import geomtry.Point;
import geomtry.Rectangle;
import other.Velocity;
import sprites.Background;
import sprites.Block;
import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level5 implements LevelInformation{
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
        return "cant break";
    }

    @Override
    public Sprite getBackground() {
        return new Background();
    }

    @Override
    public List<Block> blocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        String col[] = {"red", "blue", "pink", "green"};
        for (int i = 0; i < 9; i++) {
            blocks.add(new Block(new Rectangle(new Point(99.2 + 44.6 * i, 100), 44.6, 22), "red"));
        }
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 3; i++) {
             blocks.add(new Block(new Rectangle(new Point(233 + 44.6 * i, 188 + 22 * j),
                     44.6, 22), "yellow"));
            }
        }
        return blocks;
    }
    @Override
    public List<Block> unremovableBlocks() {
        ArrayList<Block> unremovableBlocks = new ArrayList<>();
        Color color = new Color(215, 185, 0);
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 14; i++) {
                unremovableBlocks.add(new Block(new Rectangle(new Point(54.6 + 446 * j,
                        100 + 22 * i), 44.6, 22), "gold"));
            }
        }
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 9; i++) {
                unremovableBlocks.add(new Block(new Rectangle(new Point(143.8 + 267.6 * j,
                        144 + 22 * i), 44.6, 22), "gold"));
            }
        }
        for (int i = 0; i < 5; i++) {
            unremovableBlocks.add(new Block(new Rectangle(new Point(188.4 + 44.6 * i, 144),
                    44.6, 22), "gold"));
        }
        for (int i = 0; i < 9; i++) {
            unremovableBlocks.add(new Block(new Rectangle(new Point(99.2 + 44.6 * i, 386),
                    44.6, 22), "gold"));
        }
        return unremovableBlocks;
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
        return 0;
    }

    @Override
    public int numberOfBiggerPaddleBonus() {
        return 0;
    }
}
