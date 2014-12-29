package com.hardestfield.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hardestfield.game.model.Bat;
import com.hardestfield.game.model.Squirrel;

/**
 * Created by mihai on 12/20/2014.
 * <p/>
 * THis class controls all the details of the game
 */
public class AreaController {

    public static final float AREA_WIDTH = 10;
    public static final float AREA_HEIGHT = 300;
    public static final Vector2 gravity = new Vector2(0, -12);
    public static final int STATE_RUNNING = 0;
    public static final int STATE_NEXT_LEVEL = 1;
    public static final int STATE_GAME_OVER = 2;
    public final Squirrel squirrel;
    public final Bat bat;
    int state = STATE_RUNNING;
    int score = 0;
    float heightSoFar;

    public AreaController() {
        this.squirrel = new Squirrel(5, 1);
        this.bat = new Bat(5,5);
        this.heightSoFar = 0;
        this.state = STATE_RUNNING;
    }

    public void update(float deltaTime, float accelX) {

        updateSquirrel(deltaTime, accelX);
        updateBat(deltaTime);
        if(squirrel.getState()!= Squirrel.STATE_HIT)
            score++;
    }

    private void updateSquirrel(float deltaTime, float accelX) {
        if (squirrel.getState() != Squirrel.STATE_HIT && squirrel.position.y <= 0.5f) {
            squirrel.speed.y = Squirrel.JUMP_VELOCITY;
            state = Squirrel.STATE_JUMP;
            squirrel.setStateTime(0);
        }
        if (squirrel.getState() != Squirrel.STATE_HIT)
            squirrel.speed.x = -accelX / 10 * Squirrel.MOVE_VELOCITY;
        squirrel.update(deltaTime);

    }
    private void updateBat(float deltaTime)
    {
        bat.update(deltaTime);
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
