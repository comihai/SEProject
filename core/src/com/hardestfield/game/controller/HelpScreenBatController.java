package com.hardestfield.game.controller;

import com.badlogic.gdx.Gdx;
import com.hardestfield.game.HardestField;

/**
 * Created by mihai on 1/10/2015.
 * This class controls the screen depending on the user input
 */
public class HelpScreenBatController extends HelpScreenController {

    private static String[] screenDescription = {"BE CAREFUL TO HUNGRY BATS!"};

    public HelpScreenBatController(HardestField game) {
        super(game, screenDescription);
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            helpScreen.getGuiCam().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (nextBounds.contains(touchPoint.x, touchPoint.y)) {
                helpScreen.getGame().setScreen(new HelpScreenFinishLevelController(helpScreen.getGame()));
            }
        }
    }

    @Override
    public void render(float delta) {
        update();
        helpScreen.draw(4, 120);
    }
}
