package com.hardestfield.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;
import com.hardestfield.game.utils.Settings;

/**
 * Created by mihai on 12/11/2014.
 * <p/>
 * This class construct and put options on start screen
 */
public class MainMenuScreen{
    public HardestField game;
    public OrthographicCamera guiCam;


    public MainMenuScreen(HardestField game) {
        this.game = game;
        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);


        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.backgroundRegion, 0, 0, 320, 480);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.logoRegion, 160 - 274 / 2, 480 - 20 - 142, 274, 142);
        game.batch.draw(Assets.mainMenuRegion, 10, 210 - 110 / 2, 300, 110);
        game.batch.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
        game.batch.draw(Settings.musicEnabled ? Assets.musicOn : Assets.musicOff, 250, 0, 64, 64);
        game.batch.end();
    }


}
