package com.hardestfield.game.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by mihai on 12/22/2014.
 * <p/>
 * This class return one frame of the animation
 */
public class Animation {
    public static final boolean ANIMATION_LOOPING = false;
    public static final boolean ANIMATION_NONLOOPING = true;

    final TextureRegion[] keyFrames;
    final float frameDuration;

    public Animation(float frameDuration, TextureRegion... keyFrames) {
        this.frameDuration = frameDuration;
        this.keyFrames = keyFrames;
    }

    public TextureRegion getKeyFrame(float stateTime, boolean mode) {
        int frameNumber = (int) (stateTime / frameDuration);

        if (mode == ANIMATION_NONLOOPING) {
            frameNumber = Math.min(keyFrames.length - 1, frameNumber);
        } else {
            frameNumber = frameNumber % keyFrames.length;
        }
        return keyFrames[frameNumber];
    }
}
