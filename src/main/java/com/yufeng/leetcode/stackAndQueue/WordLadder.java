package com.yufeng.leetcode.stackAndQueue;

import javafx.util.Pair;

import java.util.*;

/**
 * @description
 *      
 * @author yufeng
 * @create
 */
public class WordLadder {

    /**
     * BFS
     * Time Complexity: O(n*n)
     * Space Complexity: O(n)
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }

        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        int begin = wordList.indexOf(beginWord);

        int n = wordList.size();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < i; j ++) {
                g[j][i] = g[i][j] = similar(wordList.get(i), wordList.get(j));
            }
        }

        // bfs
        LinkedList<Integer> q = new LinkedList<>();
        int[] step = new int[n];

        q.addLast(begin);
        step[begin] = 1;

        while (!q.isEmpty()) {
            int cur = q.removeFirst();
            for (int i = 0; i < n; i ++)
                if (step[i] == 0 && g[cur][i]) {
                    if (i == end) {
                        return step[cur] + 1;
                    }
                    step[i] = step[cur] + 1;
                    q.addLast(i);
                }
        }
        return 0;
    }

    /**
     * BFS
     * Using set to store all the words and erase visited word eagerly.
     * Time Complexity: O(n*n)
     * Space Complexity: O(n)
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>();
        for (String word: wordList) {
            wordSet.add(word);
        }

        // bfs
        LinkedList<Pair<String, Integer>> q = new LinkedList<>();
        q.addLast(new Pair<>(beginWord, 1));
        wordSet.remove(beginWord);

        HashSet<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            String curWord = q.getFirst().getKey();
            int curStep = q.getFirst().getValue();
            q.removeFirst();

            visited.clear();
            for (String word: wordSet) {
                if (similar(word, curWord)) {
                    if (word.equals(endWord)) {
                        return curStep + 1;
                    }
                    q.addLast(new Pair<>(word, curStep + 1));
                    visited.add(word);
                }
            }

            for (String word: visited) {
                wordSet.remove(word);
            }
        }
        return 0;
    }

    /**
     * Bi-direction BFS
     * Time Complexity: O(n*n)
     * Space Complexity: O(n)
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }

        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        int n = wordList.size();
        int begin = wordList.indexOf(beginWord);


        Boolean[][] g = new Boolean[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < i; j ++) {
                g[j][i] = g[i][j] = similar(wordList.get(i), wordList.get(j));
            }
        }

        // bi-directional-bfs
        int[] stepStart = new int[n];
        int[] stepEnd = new int[n];

        LinkedList<Integer> qStart = new LinkedList<>();
        LinkedList<Integer> qEnd = new LinkedList<>();

        qStart.addLast(begin);
        stepStart[begin] = 1;

        qEnd.addLast(end);
        stepEnd[end] = 1;

        while (!qStart.isEmpty() && !qEnd.isEmpty()) {
            int curStart = qStart.removeFirst();
            int curEnd = qEnd.removeFirst();

            for (int i = 0; i < n; i ++) {
                if (stepStart[i] == 0 && g[curStart][i]) {
                    stepStart[i] = stepStart[curStart] + 1;
                    qStart.addLast(i);
                }
            }

            for (int i = 0; i < n; i ++) {
                if (stepEnd[i] == 0 && g[curEnd][i]) {
                    stepEnd[i] = stepEnd[curEnd] + 1;
                    qEnd.addLast(i);
                }
            }

            // check intersection
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < n; i ++) {
                if (stepStart[i] != 0 && stepEnd[i] != 0) {
                    res = Integer.min(res, stepStart[i] + stepEnd[i] - 1);
                }
            }
            if (res != Integer.MAX_VALUE) {
                return res;
            }
        }
        return 0;
    }

    /**
     * Bi-directional BFS
     * No need to calculate all pairs similarity
     * Time Complexity: O(n*n)
     * Space Complexity: O(n)
     */
    public int ladderLength4(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // bi-directional-bfs
        LinkedList<String> qStart = new LinkedList<>();
        LinkedList<String> qEnd = new LinkedList<>();

        HashMap<String, Integer> stepStart = new HashMap<>();
        HashMap<String, Integer> stepEnd = new HashMap<>();

        qStart.addLast(beginWord);
        stepStart.put(beginWord, 1);

        qEnd.addLast(endWord);
        stepEnd.put(endWord, 1);

        while (!qStart.isEmpty() && !qEnd.isEmpty()) {
            String curStartWord = qStart.removeFirst();
            String curEndWord = qEnd.removeFirst();
            for (String word: wordList) {
                if (!stepStart.containsKey(word) && similar(word, curStartWord)) {
                    stepStart.put(word, stepStart.get(curStartWord) + 1);
                    qStart.addLast(word);
                }

                if (!stepEnd.containsKey(word) && similar(word, curEndWord)) {
                    stepEnd.put(word, stepEnd.get(curEndWord) + 1);
                    qEnd.addLast(word);
                }
            }

            // check intersection
            int res = Integer.MAX_VALUE;
            for (String word: wordList) {
                if (stepStart.containsKey(word) && stepEnd.containsKey(word)) {
                    res = Integer.min(res, stepStart.get(word) + stepEnd.get(word) - 1);
                }
            }

            if (res != Integer.MAX_VALUE) {
                return res;
            }
        }
        return 0;
    }

    private boolean similar(String word1, String word2){
        if (word1.length() != word2.length() || word1.equals(word2)) {
            throw new IllegalArgumentException();
        }
        int diff = 0;
        for (int i = 0; i < word1.length(); i ++)
            if (word1.charAt(i) != word2.charAt(i)) {
                diff ++;
                if (diff > 1) {
                    return false;
                }
            }
        return true;
    }

    public static void main(String[] args) {
        WordLadder wl = new WordLadder();

        ArrayList<String> wordList1 = new ArrayList<>(
                Arrays.asList("hot","dot","dog","lot","log","cog")
        );
        String beginWord1 = "hit";
        String endWord1 = "cog";
        System.out.println(wl.ladderLength1(beginWord1, endWord1, wordList1));   // 5

        ArrayList<String> wordList2 = new ArrayList<>(
                Arrays.asList("a","b","c")
        );
        String beginWord2 = "a";
        String endWord2 = "c";
        System.out.println(wl.ladderLength1(beginWord2, endWord2, wordList2));   // 2
    }

}
