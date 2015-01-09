package com.hardestfield.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;
import com.hardestfield.game.utils.Settings;

/**
 * Created by mihai on 12/11/2014.
 * <p/>
 * This class construct and put options on start screen
 * Create the bounds touch for all options
 */
public class MainMenuScreen {
    private HardestField game;
    private OrthographicCamera guiCam;
    private Rectangle soundBounds;
    private Rectangle musicBounds;
    private Rectangle exitBounds;
    private Rectangle helpBounds;
    private Rectangle highScoresBounds;
    private Rectangle playBounds;
    private Vector3 touchPoint;

    /**
     * Generic constructor
     * @param game This variable creates and loads all the resources of the game
     */
    public MainMenuScreen(HardestField game) {
        this.game = game;
        guiCam = new OrthographicCamera(320, 480);
        guiCam.position.set(320 / 2, 480 / 2, 0);
        soundBounds = new Rectangle(5, 5, 30, 30);
        musicBounds = new Rectangle(285, 5, 30, 30);
        exitBounds = new Rectangle(128, 165, 55, 18);
        helpBounds = new Rectangle(128, 192, 59, 16);
        highScoresBounds = new Rectangle(96, 211, 129, 15);
        playBounds = new Rectangle(127, 231, 59, 18);
        touchPoint = new Vector3();
    }

    /**
     * This function draws the rectangles in the screen
     */
    public void draw() {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);

        game.batch.disableBlending();
        game.batch.begin();
        game.batch.draw(Assets.backgroundRegion, 0, 0, 320, 480);
        game.batch.end();

        game.batch.enableBlending();
        game.batch.begin();
        game.batch.draw(Assets.logoRegion, 160 - 274 / 2, 480 - 20 - 142, 274, 142);
        game.batch.draw(Assets.mainMenuRegion, 10, 210 - 110 / 2, 300, 110);
        game.batch.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 40, 40);
        game.batch.draw(Settings.musicEnabled ? Assets.musicOn : Assets.musicOff, 280, 0, 40, 40);
        game.batch.end();

    }

    /**
     * Getters and Setters
     * @return
     */
    public OrthographicCamera getGuiCam() {
        return guiCam;
    }

    public void setGuiCam(OrthographicCamera guiCam) {
        this.guiCam = guiCam;
    }

    public HardestField getGame() {
        return game;
    }

    public void setGame(HardestField game) {
        this.game = game;
    }

    public Rectangle getSoundBounds() {
        return soundBounds;
    }

    public void setSoundBounds(Rectangle soundBounds) {
        this.soundBounds = soundBounds;
    }

    public Rectangle getMusicBounds() {
        return musicBounds;
    }

    public void setMusicBounds(Rectangle musicBounds) {
        this.musicBounds = musicBounds;
    }

    public Rectangle getExitBounds() {
        return exitBounds;
    }

    public void setExitBounds(Rectangle exitBounds) {
        this.exitBounds = exitBounds;
    }

    public Rectangle getHelpBounds() {
        return helpBounds;
    }

    public void setHelpBounds(Rectangle helpBounds) {
        this.helpBounds = helpBounds;
    }

    public Rectangle getHighScoresBounds() {
        return highScoresBounds;
    }

    public void setHighScoresBounds(Rectangle highScoresBounds) {
        this.highScoresBounds = highScoresBounds;
    }

    public Rectangle getPlayBounds() {
        return playBounds;
    }

    public void setPlayBounds(Rectangle playBounds) {
        this.playBounds = playBounds;
    }

    public Vector3 getTouchPoint() {
        return touchPoint;
    }

    public void setTouchPoint(Vector3 touchPoint) {
        this.touchPoint = touchPoint;
    }


}
