package com.hardestfield.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.controller.AreaController;
import com.hardestfield.game.model.Assets;

/**
 * Created by mihai on 12/13/2014.
 * This class construct all actors and obstacles in the play area
 */
public class PlayScreen {

    HardestField game;
    Area areaRenderer;
    AreaController control;
    OrthographicCamera guiCamera;
    Vector3 touchPoint;
    Rectangle pauseBounds;
    Rectangle quitBounds;
    Rectangle resumeBounds;
    int state;
    String scoreString;

    public static final int READY = 0;
    public static final int RUNNING = 1;
    public static final int PAUSED = 2;
    public static final int LEVEL_END = 3;
    public static final int GAME_OVER = 4;

    /**
     * Generic Constructor
     * @param game This variable creates and loads all the resources of the game
     */
    public PlayScreen(HardestField game) {
        this.game = game;
        state = READY;
        guiCamera = new OrthographicCamera(320, 480);
        guiCamera.position.set(160, 240, 0);
        touchPoint = new Vector3();
        //TODO
        //Increse the bounds of the pause, resume and quit bottons because on android are too small
        quitBounds = new Rectangle(290, 450, 12, 12);
        pauseBounds = new Rectangle(290, 450, 11, 11);
        resumeBounds = new Rectangle(250, 450, 12, 12);
        control = new AreaController();
        areaRenderer = new Area(game.batch, control);
    }

    /**
     * This function draws batched quads using indices
     */
    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        areaRenderer.render();
        guiCamera.update();
        game.batch.setProjectionMatrix(guiCamera.combined);
        game.batch.enableBlending();
        game.batch.begin();

        switch (state) {
            case READY:
                ready();
                break;
            case RUNNING:
                running();
                break;
            case PAUSED:
                paused();
                break;
            case GAME_OVER:
                gameOver();
                break;
            case LEVEL_END:
                levelEnd();
                break;

        }
        game.batch.end();
    }

    /**
     * This function draws a texture region for ready state of the game
     */
    private void ready() {
        game.batch.draw(Assets.clickForStartRegion, 10, 50, 300, 30);
    }

    /**
     * This function draws a texture region for runnnig state of the game and prints the score
     */
    private void running() {

        game.batch.draw(Assets.pauseRegion, 340 - 64, 500 - 64, 40, 40);
        Assets.font.draw(game.batch, scoreString, 10, 465);
    }

    /**
     * This function draws two textures for paused state of the game and prints the score
     */
    private void paused() {
        Assets.font.draw(game.batch, scoreString, 10, 465);
        game.batch.draw(Assets.quitGameRegion, 340 - 64, 500 - 64, 40, 40);
        game.batch.draw(Assets.playRegion, 340 - 104, 500 - 64, 40, 40);
    }

    /**
     * This function draws a texture region for gameOver state of the game and prints the score / new highscore
     */
    private void gameOver()
    {
        Assets.font.draw(game.batch, scoreString, 5, 465);
        game.batch.draw(Assets.gameOver, 60, 240 - 96 / 2, 200, 180);
    }

    /**
     * This function draws a texture region for gameOver state of the game and prints the score / new highscore
     */
    private void levelEnd()
    {
        String Text = "LEVEL FINISHED!";
        Assets.font.draw(game.batch, Text, 30, 440);
    }

    /**
     * Getters and Setters
     * @return
     */
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

    public Rectangle getResumeBounds() {
        return resumeBounds;
    }

    public void setResumeBounds(Rectangle resumeBounds) {
        this.resumeBounds = resumeBounds;
    }

    public Rectangle getPauseBounds() {
        return pauseBounds;
    }

    public void setPauseBounds(Rectangle pauseBounds) {
        this.pauseBounds = pauseBounds;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Area getAreaRenderer() {
        return areaRenderer;
    }


    public void setAreaRenderer(Area areaRenderer) {
        this.areaRenderer = areaRenderer;
    }

    public AreaController getControl() {
        return control;
    }

    public void setControl(AreaController control) {
        this.control = control;
    }

    public String getScoreString() {
        return scoreString;
    }

    public void setScoreString(String scoreString) {
        this.scoreString = scoreString;
    }
}
