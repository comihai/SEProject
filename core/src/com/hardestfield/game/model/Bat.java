package com.hardestfield.game.model;


import com.hardestfield.game.controller.AreaController;

/**
 * Created by mihai on 12/27/2014.
 * This class defines the enemies
 */
public class Bat extends DynamicGameObject {
    public static final float BAT_WIDTH = 1;
    public static final float BAT_HEIGHT = 0.7f;
    public static final float BAT_SPEED = 3f;

    float stateTime = 0;

    /**
     * Generic constructor
     *
     * @param x position on the x axis
     * @param y position on the y axis
     */
    public Bat(float x, float y) {
        super(x, y, BAT_WIDTH, BAT_HEIGHT);
        speed.set(BAT_SPEED, 0);
    }

    /**
     * This class updates the position and the speed of the bats
     *
     * @param deltaTime  The time interval for updating
     * @param rangeSpeed An additional speed
     */
    public void update(float deltaTime, float rangeSpeed) {
        position.add(speed.x * deltaTime, speed.y * deltaTime);
        bounds.x = position.x - BAT_WIDTH / 2;
        bounds.y = position.y - BAT_HEIGHT / 2;

        if (position.x < BAT_WIDTH / 2) {
            position.x = BAT_WIDTH / 2;
            speed.x = BAT_SPEED + rangeSpeed;
        }
        if (position.x > AreaController.AREA_WIDTH - BAT_WIDTH / 2) {
            position.x = AreaController.AREA_WIDTH - BAT_WIDTH / 2;
            speed.x = -(BAT_SPEED + rangeSpeed);
        }
        stateTime += deltaTime;
    }

    /**
     * Getters and Setters
     *
     * @return
     */
    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }
}
