package com.hardestfield.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by mihai on 12/12/2014.
 * This class initializes the start settings and maintains them
 */
public class Settings {

    public static boolean soundEnabled = true;
    public static boolean musicEnabled = true;
    public static int[] highScores = new int[] {0,0,0,0,0,0,0};
    static String file = ".highScoresHardestField";

    public static void load()
    {
        try
        {
            FileHandle fileHandle = Gdx.files.external(file);
            String[] strings = fileHandle.readString().split("\r\n");
            soundEnabled = Boolean.parseBoolean(strings[0]);
            musicEnabled = Boolean.parseBoolean(strings[1]);
            for (int i = 0; i < 7; i++) {
                highScores[i] = Integer.parseInt(strings[i+2]);
            }
        }
        catch (Throwable e)
        {

        }
    }
    public static void save()
    {
        try {
            FileHandle fileHandle = Gdx.files.external(file);
            fileHandle.writeString(Boolean.toString(soundEnabled)+"\n", false);
            fileHandle.writeString(Boolean.toString(musicEnabled)+"\n", true);
            for (int i = 0; i < 7; i++) {
                fileHandle.writeString(Integer.toString(highScores[i])+"\n", true);
            }
        }
        catch (Throwable e) {
        }
    }

    public static void addScore (int score) {
        for (int i = 0; i < 7; i++) {
            if (highScores[i] < score) {
                for (int j = 6; j > i; j--)
                    highScores[j] = highScores[j - 1];
                highScores[i] = score;
                break;
            }
        }
    }
}
