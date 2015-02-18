package com.hardestfield.game.controller;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.view.HelpScreen;

/**
 * Created by mihai on 1/10/2015.
 * This class controls which images will be displayed on the runtime option
 */
abstract class HelpScreenController extends ScreenAdapter {

    Rectangle nextBounds;
    Vector3 touchPoint;
    HelpScreen helpScreen;

    /**
     * This constructor is used for derived classes
     *
     * @param game              This variable creates and loads all the resources of the game
     * @param screenDescription This is the description of the image
     */
    public HelpScreenController(HardestField game, String[] screenDescription) {
        helpScreen = new HelpScreen(game, screenDescription);
        nextBounds = new Rectangle(415, 0, 60, 60);
        touchPoint = new Vector3();
    }

    public abstract void update();
}
