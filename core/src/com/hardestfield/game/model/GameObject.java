package com.hardestfield.game.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mihai on 12/16/2014.
 * <p/>
 * This class determines the position and the bounds on the screen
 */
public class GameObject {
    public Vector2 position;
    public Rectangle bounds;

    /**
     * Generic Constructor
     *
     * @param x      position on the x axis
     * @param y      position on the y axis
     * @param width  the width of the object
     * @param height the height of the object
     */
    public GameObject(float x, float y, float width, float height) {
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
    }
}
