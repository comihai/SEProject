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
    public final List<Squirrel> squirrels;
    public final List<Bat> bats;
    public final List<Branch> branches;
    public final List<Beehive> beehives;
    public final List<Acorn> acorns;
    public final List<AcornLeaf> acornLeafs;
    public Hollow hollow;
    public final Random rand;
    int state = STATE_RUNNING;
    int score;
    float heightSoFar;
    int boost;

    /**
     * Generic Constructor
     */
    public AreaController(int score, int level) {
        this.boost = level + 1;
        this.score = score;
        this.squirrels = new ArrayList<Squirrel>();
        Squirrel squirrel = new Squirrel(5, 1);
        this.squirrels.add(squirrel);
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

    /**
     * This function populates the list of bats
     *
     * @param x position on the x axis
     * @param y position on the y axis
     */
    private void generateBat(float x, float y) {
        Bat bat = new Bat(x + rand.nextFloat(), y + Bat.BAT_HEIGHT + rand.nextFloat() * 2);
        bats.add(bat);
    }

    /**
     * This function populates the list of beehives
     *
     * @param x position on the x axis
     * @param y position on the y axis
     */
    private void generateBeehive(float x, float y) {
        Beehive beehive = new Beehive(x, y + Branch.BRANCH_HEIGHT / 2 + Beehive.BEEHIVE_HEIGHT / 2);
        beehives.add(beehive);
    }

    /**
     * This function populates the list of acorns
     *
     * @param x position on the x axis
     * @param y position on the y axis
     */
    private void generateAcorn(float x, float y) {
        Acorn acorn = new Acorn(x + rand.nextFloat(), y + Acorn.ACORN_HEIGHT + rand.nextFloat() * 3);
        acorns.add(acorn);
    }

    /**
     * This function populates the list of acorns with leaf
     *
     * @param x position on the x axis
     * @param y position on the y axis
     */
    private void generateAcornLeaf(float x, float y) {
        AcornLeaf acornLeaf = new AcornLeaf(x + rand.nextFloat(), y + Acorn.ACORN_HEIGHT + rand.nextFloat() * 3);
        acornLeafs.add(acornLeaf);
    }

    /**
     * This function populates the list of branches
     */
    private void generateBranches() {
        float y = 5;
        //the laws of physics : V^2 = 2*g*Hmax
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
                generateBeehive(x, y);
            }
            float acornType = rand.nextFloat();
            if (acornType > 0.5f) {
                if (acornType <= 0.8f) {
                    generateAcorn(x, y);
                } else {
                    generateAcornLeaf(x, y);
                }
            }
            y += (maxJumpHeight - rand.nextFloat() * (maxJumpHeight / 3));
        }
        hollow = new Hollow(4.5f, y);
    }

    /**
     * THis function updates all object in the area
     *
     * @param deltaTime The time interval for updating
     * @param accelX    The acceleration for the squirrel
     */
    public void update(float deltaTime, float accelX) {
        if (squirrels.isEmpty())
            return;
        updateSquirrel(deltaTime, accelX);
        updateBats(deltaTime);
        updateBranches(deltaTime);
        updateAcorns(deltaTime);
        updateAcornLeafs(deltaTime);
        if (squirrels.get(0).getState() != Squirrel.STATE_HIT) {
            checkCollisions();
        }
        checkGameOver();
    }

    /**
     * This function updates the state of the squirrel
     *
     * @param deltaTime The time interval for updating
     * @param accelX    The acceleration for the squirrel
     */
    private void updateSquirrel(float deltaTime, float accelX) {
        if (squirrels.isEmpty())
            return;
        if (squirrels.get(0).getState() != Squirrel.STATE_HIT && squirrels.get(0).position.y <= 0.5f) {
            squirrels.get(0).speed.y = Squirrel.JUMP_VELOCITY;
            squirrels.get(0).setState(Squirrel.STATE_JUMP);
            squirrels.get(0).setStateTime(0);
        }
        if (squirrels.get(0).getState() != Squirrel.STATE_HIT)
            squirrels.get(0).speed.x = -accelX / 10 * Squirrel.MOVE_VELOCITY;
        squirrels.get(0).update(deltaTime);
        heightSoFar = Math.max(squirrels.get(0).position.y, heightSoFar);
    }

    /**
     * This function updates the state of the list of the bats
     *
     * @param deltaTime The time interval for updating
     */
    private void updateBats(float deltaTime) {
        int len = bats.size();
        for (int i = 0; i < len; i++) {
            Bat bat = bats.get(i);
            bat.update(deltaTime, rand.nextFloat() * (boost * 3));
        }
    }

    /**
     * This function updates the state of the list of the bracnes
     *
     * @param deltaTime The time interval for updating
     */
    private void updateBranches(float deltaTime) {
        int len = branches.size();
        for (int i = 0; i < len; i++) {
            Branch branch = branches.get(i);
            branch.update(deltaTime, boost * 2);
        }
    }

    /**
     * This function updates the state of the list of the acorns
     *
     * @param deltaTime The time interval for updating
     */
    private void updateAcorns(float deltaTime) {
        int len = acorns.size();
        for (int i = 0; i < len; i++) {
            Acorn acorn = acorns.get(i);
            acorn.update(deltaTime);
        }
    }

    /**
     * This function updates the state of the list of the acorns with leaf
     *
     * @param deltaTime The time interval for updating
     */
    private void updateAcornLeafs(float deltaTime) {
        int len = acornLeafs.size();
        for (int i = 0; i < len; i++) {
            AcornLeaf acornLeaf = acornLeafs.get(i);
            acornLeaf.update(deltaTime);
        }
    }

    /**
     * This function verifies the collisions that squirrel gets
     */
    private void checkCollisions() {
        checkBranchesCollisions();
        checkBeehivesCollisions();
        checkAcornCollisions();
        checkAcornLeafCollisions();
        checkBatsCollisions();
        checkHollowCollisions();
    }

    /**
     * This function verifies if the squirrel collides one brach
     */
    private void checkBranchesCollisions() {
        if (squirrels.isEmpty())
            return;
        if (squirrels.get(0).speed.y > 0) return;
        int len = branches.size();
        for (int i = 0; i < len; i++) {
            Branch branch = branches.get(i);
            if (squirrels.get(0).position.y > branch.position.y) {
                if (squirrels.get(0).bounds.overlaps(branch.bounds)) {
                    squirrels.get(0).hitBranch();
                    break;
                }
            }
        }
    }

    /**
     * This function verifies if the squirrel collides one beehive
     */
    private void checkBeehivesCollisions() {
        if (squirrels.isEmpty())
            return;
        if (squirrels.get(0).speed.y > 0) return;
        int len = beehives.size();
        for (int i = 0; i < len; i++) {
            Beehive beehive = beehives.get(i);
            if (squirrels.get(0).position.y > beehive.position.y) {
                if (squirrels.get(0).bounds.overlaps(beehive.bounds)) {
                    squirrels.get(0).hitBeehive();
                }
            }
        }
    }

    /**
     * This function verifies if the squirrel collides one acorn
     */
    private void checkAcornCollisions() {
        if (squirrels.isEmpty())
            return;
        int len = acorns.size();
        for (int i = 0; i < len; i++) {
            Acorn acorn = acorns.get(i);
            if (squirrels.get(0).bounds.overlaps(acorn.bounds)) {
                acorns.remove(acorn);
                len = acorns.size();
                score += Acorn.ACORN_SCORE;
            }
        }
    }

    /**
     * This function verifies if the squirrel collides one acorn with leaf
     */
    private void checkAcornLeafCollisions() {
        if (squirrels.isEmpty())
            return;
        int len = acornLeafs.size();
        for (int i = 0; i < len; i++) {
            AcornLeaf acornLeaf = acornLeafs.get(i);
            if (squirrels.get(0).bounds.overlaps(acornLeaf.bounds)) {
                acornLeafs.remove(acornLeaf);
                len = acornLeafs.size();
                score += AcornLeaf.ACORNLEAF_SCORE;
            }
        }
    }

    /**
     * This function verifies if the squirrel collides one bat
     */
    private void checkBatsCollisions() {
        if (squirrels.isEmpty())
            return;
        int len = bats.size();
        for (int i = 0; i < len; i++) {
            Bat bat = bats.get(i);
            if (bat.bounds.overlaps(squirrels.get(0).bounds)) {
                squirrels.get(0).hitBat();
            }
        }
    }

    /**
     * This function verifies if the squirrel collides the final hollow
     */
    private void checkHollowCollisions() {
        if (squirrels.isEmpty())
            return;
        Squirrel squirrel = squirrels.get(0);
        if (hollow.bounds.overlaps(squirrel.bounds)) {
            state = STATE_NEXT_LEVEL;
            //squirrels.remove(squirrel);
        }
    }

    /**
     * This function verifies if the squirrel has died
     */
    private void checkGameOver() {
        if (squirrels.isEmpty())
            return;
        if (heightSoFar - 7.5f > squirrels.get(0).position.y) {
            state = STATE_GAME_OVER;
        }
    }

    /**
     * Getters and Setters
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
