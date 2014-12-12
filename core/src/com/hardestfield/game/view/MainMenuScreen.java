package com.hardestfield.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;

/**
 * Created by mihai on 12/11/2014.
 * <p/>
 * This class define the settings for start screen
 */
public class MainMenuScreen extends ScreenAdapter {
    private HardestField game;
    private OrthographicCamera guiCam;
    private Rectangle playOption;

    public MainMenuScreen(HardestField game) {
        this.game = game;
        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        playOption = new Rectangle(160 - 150, 200 + 18, 300, 36);
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
        game.batch.draw(Assets.soundOn, 0, 0, 64, 64);
        game.batch.draw(Assets.musicOn, 250, 0, 64, 64);
        game.batch.end();
    }

    @Override
    public void render(float delta) {
        draw();
    }

    @Override
    public void pause() {

    }
}
