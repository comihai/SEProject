package com.hardestfield.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hardestfield.game.utils.Settings;

/**
 * Created by mihai on 12/11/2014.
 * <p/>
 * This class loads all textures for all actors in arena
 */
public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;
    public static Texture mainMenu;
    public static TextureRegion mainMenuRegion;
    public static Texture logo;
    public static TextureRegion logoRegion;
    public static Texture sound;
    public static TextureRegion soundOn;
    public static TextureRegion soundOff;
    public static Texture music;
    public static TextureRegion musicOn;
    public static TextureRegion musicOff;
    public static Music backgroundMusic;
    public static TextureRegion quitRegion;
    public static Texture quit;
    public static Texture play;
    public static TextureRegion playRegion;
    public static Texture quitGame;
    public static TextureRegion quitGameRegion;


    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        background = loadTexture("images/background1.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
        mainMenu = loadTexture("images/mainMenu.png");
        mainMenuRegion = new TextureRegion(mainMenu, 0, 0, 300, 110);
        logo = loadTexture("images/logo_bun1.png");
        logoRegion = new TextureRegion(logo, 0, 0, 274, 142);
        sound = loadTexture("images/soundOn.png");
        soundOn = new TextureRegion(sound, 0, 0, 64, 64);
        sound = loadTexture("images/soundOff.png");
        soundOff = new TextureRegion(sound, 0, 0, 64, 64);
        music = loadTexture("images/musicOn.png");
        musicOn = new TextureRegion(music, 0, 0, 64, 64);
        music = loadTexture("images/musicOff.png");
        musicOff = new TextureRegion(music, 0, 0, 64, 64);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("images/backgroundMusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        if (Settings.musicEnabled)
            backgroundMusic.play();
        quit = loadTexture("images/quit.png");
        quitRegion = new TextureRegion(quit, 0,0, 64,64);
        play = loadTexture("images/play1.png");
        playRegion = new TextureRegion(play, 0,0, 64,64);
        quitGame = loadTexture("images/exit2.png");
        quitGameRegion = new TextureRegion(quitGame, 0,0,64,64);
    }
}
