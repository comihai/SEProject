package com.hardestfield.game.model;

/**
 * Created by mihai on 1/1/2015.
 *
 * This class is for finishing the game or moving to the next level
 */
public class Hollow extends GameObject {

    public static float HOLLOW_WIDTH = 1.7f;
    public static float HOLLOW_HEIGHT = 1.7f;

    public Hollow (float x, float y) {
        super(x, y, HOLLOW_WIDTH, HOLLOW_HEIGHT);
    }
}
