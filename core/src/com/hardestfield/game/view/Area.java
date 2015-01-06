package com.hardestfield.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hardestfield.game.controller.AreaController;
import com.hardestfield.game.model.*;
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

    /**
     * Generic Constructor
     * @param batch  Draws batched quads using indices
     * @param area   Instance of class Area used for accessing objects that exist in the game area
     */
    public Area(SpriteBatch batch, AreaController area) {
        this.area = area;
        this.cam = new OrthographicCamera(REND_WIDTH, REND_HEIGHT);
        this.cam.position.set(REND_WIDTH / 2, REND_HEIGHT / 2, 0);
        this.batch = batch;
    }

    /**
     * This function sets the position of the camera and the projection matrix
     */
    public void render() {
        if(area.squirrels.isEmpty())
            return;
        if (area.squirrels.get(0).position.y > cam.position.y) cam.position.y = area.squirrels.get(0).position.y;
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        renderBackground();
        renderObjects();
    }

    /**
     * THis function draws a background with the bottom left corner at x,y and stretching the region to cover the given width and height.
     */
    public void renderBackground() {
        batch.disableBlending();
        batch.begin();
        batch.draw(Assets.backgroundRegion, cam.position.x - REND_WIDTH / 2, cam.position.y - REND_HEIGHT / 2, REND_WIDTH,
                REND_HEIGHT);
        batch.end();
    }

    /**
     * THis function renders all the objects that exist in the game area
     */
    public void renderObjects() {
        batch.enableBlending();
        batch.begin();
        renderSquirrel();
        renderBat();
        renderBranch();
        renderBeehive();
        renderAcorn();
        renderAcornLeaf();
        renderHollow();
        batch.end();
    }

    /**
     * THis function draws the frames of the squirrel
     */
    private void renderSquirrel() {
        if(area.squirrels.isEmpty())
            return;
        TextureRegion keyFrame;
        switch (area.squirrels.get(0).getState()) {
            case Squirrel.STATE_FALL:
                keyFrame = Assets.actorStatesFall.getKeyFrame(area.squirrels.get(0).getStateTime(), Animation.ANIMATION_LOOPING);
                break;
            case Squirrel.STATE_JUMP:
                keyFrame = Assets.actorStatesJump.getKeyFrame(area.squirrels.get(0).getStateTime(), Animation.ANIMATION_LOOPING);
                break;
            case Squirrel.STATE_HIT:
                keyFrame = Assets.actorHit;
                break;
            default:
                keyFrame = Assets.actorHit;
        }

        float side = area.squirrels.get(0).speed.x < 0 ? 1 : -1;
        if (side < 0)
            batch.draw(keyFrame, area.squirrels.get(0).position.x + 0.5f, area.squirrels.get(0).position.y - 0.5f, side * 1, 1);
        else
            batch.draw(keyFrame, area.squirrels.get(0).position.x - 0.5f, area.squirrels.get(0).position.y - 0.5f, side * 1, 1);
    }

    /**
     * This function draws the frames of the bats
     */
    private void renderBat() {
        int len = area.bats.size();
        for (int i = 0; i < len; i++) {
            Bat bat = area.bats.get(i);
            TextureRegion keyFrame = Assets.batFly.getKeyFrame(bat.getStateTime(), Animation.ANIMATION_LOOPING);
            float side = bat.speed.x < 0 ? 1 : -1;
            if (side < 0)
                batch.draw(keyFrame, bat.position.x + 0.5f, bat.position.y - 0.5f, side * 1, 1);
            else
                batch.draw(keyFrame, bat.position.x - 0.5f, bat.position.y - 0.5f, side * 1, 1);
        }
    }

    /**
     * This function draws the frames of the branches
     */
    private void renderBranch() {
        int len = area.branches.size();
        for (int i = 0; i < len; i++) {
            Branch branch = area.branches.get(i);
            TextureRegion keyFrame = Assets.treeBranchRegion;
            batch.draw(keyFrame, branch.position.x - 1, branch.position.y - 0.25f, 2, 0.5f);
        }
    }

    /**
     * THis function draws the beehives
     */
    private void renderBeehive() {
        int len = area.beehives.size();
        for (int i = 0; i < len; i++) {
            Beehive beehive = area.beehives.get(i);
            batch.draw(Assets.beehaveRegion, beehive.position.x - 0.5f, beehive.position.y - 0.5f, 0.7f, 0.7f);
        }

    }

    /**
     * THis function draws the acorns
     */
    private void renderAcorn() {
        int len = area.acorns.size();
        for (int i = 0; i < len; i++) {
            Acorn acorn = area.acorns.get(i);
            batch.draw(Assets.acornRegion, acorn.position.x - 0.5f, acorn.position.y - 0.5f, 0.5f, 0.8f);
        }
    }

    /**
     * This function draws the acorns with leaf
     */
    private void renderAcornLeaf() {
        int len = area.acornLeafs.size();
        for (int i = 0; i < len; i++) {
            AcornLeaf acornLeaf = area.acornLeafs.get(i);
            batch.draw(Assets.acornLeafRegion, acornLeaf.position.x - 0.5f, acornLeaf.position.y - 0.5f, 0.8f, 0.8f);
        }
    }

    /**
     * This function draws the hollow at the finished of the game
     */
    private void renderHollow() {
        Hollow hollow = area.hollow;
        batch.draw(Assets.hollowRegion, hollow.position.x-2, hollow.position.y-2, 5, 5);
    }

}
