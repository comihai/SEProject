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

    static final int READY = 0;
    static final int RUNNING = 1;
    static final int PAUSED = 2;
    static final int LEVEL_END = 3;
    static final int OVER = 4;

    public PlayScreenController(HardestField game) {
        playScreen = new PlayScreen(game);
    }

    public void update()
    {
        switch (playScreen.getState())
        {
            case READY:
                updateReady();
                break;
            case RUNNING:
                updateRunning();
                break;
            case PAUSED:
                updatePaused();
                break;
        }

    }
    private void updateReady()
    {
        if (Gdx.input.justTouched()) {
            playScreen.setState(RUNNING);
        }
    }
    private  void updateRunning()
    {
        if (Gdx.input.justTouched()) {
            playScreen.getGuiCamera().unproject(playScreen.getTouchPoint().set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playScreen.getPauseBounds().contains(playScreen.getTouchPoint().x, playScreen.getTouchPoint().y)) {
                playScreen.setState(PAUSED);
                return;
            }
        }
    }
    private void updatePaused () {
        if (Gdx.input.justTouched()) {
            playScreen.getGuiCamera().unproject(playScreen.getTouchPoint().set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playScreen.getResumeBounds().contains(playScreen.getTouchPoint().x, playScreen.getTouchPoint().y)) {
                playScreen.setState(RUNNING);
                return;
            }

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