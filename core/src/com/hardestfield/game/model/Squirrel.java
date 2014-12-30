package com.hardestfield.game.model;

import com.hardestfield.game.controller.AreaController;

/**
 * Created by mihai on 12/16/2014.
 * This class defines the actor of the game : The Squirrel
 */
public class Squirrel extends DynamicGameObject {
    int state;
    float stateTime;
    public static final int STATE_JUMP = 0;
    public static final int STATE_FALL = 1;
    public static final int STATE_HIT = 2;
    public static final float SQUIRREL_WIDTH = 0.8f;
    public static final float SQUIRREL_HEIGHT = 0.8f;
    public static final float JUMP_VELOCITY = 11;
    public static final float MOVE_VELOCITY = 20;

    public Squirrel(float x, float y) {
        super(x, y, SQUIRREL_WIDTH, SQUIRREL_HEIGHT);
        state = STATE_JUMP;
        stateTime = 0;
    }

    public void update(float deltaTime) {
        speed.add(AreaController.gravity.x * deltaTime, AreaController.gravity.y * deltaTime);
        position.add(speed.x * deltaTime, speed.y * deltaTime);
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;

        if (speed.y > 0 && state != STATE_HIT) {
            if (state != STATE_JUMP) {
                state = STATE_JUMP;
                stateTime = 0;
            }
        }

        if (speed.y < 0 && state != STATE_HIT) {
            if (state != STATE_FALL) {
                state = STATE_FALL;
                stateTime = 0;
            }
        }

        if (position.x < 0) position.x = AreaController.AREA_WIDTH;
        if (position.x > AreaController.AREA_WIDTH) position.x = 0;

        stateTime += deltaTime;
    }
    public void hitBranch()
    {
        speed.y = JUMP_VELOCITY;
        state = STATE_JUMP;
        stateTime = 0;
    }
    public void hitBeehive()
    {
        speed.y = JUMP_VELOCITY * 1.5f;
        state = STATE_JUMP;
        stateTime = 0;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }
}
