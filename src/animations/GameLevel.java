// ID:211390877
package animations;

import Effects.Images;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import bonus.BallsBonus;
import bonus.BiggerPaddleBonus;
import bonus.Bonus;
import bonus.LiveBonus;
import collections.GameEnvironment;
import collections.SpriteCollection;
import geomtry.Point;
import geomtry.Rectangle;
import levels.LevelInformation;
import listeners.*;
import collision.Collidable;
import other.Counter;
import sprites.*;
import Effects.Sound;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the class is charge to initializing the game by paddle, blocks and balls,
 * And then run it.
 */
public class GameLevel implements Animation {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int RADIUS = 6;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counterBlocks;
    private Counter counterBalls;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    public static boolean onePlayer = false;
    public static boolean twoPlayers = false;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    private Paddle paddle;
    private Paddle paddle1;
    private Paddle paddle2;

    /**
     * constructor.
     * @param levelInfo the information of the level.
     * @param keyboard the keyboard.
     * @param ar the animation runner.
     * @param score the score the player got until this level.
     */
    public GameLevel(LevelInformation levelInfo, biuoop.KeyboardSensor keyboard, AnimationRunner ar,
                     Counter score, Counter lives) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        counterBlocks = new Counter(levelInfo.numberOfBlocksToRemove());
        counterBalls = new Counter(0);
        running = true;
        runner = ar;
        this.score = score;
        this.keyboard = keyboard;
        this.levelInfo = levelInfo;
        this.lives = lives;
    }

    public GameLevel(AnimationRunner runner, KeyboardSensor keyboard) {
        this.runner = runner;
        this.keyboard = keyboard;
    }

    /**
     * add collidable object to the list.
     *
     * @param c is collidasble object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add sprite object to the list.
     *
     * @param s is sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove collidable from the game.
     *
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove sprite from the game.
     *
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * @return the score.
     */
    public Counter getScoreIndicator() {
        return score;
    }

    /**
     * @return thu number of the balls in the game.
     */
    public int getCounterBalls() {
        return counterBalls.getValue();
    }
    public Counter getLives() {
        return lives;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(sprites)));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        if (counterBlocks.getValue() == 0 || counterBalls.getValue() == 0) {
            if (counterBlocks.getValue() > 0 && lives.getValue() > 0) {
                Sound.playSound("LoseLife");
                semiInitialize();
                run();
            }
            return running;
        }
        return !running;
    }

    public void semiInitialize() {
        //create balls.
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(null, 0, null);
            if (onePlayer) {
                ball = new Ball(new Point(WIDTH / 2, 570), RADIUS, Color.WHITE);
            }
            if (twoPlayers) {
                ball = new Ball(new Point(WIDTH / 4, 570), RADIUS, Color.WHITE);
            }
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
            ball.setEnv(environment);
            counterBalls.increase(1);
        }
        //set paddle in the middle.
        if (onePlayer) {
            paddle.setRect(new Rectangle(new Point((double) (WIDTH - 20 - levelInfo.paddleWidth()) / 2 + 10, 575),
                    levelInfo.paddleWidth(), 20));
        }
        if (twoPlayers) {
            paddle1.setRect(new Rectangle(new Point((double) WIDTH / 4  - levelInfo.paddleWidth() / 2, 575),
                    levelInfo.paddleWidth(), 20));
            paddle2.setRect(new Rectangle(new Point((double) 3 * WIDTH / 4  - levelInfo.paddleWidth() / 2, 575),
                    levelInfo.paddleWidth(), 20));


        }
        //remove bonuses
        List<Sprite> allSprites = new ArrayList<>(sprites.getSprites());
        for (Sprite s: allSprites) {
            if (s.getClass() == LiveBonus.class || s.getClass() == BallsBonus.class
                    || s.getClass() == BiggerPaddleBonus.class) {
                ((Bonus) s).removeFromGame(this);
            }
        }
    }
    /**
     * Initialize a new game: create the Blocks Balls and sprites.Paddle
     * and add them to the game.
     */
    public void initialize() {
        //images
          new Images();
        //level name
        LevelName levelName = new LevelName(levelInfo.levelName());
        levelName.addToGame(this);
        //lives
        LivesIndicator livesIndicator = new LivesIndicator(lives);
        livesIndicator.addToGame(this);
        //background
        addSprite(levelInfo.getBackground());
        //create score indicator.
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
        //create balls.
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(null, 0, null);
            if (onePlayer) {
                ball = new Ball(new Point(WIDTH / 2, 570), RADIUS, Color.WHITE);
            }
            if (twoPlayers) {
                ball = new Ball(new Point(WIDTH / 4, 570), RADIUS, Color.WHITE);
            }
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));
            ball.addToGame(this);
            ball.setEnv(environment);
            counterBalls.increase(1);
        }
        //create paddle.
        if (onePlayer) {
            paddle = new Paddle(new Rectangle(new Point((double) (WIDTH - 20 - levelInfo.paddleWidth()) / 2 + 10, 575),
                    levelInfo.paddleWidth(), 20), keyboard, levelInfo.paddleSpeed());
            paddle.addToGame(this);
        }
        if (twoPlayers){
            paddle1 = new Paddle(new Rectangle(new Point((double) WIDTH / 4 - levelInfo.paddleWidth() / 2 , 575),
                    levelInfo.paddleWidth(), 20), keyboard, levelInfo.paddleSpeed());
            paddle2 = new Paddle(new Rectangle(new Point((double) 3 * WIDTH / 4  - levelInfo.paddleWidth() / 2, 575),
                    levelInfo.paddleWidth(), 20), keyboard, levelInfo.paddleSpeed());
            paddle1.setRightLimit(296);
            paddle2.setLeftLimit(304);
            paddle1.setLeftControl("a");
            paddle1.setRightControl("d");
            paddle1.addToGame(this);
            paddle2.addToGame(this);

        }

        //blocks frame.
        List<Block> frameBlockList = new ArrayList<>();
        frameBlockList.add(new Block(new Rectangle(new Point(0, 20), 10, HEIGHT), Color.gray));
        frameBlockList.add(new Block(new Rectangle(new Point(0, 20), WIDTH, 10), Color.gray));
        frameBlockList.add(new Block(new Rectangle(new Point(590, 20), 10, HEIGHT), Color.gray));
        for (Block block : frameBlockList) {
            block.addToGame(this);
        }
        //blocks
        List<Block> blockList = levelInfo.blocks();
        //unremovable blocks
        List<Block> unremovableBlockList = levelInfo.unremovableBlocks();
        //death region.
        Block deathRegion = new Block(new Rectangle(new Point(0, 599), WIDTH, 1), Color.gray);
        deathRegion.addToGame(this);
        //create listeners.
        BlockRemover blockRemover = new BlockRemover(this, counterBlocks);
        BallRemover ballRemover = new BallRemover(this, counterBalls);
        LiveListener liveListener = new LiveListener(lives, counterBalls);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
        BonusAdder ballsBonusAdder = new BonusAdder(this, environment, "Balls");
        ballsBonusAdder.setCounterBalls(counterBalls);
        ballsBonusAdder.setSpriteCollection(sprites);
        BonusAdder biggerPaddleBonusAdder = new BonusAdder(this, environment, "BiggerPaddle");
        BonusAdder liveBonusAdder = new BonusAdder(this, environment, "Live");
        liveBonusAdder.setLives(lives);
        BonusRemover bonusRemover = new BonusRemover(this);
        SoundAdder soundAdder = new SoundAdder();
        //add listeners to the game.
        if (onePlayer) {
            paddle.addHitListener(bonusRemover);
        }
        if (twoPlayers){
            paddle1.addHitListener(bonusRemover);
            paddle2.addHitListener(bonusRemover);
        }
        deathRegion.addHitListener(ballRemover);
        deathRegion.addHitListener(liveListener);
        deathRegion.addHitListener(bonusRemover);
        for (Block block : blockList) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        for (Block block : unremovableBlockList) {
            block.addToGame(this);
            block.addHitListener(soundAdder);
        }
        //add bonuses listeners random.
        int numOfLiveBonus = levelInfo.numberOfLiveBonus();
        int numOfBiggerPaddleBonus = levelInfo.numberOfBiggerPaddleBonus();
        int numOfBallsBonus = levelInfo.numberOfBallsBonus();
        for (Block block : blockList) {
            Random rand = new Random();
            int num = rand.nextInt() % 4;
            if (num == 0) {
                num = Math.abs(rand.nextInt() % 3);
                if (num == 0 && numOfLiveBonus != 0) { //
                    // System.out.println("live bonus" + count);
                   block.addHitListener(liveBonusAdder);
                   numOfLiveBonus--;
                }
                if (num == 1 && numOfBallsBonus != 0) {
                    //System.out.println("balls bonus" + count);
                    block.addHitListener(ballsBonusAdder);
                    numOfBallsBonus--;
                }
                if (num == 2 && numOfBiggerPaddleBonus != 0) {
                    //System.out.println("paddle bonus" + count);
                    block.addHitListener(biggerPaddleBonusAdder);
                    numOfBiggerPaddleBonus--;
                }
            }
        }
    }

    /**
     * Run the game - start the animation loop.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }
    public void howManyPlayers() {
        runner.run(new HowManyPlayers(keyboard));
    }
}