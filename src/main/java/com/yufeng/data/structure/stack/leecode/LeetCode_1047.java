package com.yufeng.data.structure.stack.leecode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 *  
 * 示例：
 *   输入："abbaca"
 *   输出："ca"
 *   解释：
 *   例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
 *   之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *  
 *
 * 提示：
 *   1 <= S.length <= 20000
 *   S 仅由小写英文字母组成。
 *
 */
public class LeetCode_1047 {


    /**
     * 执行结果：通过 显示详情
     *    执行用时：30 ms, 在所有 Java 提交中击败了36.28%的用户
     *    内存消耗：39 MB, 在所有 Java 提交中击败了67.92%的用户
     */
    public static String removeDuplicates(String S) {

        Stack<Character> characterStack = new Stack<>();

        for (int i = 0; i < S.length(); i ++) {
            char c = S.charAt(i);
            if (!characterStack.isEmpty() && characterStack.peek().equals(c)) {
                characterStack.pop();
            } else {
                characterStack.push(c);
            }
        }

        //System.out.println(Arrays.toString(characterStack.toArray()));

        StringBuilder res = new StringBuilder();
        while (!characterStack.isEmpty()) {
            char c = characterStack.pop();
            res.append(c);
        }
//        for (int i = 0; i < characterStack.size(); i ++) {
//            res.append(characterStack.pop());
//        }
        return res.reverse().toString();
    }


    public static void main(String[] args) {

        String str = "abbaca";
        System.out.println(removeDuplicates(str));
    }
}
