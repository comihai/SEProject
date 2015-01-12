package com.hardestfield.game.model;

import com.hardestfield.game.controller.AreaController;

/**
 * Created by mihai on 12/29/2014.
 * This class represents the branches that squirrel jumps
 */
public class Branch extends DynamicGameObject {

    public static final float BRANCH_WIDTH = 2;
    public static final float BRANCH_HEIGHT = 0.5f;
    public static final int BRANCH_TYPE_STATIC = 0;
    public static final int BRANCH_TYPE_MOVING = 1;
    public static final float BRANCH_VELOCITY = 2;

    int type;
    float stateTime;

    /**
     * Generic constructor
     *
     * @param type type of the branch - static / moving
     * @param x    position on the x axis
     * @param y    position on the y axis
     */
    public Branch(int type, float x, float y) {
        super(x, y, BRANCH_WIDTH, BRANCH_HEIGHT);
        this.type = type;
        this.stateTime = 0;
        if (type == BRANCH_TYPE_MOVING) {
            speed.x = BRANCH_VELOCITY;
        }
    }

    /**
     * This function updates the position of the branch
     *
     * @param deltaTime The time interval for updating
     */
    public void update(float deltaTime, float boost) {
        if (type == BRANCH_TYPE_MOVING) {
            position.add(speed.x * deltaTime, 0);
            bounds.x = position.x - BRANCH_WIDTH / 2;
            bounds.y = position.y - BRANCH_HEIGHT / 2;

            if (position.x < BRANCH_WIDTH / 2) {
                speed.x = -(speed.x + boost);
                position.x = BRANCH_WIDTH / 2;
            }
            if (position.x > AreaController.AREA_WIDTH - BRANCH_WIDTH / 2) {
                speed.x = -(speed.x + boost);
                position.x = AreaController.AREA_WIDTH - BRANCH_WIDTH / 2;
            }
        }

        stateTime += deltaTime;
    }
}
