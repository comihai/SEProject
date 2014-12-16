package com.hardestfield.game.model;

/**
 * Created by mihai on 12/16/2014.
 * This class defines the actor of the game : The Squirrel
 */
public class Squirrel extends DynamicGameObject {
    int state;
    int STATE_JUMP = 0;
    int STATE_FALL = 1;
    int STATE_HIT = 2;
    static float WIDTH = 0.8f;
    static float HEIGHT = 0.8f;

    public Squirrel (float x, float y) {
        super(x, y, WIDTH, HEIGHT);
        state = STATE_JUMP;

    }
}
