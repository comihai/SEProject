package com.hardestfield.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hardestfield.game.HardestField;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        mainConfig(config);
        new LwjglApplication(new HardestField(), config);
    }

    private static void mainConfig(LwjglApplicationConfiguration config) {
        String os = System.getProperty("os.name");
        if (os.contains("Windows") || os.contains("Linux")) {
            config.addIcon("images/icon32x32.png", Files.FileType.Internal);
        } else if (os.contains("Mac")) {
            config.addIcon("images/icon128x128.png", Files.FileType.Internal);
        }
        config.title = "Hardest Field";
        config.width = 480;
        config.height = 600;
        config.resizable = false;
    }
}
