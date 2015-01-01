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
 * This class controls all the action performed during play
 */
public class PlayScreenController extends ScreenAdapter {

    PlayScreen playScreen;

    final int READY = 0;
    final int RUNNING = 1;
    final int PAUSED = 2;
    final int LEVEL_END = 3;
    final int GAME_OVER = 4;
    int lastScore;


    public PlayScreenController(HardestField game) {

        playScreen = new PlayScreen(game);
        lastScore = 0;
        playScreen.setScoreString("SCORE : 0");
    }

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
            case GAME_OVER:
                updateGameOver();
                break;
            case LEVEL_END:
                updateLevelEnd();
                break;
        }

    }

    private void updateReady() {
        if (Gdx.input.justTouched()) {
            playScreen.setState(RUNNING);
        }
    }

    private void updateRunning(float deltaTime) {
        if (Gdx.input.justTouched()) {
            playScreen.getGuiCamera().unproject(playScreen.getTouchPoint().set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playScreen.getPauseBounds().contains(playScreen.getTouchPoint().x, playScreen.getTouchPoint().y)) {
                playScreen.setState(PAUSED);
                return;
            }
        }
        Application.ApplicationType appType = Gdx.app.getType();


        if (appType == Application.ApplicationType.Android || appType == Application.ApplicationType.iOS) {
            playScreen.getControl().update(deltaTime, Gdx.input.getAccelerometerX());
        } else {
            float accel = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)) accel = 5f;
            if (Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)) accel = -5f;
            playScreen.getControl().update(deltaTime, accel);
        }
        if(playScreen.getControl().getScore() != lastScore)
        {
            lastScore = playScreen.getControl().getScore();
            playScreen.setScoreString("SCORE : " + lastScore);
        }
        if(playScreen.getControl().getState() == AreaController.STATE_NEXT_LEVEL)
        {
            playScreen.getGame().setScreen(new MainMenuScreenController(playScreen.getGame()));
        }
        if(playScreen.getControl().getState() == AreaController.STATE_GAME_OVER)
        {
            playScreen.setState(GAME_OVER);
            if(lastScore >= Settings.highScores[6])
                playScreen.setScoreString("NEW HIGHSCORE : "+lastScore);
            else
                playScreen.setScoreString("SCORE : "+ lastScore);
            Settings.addScore(lastScore);
            Settings.save();
        }
    }

    private void updatePaused() {
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
    private void updateGameOver()
    {
        if(Gdx.input.justTouched())
        {
            playScreen.getGame().setScreen(new MainMenuScreenController(playScreen.getGame()));
        }
    }
    private void updateLevelEnd()
    {
        if(Gdx.input.justTouched())
        {
            playScreen.getControl().setScore(lastScore);
            playScreen.setState(READY);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        playScreen.draw();
    }

    @Override
    public void pause() {
        if(playScreen.getState() == RUNNING)
            playScreen.setState(PAUSED);
    }
}
