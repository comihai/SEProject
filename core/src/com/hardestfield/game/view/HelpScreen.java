package com.hardestfield.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;

/**
 * Created by mihai on 1/10/2015.
 * THis class draws the images for help option
 */
public class HelpScreen {
    HardestField game;
    OrthographicCamera guiCam;
    String[] title;

    /**
     * Generic Constructor
     *
     * @param game This variable creates and loads all the resources of the game
     */
    public HelpScreen(HardestField game) {
        this.game = game;
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 482, 602);
        title = new String[1];
        title[0] = "COLLECT ACORN TO WIN 10 PTS!";
    }

    /**
     * This constructor is used for derived classes
     *
     * @param game This variable creates and loads all the resources of the game
     */
    public HelpScreen(HardestField game, String[] string) {
        this.game = game;
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 482, 602);
        title = new String[string.length];
        title = string;
    }

    /**
     * This function draws the image and the representative description
     */
    public void draw(int screenNumber, int yCoord) {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.disableBlending();
        game.batch.begin();
        switch (screenNumber) {
            case 1:
                game.batch.draw(Assets.helpRegion1, 0, 0);
                break;
            case 2:
                game.batch.draw(Assets.helpRegion2, 0, 0);
                break;
            case 3:
                game.batch.draw(Assets.helpRegion3, 0, 0);
                break;
            case 4:
                game.batch.draw(Assets.helpRegion4, 0, 0);
                break;
            default:
                game.batch.draw(Assets.helpRegion5, 0, 0);
                break;
        }
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.playRegion, 410, 0, 64, 64);
        for (int i = 0; i < title.length; i++) {
            Assets.font.draw(game.batch, title[i], 5 + Math.abs(28 - title[i].length()) * 8, yCoord - 25 * i);
        }
        game.batch.end();
    }

    /**
     * Getters
     *
     * @return
     */
    public OrthographicCamera getGuiCam() {
        return guiCam;
    }

    public HardestField getGame() {
        return game;
    }
}
