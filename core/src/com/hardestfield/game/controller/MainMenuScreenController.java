package com.hardestfield.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hardestfield.game.HardestField;
import com.hardestfield.game.model.Assets;
import com.hardestfield.game.utils.Settings;
import com.hardestfield.game.view.MainMenuScreen;


/**
 * Created by mihai on 12/12/2014.
 */
public class MainMenuScreenController extends ScreenAdapter{

    private MainMenuScreen mms;
    private Rectangle soundBounds;
    private Rectangle musicBounds;
    private Vector3 touchPoint;
    private int volume=100;
    private int temp = 1;

    public MainMenuScreenController(HardestField game) {
        mms = new MainMenuScreen( game);
        soundBounds = new Rectangle(10, 10, 40, 40);
        musicBounds = new Rectangle(270,10, 40,40);
        touchPoint = new Vector3();
    }
    public void update() {
        if (Gdx.input.justTouched()) {
            mms.guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
                //Settings.soundEnabled = !Settings.soundEnabled;
                if(temp==1) {
                    volume -= 20;
                   
                    Assets.backgroundMusic.setVolume((float)volume/200);
                    if(volume == 0)
                    {
                        temp=0;
                        Settings.soundEnabled = !Settings.soundEnabled;
                    }
                }
                else
                {
                    volume+=20;
                    Assets.backgroundMusic.setVolume((float)volume/200);
                    if(volume == 100)
                    {
                        temp=1;
                        Settings.soundEnabled = !Settings.soundEnabled;
                    }
                }


                return;
            }
            if (musicBounds.contains(touchPoint.x, touchPoint.y)) {
                Settings.musicEnabled = !Settings.musicEnabled;
                if(Settings.musicEnabled)
                    Assets.backgroundMusic.play();
                else
                    Assets.backgroundMusic.pause();
                return;
            }

        }
    }

    @Override
    public void render(float delta) {
        update();
        mms.draw();
    }
}
