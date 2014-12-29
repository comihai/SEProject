package com.hardestfield.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    public static TextureRegion pauseRegion;
    public static Texture pause;
    public static Texture play;
    public static TextureRegion playRegion;
    public static Texture quitGame;
    public static TextureRegion quitGameRegion;
    public static Texture clickForStart;
    public static TextureRegion clickForStartRegion;
    public static Texture states;
    public static Animation actorStatesJump;
    public static Animation actorStatesFall;
    public static TextureRegion actorJump;
    public static TextureRegion actorFall;
    public static TextureRegion actorHit;
    public static BitmapFont font;
    public static TextureRegion highScoresRegion;
    public static Texture statesBat;
    public static Animation batFly;
    public static Texture treeBranch;
    public static TextureRegion treeBranchRegion;


    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        background = loadTexture("images/background1.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
        mainMenu = loadTexture("images/mainMenu.png");
        mainMenuRegion = new TextureRegion(mainMenu, 0, 0, 300, 110);
        logo = loadTexture("images/logo_final_big.png");
        logoRegion = new TextureRegion(logo, 0, 0, 548, 284);
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
        pause = loadTexture("images/quit.png");
        pauseRegion = new TextureRegion(pause, 0, 0, 64, 64);
        play = loadTexture("images/play.png");
        playRegion = new TextureRegion(play, 0, 0, 64, 64);
        quitGame = loadTexture("images/exit.png");
        quitGameRegion = new TextureRegion(quitGame, 0, 0, 64, 64);
        clickForStart = loadTexture("images/clickForStart.png");
        clickForStartRegion = new TextureRegion(clickForStart, 0, 0, 300, 30);
        states = loadTexture("images/heros.png");
        actorJump = new TextureRegion(states, 0, 0, 64, 64);
        actorFall = new TextureRegion(states, 64, 0, 64, 64);
        actorHit = new TextureRegion(states, 192, 0, 64, 64);
        actorStatesJump = new Animation(0.2f, new TextureRegion(states, 0, 0, 64, 64), new TextureRegion(states, 0, 0, 64, 64));
        actorStatesFall = new Animation(0.3f, new TextureRegion(states, 64, 0, 64, 64), new TextureRegion(states, 128, 0, 64, 64));
        font = new BitmapFont(Gdx.files.internal("images/font.fnt"), Gdx.files.internal("images/font.png"), false);
        highScoresRegion = new TextureRegion(mainMenu, 80, 35, 140, 20);
        statesBat = loadTexture("images/enemies1.png");
        batFly = new Animation(0.2f, new TextureRegion(statesBat, 0, 0, 110, 100), new TextureRegion(statesBat, 110, 0, 110, 100));
        treeBranch = loadTexture("images/tree.png");
        treeBranchRegion = new TextureRegion(treeBranch, 0, 0, 1280, 444);
    }
}