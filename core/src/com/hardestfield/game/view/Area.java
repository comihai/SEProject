package com.hardestfield.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hardestfield.game.controller.AreaController;
import com.hardestfield.game.model.Assets;
import com.hardestfield.game.model.Squirrel;
import com.hardestfield.game.utils.Animation;

/**
 * Created by mihai on 12/20/2014.
 * <p/>
 * THis class keeps all the details of the game
 */


public class Area {
    static final float REND_WIDTH = 10;
    static final float REND_HEIGHT = 15;
    AreaController area;
    OrthographicCamera cam;
    SpriteBatch batch;

    public Area(SpriteBatch batch, AreaController area) {
        this.area = area;
        this.cam = new OrthographicCamera(REND_WIDTH, REND_HEIGHT);
        this.cam.position.set(REND_WIDTH / 2, REND_HEIGHT / 2, 0);
        this.batch = batch;
    }

    public void render() {
        if (area.squirrel.position.y > cam.position.y) cam.position.y = area.squirrel.position.y;
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
    }

    public void renderBackground() {
        batch.disableBlending();
        batch.begin();
        batch.draw(Assets.backgroundRegion, cam.position.x - REND_WIDTH / 2, cam.position.y - REND_HEIGHT / 2, REND_WIDTH,
                REND_HEIGHT);
        batch.end();
    }

    public void renderObjects() {
        batch.enableBlending();
        batch.begin();
        renderSquirrel();
        batch.end();
    }

    private void renderSquirrel() {
        TextureRegion keyFrame;
        switch (area.squirrel.getState()) {
            case Squirrel.STATE_FALL:
                keyFrame = Assets.actorStatesFall.getKeyFrame(area.squirrel.getStateTime(), Animation.ANIMATION_LOOPING);
                break;
            case Squirrel.STATE_JUMP:
                keyFrame = Assets.actorStatesJump.getKeyFrame(area.squirrel.getStateTime(), Animation.ANIMATION_LOOPING);
                break;
            case Squirrel.STATE_HIT:
            default:
                keyFrame = Assets.actorFall;
        }

        float side = area.squirrel.speed.x < 0 ? -1 : 1;
        if (side < 0)
            batch.draw(keyFrame, area.squirrel.position.x + 0.5f, area.squirrel.position.y - 0.5f, side * 1, 1);
        else
            batch.draw(keyFrame, area.squirrel.position.x - 0.5f, area.squirrel.position.y - 0.5f, side * 1, 1);
    }


}
