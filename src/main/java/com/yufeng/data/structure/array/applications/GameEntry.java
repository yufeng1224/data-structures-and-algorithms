package com.yufeng.data.structure.array.applications;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-01-20
 */
public class GameEntry {

    private String name;                // name of the person earning the socre

    private int score;                  // the score value

    /**
     * Constructors a game entry with given parameters
     */
    public GameEntry(String n, int s) {
        name = n;
        score = s;
    }


    /**
     * Returns the name field
     */
    public String getName() {
        return name;
    }


    /**
     * Return the score field
     */
    public int getScore() {
        return score;
    }


    @Override
    public String toString() {
        return "(" + name + ", " + score + ")";
    }
}