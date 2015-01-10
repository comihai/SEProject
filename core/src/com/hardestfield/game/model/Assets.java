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
    public static Texture beehave;
    public static TextureRegion beehaveRegion;
    public static Texture acorn;
    public static TextureRegion acornRegion;
    public static Texture acornLeaf;
    public static TextureRegion acornLeafRegion;
    public static Texture hollow;
    public static TextureRegion hollowRegion;
    public static Texture gameOver;
    public static TextureRegion gameOverRegion;
    public static Texture help1;
    public static TextureRegion helpRegion1;
    public static Texture help2;
    public static TextureRegion helpRegion2;
    public static Texture help3;
    public static TextureRegion helpRegion3;
    public static Texture help4;
    public static TextureRegion helpRegion4;
    public static Texture help5;
    public static TextureRegion helpRegion5;


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
        beehave = loadTexture("images/beehive.png");
        beehaveRegion = new TextureRegion(beehave, 0, 0, 512, 512);
        acorn = loadTexture("images/acorn.png");
        acornRegion = new TextureRegion(acorn, 0, 0, 444, 700);
        acornLeaf = loadTexture("images/leafAcorn.png");
        acornLeafRegion = new TextureRegion(acornLeaf, 0, 0, 700, 700);
        hollow = loadTexture("images/hollow.png");
        hollowRegion = new TextureRegion(hollow, 0, 0, 300, 256);
        gameOver = loadTexture("images/gameOver.png");
        gameOverRegion = new TextureRegion(gameOver, 0, 0, 512, 512);
        help1 = loadTexture("images/help1.png");
        helpRegion1 = new TextureRegion(help1, 0, 0, 482, 602);
        help2 = loadTexture("images/help2.png");
        helpRegion2 = new TextureRegion(help2, 0, 0, 482, 602);
        help3 = loadTexture("images/help3.png");
        helpRegion3 = new TextureRegion(help3, 0, 0, 482, 602);
        help4 = loadTexture("images/help4.png");
        helpRegion4 = new TextureRegion(help4, 0, 0, 482, 602);
        help5 = loadTexture("images/help5.png");
        helpRegion5 = new TextureRegion(help5, 0, 0, 482, 602);

    }
}