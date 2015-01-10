package com.hardestfield.game.controller;

import com.badlogic.gdx.Gdx;
import com.hardestfield.game.HardestField;

/**
 * Created by mihai on 1/10/2015.
 * <p/>
 * This class controls the screen depending on the user input
 */
public class HelpScreenAcornLeafController extends HelpScreenController {

    private static String[] screenDescription = {"COLLECT ACORN WITH LEAF", "TO WIN 20 PTS!"};

    public HelpScreenAcornLeafController(HardestField game) {
        super(game, screenDescription);
    }

    public void update() {
        if (Gdx.input.justTouched()) {
            helpScreen.getGuiCam().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (nextBounds.contains(touchPoint.x, touchPoint.y)) {
                helpScreen.getGame().setScreen(new HelpScreenBeehiveController(helpScreen.getGame()));
            }
        }
    }

    @Override
    public void render(float delta) {
        update();
        helpScreen.draw(2, 220);
    }
}
