package com.hardestfield.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hardestfield.game.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mihai on 12/20/2014.
 * <p/>
 * THis class controls all the details of the game
 */
public class AreaController {

    //latimea pe care se desfasoara jocul
    public static final float AREA_WIDTH = 10;
    //lungimea pe care se desfasoara jocul
    public static final float AREA_HEIGHT = 300;
    public static final Vector2 gravity = new Vector2(0, -12);
    public static final int STATE_RUNNING = 0;
    public static final int STATE_NEXT_LEVEL = 1;
    public static final int STATE_GAME_OVER = 2;
    public final Squirrel squirrel;
    public final List<Bat> bats;
    public final List<Branch> branches;
    public final List<Beehive> beehives;
    public final List<Acorn> acorns;
    public final List<AcornLeaf> acornLeafs;
    public final Random rand;
    int state = STATE_RUNNING;
    int score = 0;
    float heightSoFar;

    public AreaController() {
        this.squirrel = new Squirrel(5, 1);
        this.bats = new ArrayList<Bat>();
        this.branches = new ArrayList<Branch>();
        this.beehives = new ArrayList<Beehive>();
        this.acorns = new ArrayList<Acorn>();
        this.acornLeafs = new ArrayList<AcornLeaf>();
        this.heightSoFar = 0;
        this.state = STATE_RUNNING;
        rand = new Random();
        generateBranches();

    }

    private void generateBat(float x, float y) {
        Bat bat = new Bat(x + rand.nextFloat(), y + Bat.BAT_HEIGHT + rand.nextFloat() * 2);
        bats.add(bat);
    }
    private void generateBeehive(float x, float y)
    {
        Beehive beehive = new Beehive(x,y+Branch.BRANCH_HEIGHT/2+Beehive.BEEHIVE_HEIGHT/2);
        beehives.add(beehive);
    }
    private void generateAcorn(float x, float y)
    {
        Acorn acorn = new Acorn(x + rand.nextFloat(), y+Acorn.ACORN_HEIGHT + rand.nextFloat() * 3);
        acorns.add(acorn);
    }
    private void generateAcornLeaf(float x, float y)
    {
        AcornLeaf acornLeaf = new AcornLeaf(x + rand.nextFloat(), y+Acorn.ACORN_HEIGHT + rand.nextFloat() * 3);
        acornLeafs.add(acornLeaf);
    }

    private void generateBranches() {
        float y = 5;
        float maxJumpHeight = Squirrel.JUMP_VELOCITY * Squirrel.JUMP_VELOCITY / (2 * -gravity.y);
        while (y < AREA_HEIGHT) {
            int type = rand.nextFloat() > 0.7f ? Branch.BRANCH_TYPE_MOVING : Branch.BRANCH_TYPE_STATIC;
            float x = rand.nextFloat() * (AREA_WIDTH - Branch.BRANCH_WIDTH) + Branch.BRANCH_WIDTH / 2;
            Branch branch = new Branch(type, x, y);
            branches.add(branch);

            if (y > AREA_HEIGHT / 3 && rand.nextFloat() > 0.7f) {
                generateBat(x, y);
            }
            if (rand.nextFloat() > 0.9f && type != Branch.BRANCH_TYPE_MOVING) {
                generateBeehive(x,y);
            }
            float acornType = rand.nextFloat();
            if (acornType > 0.5f) {
                if(acornType <= 0.8f) {
                    generateAcorn(x, y);
                }
                else
                {
                    generateAcornLeaf(x,y);
                }
            }

            y += (maxJumpHeight - rand.nextFloat() * (maxJumpHeight / 3));
        }
    }

    public void update(float deltaTime, float accelX) {

        updateSquirrel(deltaTime, accelX);
        updateBats(deltaTime);
        updateBranches(deltaTime);
        updateAcorns(deltaTime);
        updateAcornLeafs(deltaTime);
        if (squirrel.getState() != Squirrel.STATE_HIT) {
            checkCollisions();
        }
    }

    private void updateSquirrel(float deltaTime, float accelX) {
        if (squirrel.getState() != Squirrel.STATE_HIT && squirrel.position.y <= 0.5f) {
            squirrel.speed.y = Squirrel.JUMP_VELOCITY;
            state = Squirrel.STATE_JUMP;
            squirrel.setStateTime(0);
        }
        if (squirrel.getState() != Squirrel.STATE_HIT)
            squirrel.speed.x = -accelX / 10 * Squirrel.MOVE_VELOCITY;
        squirrel.update(deltaTime);

    }

    private void updateBats(float deltaTime) {
        int len = bats.size();
        for (int i = 0; i < len; i++) {
            Bat bat = bats.get(i);
            bat.update(deltaTime, rand.nextFloat() * 2);
        }
    }

    private void updateBranches(float deltaTime) {
        int len = branches.size();
        for (int i = 0; i < len; i++) {
            Branch branch = branches.get(i);
            branch.update(deltaTime);
        }
    }
    private void updateAcorns(float deltaTime)
    {
        int len = acorns.size();
        for (int i = 0; i < len; i++) {
            Acorn acorn = acorns.get(i);
            acorn.update(deltaTime);
        }
    }
    private void updateAcornLeafs(float deltaTime)
    {
        int len = acornLeafs.size();
        for (int i = 0; i < len; i++) {
            AcornLeaf acornLeaf = acornLeafs.get(i);
            acornLeaf.update(deltaTime);
        }
    }

    private void checkCollisions() {
        checkBranchesCollisions();
        checkBeehivesCollisions();
        checkAcornCollisions();
        checkAcornLeafCollisions();
    }

    private void checkBranchesCollisions() {
        if (squirrel.speed.y > 0) return;
        int len = branches.size();
        for (int i = 0; i < len; i++) {
            Branch branch = branches.get(i);
            if (squirrel.position.y > branch.position.y) {
                if (squirrel.bounds.overlaps(branch.bounds)) {
                    squirrel.hitBranch();
                    break;
                }
            }
        }
    }
    private void checkBeehivesCollisions()
    {
        if (squirrel.speed.y > 0) return;
        int len = beehives.size();
        for (int i = 0; i < len; i++) {
            Beehive beehive = beehives.get(i);
            if (squirrel.position.y > beehive.position.y) {
                if (squirrel.bounds.overlaps(beehive.bounds)) {
                    squirrel.hitBeehive();
                }
            }
        }
    }
    private void checkAcornCollisions()
    {
        int len = acorns.size();
        for (int i = 0; i < len; i++) {
            Acorn acorn = acorns.get(i);
            if(squirrel.bounds.overlaps(acorn.bounds))
            {
                acorns.remove(acorn);
                len = acorns.size();
                score += Acorn.ACORN_SCORE;
            }
        }
    }
    private void checkAcornLeafCollisions()
    {
        int len = acornLeafs.size();
        for (int i = 0; i < len; i++) {
            AcornLeaf acornLeaf = acornLeafs.get(i);
            if(squirrel.bounds.overlaps(acornLeaf.bounds))
            {
                acornLeafs.remove(acornLeaf);
                len = acornLeafs.size();
                score += AcornLeaf.ACORNLEAF_SCORE;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
