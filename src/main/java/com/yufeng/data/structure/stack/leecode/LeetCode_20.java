package com.yufeng.data.structure.stack.leecode;

import java.util.Stack;

/**
 * 有效的括号
 *     给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 *
 * 有效字符串需满足：
 *     左括号必须用相同类型的右括号闭合。
 *     左括号必须以正确的顺序闭合。
 *     注意空字符串可被认为是有效字符串。
 *
 *
 * 示例 1:
 *    输入: "()"
 *    输出: true
 *
 *
 * 示例 2:
 *    输入: "()[]{}"
 *    输出: true
 *
 *
 * 示例 3:
 *    输入: "(]"
 *    输出: false
 *
 *
 * 解题思路: 使用 Stack 来做校验
 *    1. 当输入的元素是 ( [ { 时, 就推入栈;
 *    2. 当输入的元素是 ) ] } 时, 就推出栈;
 *    3. 可以是 push、push、pop、pop、push、pop这种形式, 也可以是 push、push、push、pop、pop、pop这种，
 *       重点是推入和推出是就近匹配关系! 且完全匹配后stack为空!
 */
public  class LeetCode_20 {


    /**
     * 执行结果：通过显示详情
     *    执行用时：2 ms, 在所有 Java 提交中击败了77.21%的用户
     *    内存消耗：36.8 MB, 在所有 Java 提交中击败了25.20%的用户
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 当 ']' 时会出现这种情况，直接return false!
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(')
                    return false;
                if (c == ']' && topChar != '[')
                    return false;
                if (c == '}' && topChar != '{')
                    return false;
            }
        }
        // 当前面全部匹配并且推出栈中的元素后，还需保证stack是空的，没有多余元素
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        String s1 = "[]{}()[]";
        System.out.println(isValid(s1));

        String s2 = "[]{}()[]";
        System.out.println(isValid(s2));

    }
}