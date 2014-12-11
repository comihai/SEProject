package com.hardestfield.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;

/**
 * Created by mihai on 12/11/2014.
 */
public class MainMenuScreen extends ScreenAdapter{
    private HardestField game;
    private OrthographicCamera guiCam;

    public MainMenuScreen(HardestField game) {
        this.game = game;
        guiCam = new OrthographicCamera(320,480);
        guiCam.position.set(320/2,480/2,0);
    }
    public void draw()
    {
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
