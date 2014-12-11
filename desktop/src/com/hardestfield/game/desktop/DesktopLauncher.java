package com.hardestfield.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hardestfield.game.HardestField;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Hardest Field";
        config.width = 480;
        config.height = 800;
		new LwjglApplication(new HardestField(), config);
	}
}
