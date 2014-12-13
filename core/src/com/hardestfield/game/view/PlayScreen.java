package com.hardestfield.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;

/**
 * Created by mihai on 12/13/2014.
 * This class construct all actors and obstacles in the play area
 */
public class PlayScreen {

    HardestField game;
    OrthographicCamera guiCamera;
    Vector3 touchPoint;
    Rectangle quitBounds;
    int state;

    static int READY = 0;
    static int RUNNING = 1;
    static int PAUSED = 2;
    static int LEVEL_END = 3;
    static int OVER = 4;

    public PlayScreen(HardestField game) {
        this.game = game;
        state = READY;
        guiCamera = new OrthographicCamera(320,480);
        guiCamera.position.set(320/2, 480/2, 0);
        touchPoint = new Vector3();
        quitBounds = new Rectangle(277,436,21,24);
    }

    public void draw()
    {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        guiCamera.update();
        game.batch.setProjectionMatrix(guiCamera.combined);
        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.backgroundRegion, 0, 0, 320, 480);
        game.batch.draw(Assets.quit, 320 - 64, 480 - 64, 64, 64);
        game.batch.end();
    }

    public HardestField getGame() {
        return game;
    }

    public void setGame(HardestField game) {
        this.game = game;
    }

    public OrthographicCamera getGuiCamera() {
        return guiCamera;
    }

    public void setGuiCamera(OrthographicCamera guiCamera) {
        this.guiCamera = guiCamera;
    }

    public Rectangle getQuitBounds() {
        return quitBounds;
    }

    public void setQuitBounds(Rectangle quitBounds) {
        this.quitBounds = quitBounds;
    }

    public Vector3 getTouchPoint() {
        return touchPoint;
    }

    public void setTouchPoint(Vector3 touchPoint) {
        this.touchPoint = touchPoint;
    }
}
