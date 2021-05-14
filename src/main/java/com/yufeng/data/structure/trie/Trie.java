package com.yufeng.data.structure.trie;

import java.util.TreeMap;

/**
 * 描述:
 *      字典树
 * @author yufeng
 * @create 2019-08-22
 */
public class Trie {

    private class Node {

        public boolean isWord;                  // 判断当前节点是否是单词结尾

        public TreeMap<Character, Node> next;   // 每一个节点里面都包含着一个映射, 映射的key存储着字符, value指向下一个节点

        public Node() {
            this(false);
        }

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }
    }

    private Node root;      // 根节点

    private int size;       // 用于维护字符串的数量大小

    public Trie() {
        root = new Node();
        size = 0;
    }


    /**
     * 获得字典树中存储的单词数量
     */
    public int getSize() {
        return size;
    }


    /**
     * 向字典树中添加一个新的单词(添加元素的逻辑跟二叉树类似)
     * 练习题: 添加元素逻辑的递归写法?
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());        // 在map中添加一个新的key-value, key=c, value=new Node();
            }
            cur = cur.next.get(c);                  // 在当前节点的map中查找key=c的节点, cur指向新的查询出来的节点
        }
        if (!cur.isWord) {                          // 不会重复添加(类似集合)
            cur.isWord = true;
            size ++;
        }
    }


    /**
     * 查询单词是否在字典树中(循环遍历)
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }


    /**
     * 查询在字典树中是否有单词以prefix为前缀
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        int i = 0;
        while (i < prefix.length()) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
            i ++;
        }
        return true;
    }


//    public boolean isPrefix(String prefix) {
//        Node cur = root;
//        for (int i = 0; i < prefix.length(); i ++) {
//            char c = prefix.charAt(i);
//            if (cur.next.get(c) == null) {
//                return false;
//            }
//            cur = cur.next.get(c);
//        }
//        return true;
//    }

}
