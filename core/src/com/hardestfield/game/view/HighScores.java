package com.hardestfield.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;

/**
 * Created by mihai on 12/23/2014.
 *
 * THis class aims the scores
 */
public class HighScores {

    Rectangle backBounds;
    String[] scores;
    float offset = 0;
    OrthographicCamera guiCam;
    HardestField game;

    public HighScores(HardestField game) {
        this.game = game;
        backBounds = new Rectangle(0, 0, 40, 40);
        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(160, 240, 0);
        scores = new String[5];
        for (int i = 0; i < 5; i++) {
            scores[i] = i+1+") ";

        }
    }
    public void draw()
    {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();

        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(Assets.backgroundRegion, 0, 0, 320, 480);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();




        game.batch.draw(Assets.quitGameRegion, 0, 0, 40, 40);
        game.batch.end();
    }

    public String[] getScores() {
        return scores;
    }

    public Rectangle getBackBounds() {
        return backBounds;
    }

    public OrthographicCamera getGuiCam() {
        return guiCam;
    }

    public HardestField getGame() {
        return game;
    }
}
