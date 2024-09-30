package Array;

//快慢指针
public class FastAndSlow {
    public static void main(String[] args) {
        removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
    }

    //todo:题目1；27.删除元素
    /*给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
    示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
    示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。*/
    public int removeElement(int[] nums, int val) {
        //使用快慢指针
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    //todo:题目2：删除有序数组的重复项
    /*输入：nums = [0,0,1,1,1,2,2,3,3,4]
    输出：5, nums = [0,1,2,3,4]
    解释：函数应该返回新的长度 5 ，
    并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素*/
    //定义两个指针？
    public static int removeDuplicates(int[] nums) {
        //快慢指针思想：让快的去指向下一个，慢的走慢一点，两个去比较，如果一样则不覆盖进新的原数组，不一样则覆盖
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                //先++，其实就是默认num[0]一定是再新数组里面的，不用去比较。
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    //todo:题目3：283 移动0
        /*给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
        请注意 ，必须在不复制数组的情况下原地对数组进行操作
        输入: nums = [0,1,0,3,12] 【1，0，0 3 12
        输出: [1,3,12,0,0]*/
    public void moveZeroes(int[] nums) {
        //定义两个指针，快指针遇到非0就停下，慢指针遇到0停下，然后去互换
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            //先让快指针走
            if (nums[fast] != 0) {
                //让慢指针一直去找到为0的位置，然后去替换
                while (slow < fast && nums[slow] != 0) {
                    slow++;
                }
                //当nums[fast]!=0和nums[slow]==0时，两个转换
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
            }
        }
    }
}

