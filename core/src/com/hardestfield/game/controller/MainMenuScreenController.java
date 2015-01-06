package com.hardestfield.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;
import com.hardestfield.game.utils.Settings;
import com.hardestfield.game.view.MainMenuScreen;


/**
 * Created by mihai on 12/12/2014.
 * <p/>
 * This is the controller for main menu that manages the user input
 */

public class MainMenuScreenController extends ScreenAdapter {

    private MainMenuScreen mms;
    private int volume = 100;
    private int temp = 1;

    /**
     * Generic constructor
     *
     * @param game This variable creates and loads all the resources of the game
     */
    public MainMenuScreenController(HardestField game) {
        mms = new MainMenuScreen(game);
    }

    /**
     * THis function updates the screen depending on user input
     */
    public void update() {
        if (Gdx.input.justTouched()) {
            mms.getGuiCam().unproject(mms.getTouchPoint().set(Gdx.input.getX(), Gdx.input.getY(), 0));

            //TODO
            //redraw the menu for multiplayer option
            //write code for help option

            //controls the volume of the music
            if (mms.getSoundBounds().contains(mms.getTouchPoint().x, mms.getTouchPoint().y)) {
                if (temp == 1) {
                    volume -= 20;

                    Assets.backgroundMusic.setVolume((float) volume / 200);
                    if (volume == 0) {
                        temp = 0;
                        Settings.soundEnabled = !Settings.soundEnabled;
                    }
                } else {
                    volume += 20;
                    Assets.backgroundMusic.setVolume((float) volume / 200);
                    if (volume == 100) {
                        temp = 1;
                        Settings.soundEnabled = !Settings.soundEnabled;
                    }
                }
                return;
            }
            //controls the music on / off
            if (mms.getMusicBounds().contains(mms.getTouchPoint().x, mms.getTouchPoint().y)) {
                Settings.musicEnabled = !Settings.musicEnabled;
                if (Settings.musicEnabled)
                    Assets.backgroundMusic.play();
                else
                    Assets.backgroundMusic.pause();
                return;
            }
            //check if is clicked the play option
            if (mms.getPlayBounds().contains(mms.getTouchPoint().x, mms.getTouchPoint().y)) {
                mms.getGame().setScreen(new PlayScreenController(mms.getGame()));
            }
            //check if is clicked the highscores option
            if (mms.getHighScoresBounds().contains(mms.getTouchPoint().x, mms.getTouchPoint().y)) {
                mms.getGame().setScreen(new HighScoresController(mms.getGame()));
            }
            //check if is clicked the help option
            if (mms.getExitBounds().contains(mms.getTouchPoint().x, mms.getTouchPoint().y)) {
                Gdx.app.exit();
                return;
            }
        }
    }

    @Override
    public void render(float delta) {
        update();
        mms.draw();
    }
}
