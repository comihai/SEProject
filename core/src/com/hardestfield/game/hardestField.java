package com.hardestfield.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hardestfield.game.model.Assets;
import com.hardestfield.game.view.MainMenuScreen;

public class HardestField extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {

        batch = new SpriteBatch();
        Assets.load();
        setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}
}
