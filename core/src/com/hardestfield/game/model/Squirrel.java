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

    /**
     * Generic constructor
     * @param x  position on the x axis
     * @param y  position on the y axis
     */
    public Squirrel(float x, float y) {
        super(x, y, SQUIRREL_WIDTH, SQUIRREL_HEIGHT);
        state = STATE_JUMP;
        stateTime = 0;
    }

    /**
     * This function updates the position of the squirrel
     * @param deltaTime The time interval for updating
     */
    public void update(float deltaTime) {
        speed.add(AreaController.gravity.x * deltaTime, AreaController.gravity.y * deltaTime);
        position.add(speed.x * deltaTime, speed.y * deltaTime);
        bounds.x = position.x - bounds.width / 2;
        bounds.y = position.y - bounds.height / 2;

        /**
         * If the vertical speed is positive
         */
        if (speed.y > 0 && state != STATE_HIT) {
            if (state != STATE_JUMP) {
                state = STATE_JUMP;
                stateTime = 0;
            }
        }
        /**
         * If the vertical speed is negative
         */
        if (speed.y < 0 && state != STATE_HIT) {
            if (state != STATE_FALL) {
                state = STATE_FALL;
                stateTime = 0;
            }
        }

        /**
         * The actor can move from side to side in horizontally axial area
         */
        if (position.x < 0) position.x = AreaController.AREA_WIDTH;
        if (position.x > AreaController.AREA_WIDTH) position.x = 0;

        stateTime += deltaTime;
    }

    /**
     * This function change the state of the actor when touches the branches - he will jumps
     */
    public void hitBranch() {
        speed.y = JUMP_VELOCITY;
        state = STATE_JUMP;
        stateTime = 0;
    }

    /**
     * This function increases the speed reaches a beehive positioned on a branch
     */
    public void hitBeehive() {
        speed.y = JUMP_VELOCITY * 1.5f;
        state = STATE_JUMP;
        stateTime = 0;
    }

    /**
     * This function warns when an enemy catches the squirrel and set the speed of the actor at zero
     */
    public void hitBat() {
        speed.set(0, 0);
        state = STATE_HIT;
        stateTime = 0;
    }

    /**
     * Getters and Setters
     * @return
     */
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
