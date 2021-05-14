package com.yufeng.data.structure.stack.leecode;

import java.util.Stack;

/**
 * 描述:
 *      有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。
 *      例如，""，"()"，"(())()" 和 "(()(()))" 都是有效的括号字符串。
 *      如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），
 *      其中 A 和 B 都是非空有效括号字符串。
 *
 *      给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
 *      对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
 *
 *
 * 示例 1：
 *    输入："(()())(())"
 *    输出："()()()"
 *    解释：
 *    输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
 *    删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
 *
 *
 * 示例 2：
 *    输入："(()())(())(()(()))"
 *    输出："()()()()(())"
 *    解释：
 *    输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
 *    删除每隔部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
 *
 *
 * 示例 3：
 *    输入："()()"
 *    输出：""
 *    解释：
 *    输入字符串为 "()()"，原语化分解得到 "()" + "()"，
 *    删除每个部分中的最外层括号后得到 "" + "" = ""。
 *  
 *
 * 提示：
 *    S.length <= 10000
 *    S[i] 为 "(" 或 ")"
 *    S 是一个有效括号字符串
 *
 */
public class LeetCode_1021 {


    /**
     * 执行结果：通过 显示详情
     *    执行用时：13 ms, 在所有 Java 提交中击败了13.93%的用户
     *    内存消耗：38.5 MB, 在所有 Java 提交中击败了63.83%的用户
     */
    public static String removeOuterParentheses(String S) {

        // 用于标记有效字符串的起始和结束位置, 用于取子字符串
        int start = 0;
        int end = 0;
        boolean flag = true;

        StringBuilder res = new StringBuilder();
        // 栈
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i ++) {

            Character parentheses = S.charAt(i);

            // 进入左括号的时候，记录start位置, 并且将flag设置为true, 最后入栈
            if (parentheses.equals('(')) {
                stack.push(parentheses);
                // 设置start位置, flag 设置为ture
                if (flag) {
                    start = i;
                    flag = false;
                }
            } else if (parentheses.equals(')')) {       // 右括号的时候
                // 出栈
                stack.pop();
                if (stack.isEmpty()) {
                    end = i;            // 记录结束位置
                    flag = true;
                    // 添加一组有效括号字符串: 从beginIndex开始取，到endIndex结束，从0开始数，其中不包括endIndex位置的字符
                    // 又是方法没记明白, String.substring(startIndex, endIndex), 是左开右闭的方法
                    res.append(S.substring(start + 1, end));
                }
            }
        }
        return res.toString();
    }


    public static void main(String[] args) {
        String str = "(()())(())(()(()))";
        System.out.println(LeetCode_1021.removeOuterParentheses(str));
    }

}
