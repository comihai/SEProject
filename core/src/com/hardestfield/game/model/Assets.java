package com.hardestfield.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mihai on 12/11/2014.
 *
 * This class loads all textures for all actors in arena
 */
public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;

    public static Texture loadTexture (String file)
    {
        return new Texture(Gdx.files.internal(file));
    }
    public static void load()
    {
        background = loadTexture("background.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
    }
}

