package com.hardestfield.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector3;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.view.HighScores;

/**
 * Created by mihai on 12/23/2014.
 *
 * This class controls the highscores page
 */
public class HighScoresController extends ScreenAdapter {


    Vector3 touchPoint;
    HighScores highScores;

    public HighScoresController(HardestField game) {

        touchPoint = new Vector3();
        highScores = new HighScores(game);
    }

    public void update(){
        if(Gdx.input.justTouched())
        {
            highScores.getGuiCam().unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(highScores.getBackBounds().contains(touchPoint.x,touchPoint.y)){
                highScores.getGame().setScreen(new MainMenuScreenController(highScores.getGame()));
                return;
            }
        }
    }

    @Override
    public void render(float delta) {
        update();
        highScores.draw();
    }
}
