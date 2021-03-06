package com.tropico.game;

/**
 * Class Constraint
 * <p>
 * Containt threshold of end of game and weight of action according to the level of the game.
 **/

public class Constraint {

    private Level level;
    private int threshold;
    private int lossWeight;
    private int gainWeight;

    public Constraint(Level level, int threshold) {
        this.level = level;
        this.threshold = threshold;
        this.lossWeight = matchLossWeightLevel();
        this.gainWeight = matchGainWeightLevel();

    }

    private int matchGainWeightLevel() {
        if (level.equals(Level.EASY)) {
            return 2;
        }
        return 1;
    }

    private int matchLossWeightLevel() {

        if (level.equals(Level.HARD)) {
            return 2;
        }
        return 1;
    }

    public int getThreshold() {
        return threshold;
    }

    public Level getLevel() {
        return level;
    }

    public int getGainWeight() {
        return gainWeight;
    }

    public int getLossWeight() {
        return lossWeight;
    }
}
