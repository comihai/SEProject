package com.hardestfield.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hardestfield.game.controller.MainMenuScreenController;
import com.hardestfield.game.model.Assets;
import com.hardestfield.game.utils.Settings;

public class HardestField extends Game {
    //is common for all the states of the game
    public SpriteBatch batch;

    @Override
    public void create() {

        /**
         * Constructs a new SpriteBatch with a size of 1000, one buffer, and the default shader
         */
        batch = new SpriteBatch();

        /**
         * Loads the startup settings
         */
        Settings.load();
        /**
         * Loads the resources
         */
        Assets.load();

        /**
         * Startup screen
         */
        setScreen(new MainMenuScreenController(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
