package com.hardestfield.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.view.PlayScreen;

/**
 * Created by mihai on 12/13/2014.
 * This class controls all the action performed during play
 */
public class PlayScreenController extends ScreenAdapter {

    PlayScreen playScreen;

    public PlayScreenController(HardestField game) {
        playScreen = new PlayScreen(game);
    }

    public void update()
    {
        if (Gdx.input.justTouched()) {
            playScreen.getGuiCamera().unproject(playScreen.getTouchPoint().set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playScreen.getQuitBounds().contains(playScreen.getTouchPoint().x, playScreen.getTouchPoint().y)) {

                playScreen.getGame().setScreen(new MainMenuScreenController(playScreen.getGame()));
                return;
            }
        }
    }

    @Override
    public void render(float delta) {
        update();
        playScreen.draw();
    }
}
