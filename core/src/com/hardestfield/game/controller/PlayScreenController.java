package com.hardestfield.game.controller;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.utils.Settings;
import com.hardestfield.game.view.PlayScreen;

/**
 * Created by mihai on 12/13/2014.
 * This class controls all the action performed during the game
 */
public class PlayScreenController extends ScreenAdapter {

    PlayScreen playScreen;

    final int READY = 0;
    final int RUNNING = 1;
    final int PAUSED = 2;
    final int LEVEL_END = 3;
    final int GAME_OVER = 4;
    int lastScore;


    /**
     * Generic Constructor
     *
     * @param game This variable creates and loads all the resources of the game
     */
    public PlayScreenController(HardestField game, int score, int level) {

        playScreen = new PlayScreen(game, score, level);
        lastScore = score;
        playScreen.setScoreString("SCORE : " + lastScore);
    }

    /**
     * This function updates the state of the game at each input
     *
     * @param deltaTime The time interval for updating
     */
    public void update(float deltaTime) {
        if (deltaTime > 0.1f) deltaTime = 0.1f;
        switch (playScreen.getState()) {
            case READY:
                updateReady();
                break;
            case RUNNING:
                updateRunning(deltaTime);
                break;
            case PAUSED:
                updatePaused();
                break;
            case LEVEL_END:
                updateLevelEnd();
                break;
            case GAME_OVER:
                updateGameOver();
                break;

        }

    }

    /**
     * This function set the game in running state on the first click
     */
    private void updateReady() {
        if (Gdx.input.justTouched()) {
            playScreen.setState(RUNNING);
        }
    }

    /**
     * This function update the screen and loads the options for resume or exit the game
     *
     * @param deltaTime The time interval for updating
     */
    private void updateRunning(float deltaTime) {
        if (Gdx.input.justTouched()) {
            playScreen.getGuiCamera().unproject(playScreen.getTouchPoint().set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playScreen.getPauseBounds().contains(playScreen.getTouchPoint().x, playScreen.getTouchPoint().y)) {
                playScreen.setState(PAUSED);
                return;
            }
        }
        Application.ApplicationType appType = Gdx.app.getType();

        /**
         * Check the type of platform for getting commands for moving left or right
         */
        if (appType == Application.ApplicationType.Android) {
            //for android
            playScreen.getControl().update(deltaTime, Gdx.input.getAccelerometerX());
        } else {
            //for desktop and web
            float accel = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) accel = 5f;
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) accel = -5f;
            playScreen.getControl().update(deltaTime, accel);
        }
        /**
         * Modify the score that is displayed if it is different from the last change
         */
        if (playScreen.getControl().getScore() != lastScore) {
            lastScore = playScreen.getControl().getScore();
            playScreen.setScoreString("SCORE : " + lastScore);
        }

        /**
         * Check if the actor has finished the level
         */
        if (playScreen.getControl().getState() == AreaController.STATE_NEXT_LEVEL) {
            playScreen.setState(LEVEL_END);
            if(playScreen.getLevelNumber() == 2) {
                Settings.addScore(lastScore);
                Settings.save();
            }


        }
        /**
         * Check if the game is over
         */
        if (playScreen.getControl().getState() == AreaController.STATE_GAME_OVER) {
            playScreen.setState(GAME_OVER);
            if (lastScore >= Settings.highScores[6])
                playScreen.setScoreString("NEW HIGHSCORE:" + lastScore);
            else
                playScreen.setScoreString("SCORE : " + lastScore);
            Settings.addScore(lastScore);
            Settings.save();
        }
    }

    /**
     * Update the state of the game depending on the option chosen
     */
    private void updatePaused() {
        if (Gdx.input.justTouched()) {
            playScreen.getGuiCamera().unproject(playScreen.getTouchPoint().set(Gdx.input.getX(), Gdx.input.getY(), 0));

            //resume
            if (playScreen.getResumeBounds().contains(playScreen.getTouchPoint().x, playScreen.getTouchPoint().y)) {
                playScreen.setState(RUNNING);
                return;
            }

            ///exit
            if (playScreen.getQuitBounds().contains(playScreen.getTouchPoint().x, playScreen.getTouchPoint().y)) {
                playScreen.getGame().setScreen(new MainMenuScreenController(playScreen.getGame()));
                return;
            }
        }
    }

    /**
     * If the game is over and one click is considered as input it will set the screen to mainscreen
     */
    private void updateGameOver() {
        if (Gdx.input.justTouched()) {
            playScreen.getGame().setScreen(new MainMenuScreenController(playScreen.getGame()));
        }
    }

    /**
     * iF the state of the game is LEVEL_END than at one click it will jump to next level
     */
    private void updateLevelEnd() {
        if (Gdx.input.justTouched()) {
            playScreen.getControl().setScore(lastScore);
            switch (playScreen.getLevelNumber())
            {
                case 0 :
                    playScreen.getGame().setScreen(new PlayScreenController(playScreen.getGame(),lastScore,1));
                    break;
                case 1 :
                    playScreen.getGame().setScreen(new PlayScreenController(playScreen.getGame(),lastScore,2));
                    break;
                case 2 :
                    playScreen.getGame().setScreen(new MainMenuScreenController(playScreen.getGame()));
                    break;
            }

        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        playScreen.draw();
    }

    @Override
    public void pause() {
        if (playScreen.getState() == RUNNING)
            playScreen.setState(PAUSED);
    }
}
