package com.yufeng.data.structure.array.applications;

/**
 * @description
 *     Class for storing high scores in an array in nondecreasing order
 * @author yufeng
 * @create 2020-01-20
 */
public class Scoreboard {

    private int numEntries = 0;             // number of actual entries

    private GameEntry[] board;              // array of game entries

    public Scoreboard(int capacity) {
        board = new GameEntry[capacity];
    }

    /**
     * Attempt to add a new score to the collection(if it is high enough)
     */
    public void add(GameEntry e) {
        int newScore = e.getScore();
        if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
            if (numEntries < board.length) {            // no score drops from the board
                numEntries ++;                          // number increase
            }

            // shift any lower scores right to make room for the new entry
            int j = numEntries - 1;
            while (j > 0 && board[j-1].getScore() < newScore) {
                board[j] = board[j-1];                  // shift entry from j-1 to j
                j --;                                   // and decrement j
            }
            board[j] = e;                               // when done, add new entry
        }
    }

    /**
     * Remove and return high score at index i
     */
    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= numEntries) {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }

        GameEntry temp = board[i];                      // save the object to be removed
        for (int j = 0; j < numEntries - 1; j ++) {     // count up from i(not down)
            board[j] = board[j+1];
        }

        board[numEntries - 1] = null;                   // num out the old last score
        numEntries --;
        return temp;                                    // return the removed object
    }


    public static void main(String[] args) {
        GameEntry gameEntry01 = new GameEntry("Mike", 1105);
        GameEntry gameEntry02 = new GameEntry("Rob", 750);
        GameEntry gameEntry03 = new GameEntry("Mike", 720);
        GameEntry gameEntry04 = new GameEntry("Mike", 660);
        GameEntry gameEntry05 = new GameEntry("Mike", 590);
        GameEntry gameEntry06 = new GameEntry("Mike", 510);

        Scoreboard scoreboard = new Scoreboard(10);
        scoreboard.add(gameEntry01);
        scoreboard.add(gameEntry02);
        scoreboard.add(gameEntry03);
        scoreboard.add(gameEntry04);
        scoreboard.add(gameEntry05);
        scoreboard.add(gameEntry06);

        GameEntry gameEntry07 = new GameEntry("Jill", 740);
        scoreboard.add(gameEntry07);
    }

}
