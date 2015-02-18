package com.hardestfield.game.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by mihai on 12/16/2014.
 * This class defines the dynamic objects
 */
public class DynamicGameObject extends GameObject {
    public Vector2 speed;
    public Vector2 acceleration;

    /**
     * Generic constructor
     *
     * @param x      position on the x axis
     * @param y      position on the y axis
     * @param width  the width of the object
     * @param height the height of the object
     */
    public DynamicGameObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        speed = new Vector2();
        acceleration = new Vector2();
    }

}
