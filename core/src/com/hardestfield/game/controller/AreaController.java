package com.hardestfield.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hardestfield.game.model.Bat;
import com.hardestfield.game.model.Branch;
import com.hardestfield.game.model.Squirrel;

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
    public final Random rand;
    int state = STATE_RUNNING;
    int score = 0;
    float heightSoFar;

    public AreaController() {
        this.squirrel = new Squirrel(5, 1);
        this.bats = new ArrayList<Bat>();
        this.branches = new ArrayList<Branch>();
        this.heightSoFar = 0;
        this.state = STATE_RUNNING;
        rand = new Random();
        generateBats();
        generateBranches();

    }
    private void generateBats()
    {
        for (int i = 0; i < 7; i++) {
            Bat bat = new Bat(rand.nextInt(10),6+i);
            bats.add(bat);
        }
    }
    private void generateBranches()
    {
        int type = Branch.BRANCH_TYPE_MOVING;
        float x;
        for (int i = 0; i < 2; i++) {
            x = rand.nextFloat() * (AREA_WIDTH - Branch.BRANCH_WIDTH) + Branch.BRANCH_WIDTH / 2;
            Branch branch = new Branch(type,x, 5+2*i);
            branches.add(branch);
        }
    }

    public void update(float deltaTime, float accelX) {

        updateSquirrel(deltaTime, accelX);
        updateBats(deltaTime);
        updateBranches(deltaTime);
        if(squirrel.getState()!= Squirrel.STATE_HIT)
            score++;
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
    private void updateBats(float deltaTime)
    {
        int len = bats.size();
        for (int i = 0; i < len; i++) {
            Bat bat = bats.get(i);
            bat.update(deltaTime);
        }
    }
    private void updateBranches(float deltaTime)
    {
        int len = branches.size();
        for (int i = 0; i < len; i++) {
            Branch branch = branches.get(i);
            branch.update(deltaTime);
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
