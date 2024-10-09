package Array;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    //todo：框架
    // 滑动窗口算法伪码框架
/*    void slidingWindow(String s) {
        // 用合适的数据结构记录窗口中的数据，根据具体场景变通
        // 比如说，我想记录窗口中元素出现的次数，就用 map
        // 如果我想记录窗口中的元素和，就可以只用一个 int
        Object window = ...

        int left = 0, right = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s[right];
            window.add(c)
            // 增大窗口
            right++;
            // 进行窗口内数据的一系列更新
        ...

            // *** debug 输出的位置 ***
            // 注意在最终的解法代码中不要 print
            // 因为 IO 操作很耗时，可能导致超时
            printf("window: [%d, %d)\n", left, right);
            // ***********************

            // 判断左侧窗口是否要收缩
            while (left < right && window needs shrink) {
                // d 是将移出窗口的字符
                char d = s[left];
                window.remove(d)
                // 缩小窗口
                left++;
                // 进行窗口内数据的一系列更新
            ...
            }
        }
    }*/

    //todo：题目1：204 长度最小的数组
    /*给定一个含有 n 个正整数的数组和一个正整数 target 。
    找出该数组中满足其总和大于等于 target 的长度最小的
    子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    输入：target = 7, nums = [2,3,1,2,4,3]
    输出：2
    解释：子数组 [4,3] 是该条件下的长度最小的子数组
    * */
    //todo：滑动窗口的重点（双指针）：如何动态移动初始位置
    //      i：指向起始位置，j指向改窗口的终止位置，i-j之间形成一个窗口/集合。
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < nums.length; j++) {
            //todo:这里从0到j去求和，求得该窗口的和
            sum += nums[j];
            //如果sum>=targer时，进行初始位置的移动，使得求出最小的窗口数组
            //todo:为什么的while？：因为while可以在当窗口大于target时一直去缩小数组长度，直到<target
            //如：1，1，1，1，1，100,target=100
            while (sum >= target) {
                //先求出此时的窗口长度：j-i+1，然后去比较，看看哪个更小
                res = Math.min(res, j - i + 1);
                //减少sum的值
                sum -= nums[i];
                //todo：初始位置的移动
                i++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    //todo：题目2：904 水果成篮
    /*你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
    你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
    你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
    你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
    一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
    给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
    输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
    输出：5
    解释：可以采摘 [1,2,1,1,2] 这五棵树。*/
    public int totalFruit(int[] fruits) {
        int left = 0;
        int right = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (; right < fruits.length; ++right) {
            //这里去判断是否存在该种类，如果存在则数量+1，如果不存在则添加
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            //如果map的key超过3个，就应该去移动起始位置了，使得只剩下两个种类
            while (map.size() > 2) {
                //这里去根据起始位置left去得到fruits起始位置的种类，然后根据这个去查map得到数量，然后去-1，
                //那么这里能不能只减少同一种类的呢？不行，因为如果真的要重新开始，那么就必须找到一个新的起始点，使得不包含前面的其中某一种树
                //如：1，2，1，2，1，3： 这里刚开始是只包含1和2，但是当遇到3时，如果要去计算新的种类树，不能说只先删去1，因为如果不删去第二个的2
                //那么即证明要从第二个2去开始采摘，那么仍然会采摘到第三个1，所以必须得遇到其中一个种类的数量为0，这样才有可能创造出新的数
                map.put(fruits[left], map.get(fruits[left] - 1));
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                //todo：这里去移动起始位置
                left++;
            }
            res = Integer.max(res, right - left + 1);
        }
        return res;
    }

    public int totalFruit2(int[] fruits) {
        //先弄一个窗口
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < fruits.length) {
            //将东西先装入篮子
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            //如果超出两种了，那么就得移动窗口了
            while (map.size() > 2) {
                //按照框架，是将这个移出去咯
                //不能单纯移出去，要将改值减少为0移出去才可，所以得循环
                map.put(fruits[left], map.get(fruits[left])-1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                //得到当前的值
                left++;
            }
            //更新一下
            res = Integer.max(res, right - left + 1);
            //todo:这里要先让其先更新res再自增right，为什么呢？
            //因为如果不是的话那么在第一次循环中，
            right++;
        }
        return res;
    }


    //todo:76. 最小覆盖子串
    /*给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
    注意：
    对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
    如果 s 中存在这样的子串，我们保证它是唯一的答案。*/
    /*输入：s = "ADOBECODEBANC", t = "ABC"
    输出："BANC"
    解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。*/
    public String minWindow(String s, String t) {
        return null;
    }
}
