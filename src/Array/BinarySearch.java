package Array;
//二分搜索:todo：前提：数组有序


public class BinarySearch {
    public static void main(String[] args) {
        generateMatrix(3);
    }
    //寻找左右边界
    private static boolean check(int x) {
        /* ... */
        return true;
    }

    //todo:整数二分
    //使用左闭右开写法
    //todo：向左搜索,所以满足时变得是右
    static int leftBinarySearch(int[] arr, int left, int right) {
        //todo：这里的left<right也是固定的，即二分结束的条件就是left>=right，其实这也表示了二分完了
        while (left < right) {
            // 定义mid
            int mid = arr[left + right >> 1];
            if (check(mid)) {
                //如果满足性质，因为要找到左边第一个满足的，所以是向左搜索，所以此时缩小范围，right=mid
                right = mid;
            } else {
                //如果判断不成立，因为要找到左边第一个满足的，则应该扩大范围，向右找第一个满足的，所以是left=mid+1
                left = mid + 1;
            }
        }
        //todo:这里的left其实即为所求，即不断二分，最好找的的位置就是left所在位置
        return left;
    }

    //todo：右边界：想右搜索，所以满足时变得是左
    //寻找右侧最后一个满足条件的数
    static int rightBinarySearch(int[] arr, int left, int right) {
        while (left < right) {
            //todo：如果下面是left=mid则这里有+1,所以下面有-1
            int mid = arr[left + right + 1 >> 1];
            if (check(mid)) {
                //如果满足条件，则需要向右搜索，找寻最后一个满足的，所以应该是left=mid
                left = mid;
            } else {
                //如果不满足，则需要向左搜索，找寻最后一个满足的
                right = mid - 1;
            }
        }
        return left;
    }

    /*
    todo：题目1：704. 二分查找
    给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
    写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
    */
    //两种版本
    //todo：1. 定义的区间是左闭右闭
    //// 定义target在左闭右闭的区间里，[left, right]
    public int search(int[] nums, int target) {
        //左闭,定义索引为0
        int left = 0;
        //右闭，因为索引nums.length-1是可以取到的，所以是右闭
        int right = nums.length - 1;
        //因为是左闭右闭，所以这里可以取到left=right
        while (left <= right) {
            int mid = left + ((right - left) / 2);// todo:防止溢出 等同于(left + right)/2
            if (nums[mid] < target) {
                //因为这里是左闭右闭，所以这里是mid+1，因为mid已经取到了
                left = mid + 1;     // target 在右区间，所以[middle + 1, right]
            } else if (nums[mid] > target) {
                //因为这里是左闭右闭，所以这里是mid-1，因为mid已经取到了
                right = mid - 1;    // target 在左区间，所以[left, middle - 1]
            } else {
                //如果找到则返回下标mid
                return mid;
            }
        }
        //如果找不到则返回-1；
        return -1;
    }

    //todo：2.定义的区间是左闭右开
    public int search2(int[] nums, int target) {
        int left = 0;
        //因为这里假设的区间是左闭右开，所以这里的right应该是取不到的，所以是nums.length
        int right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) / 2);// todo:防止溢出 等同于(left + right)/2
            if (nums[mid] < target) {
                //因为是左开右闭的，所以这里的left更新要为+1，因为mid已经取到过了
                left = mid + 1;
            } else if (nums[mid] > target) {
                //因为是右开，所以right=mid没有取到，所以这里不要-1
                right = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /* //todo:题目2：35 搜索插入位置
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
请必须使用时间复杂度为 O(log n) 的算法。
    * */
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) / 2);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                //因为是右开，所以right=mid没有取到，所以这里不要-1
                right = mid;
            } else {
                return mid;
            }
        }
        // 分别处理如下四种情况
        // 目标值在数组所有元素之前 [0,0)
        // 目标值等于数组中某一个元素 return middle

        // 在数组中：因为 [left, right) ，return right 即可【即因为right是没取到的
        //如果数组 1 3 5 6 target=2，因为right是没取到索引1，所以最后2的索引就是1
        // 在数组所有元素之后：因为[left, right)，因为是右开区间，所以 return right
        return right;
    }

    /*todo：题目3：34. 在排序数组中查找元素的第一个和最后一个位置
    给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
    如果数组中不存在目标值 target，返回 [-1, -1]。
    你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。*/
    public int[] searchRange(int[] nums, int target) {
        // 将返回值放到一个长度为2的数组中
        int[] res = new int[2];
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // 先LBS（下界搜索，即寻找第一个出现目标target的第一个索引的位置】，找到左边界【模板代码1】
        //left 和 right 相遇时，left 就是 target 的左边界。
        while (left < right) {
            int mid = left + right >> 1;
            //思考变化条件：如果是ture则变化条件是r=mid，所以上面需要+1
            //todo：关键是这个判断条件，而不是right=mid，这个可以根据判断条件去得出
            // 因为找的是左边界，所以是>=，向左搜索，
            //      这意味着我们需要尽可能地向左扩展搜索范围，直到我们不能再向左移动为止。
            //      因此，我们的判断条件是 nums[mid] >= target
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 如果找到的答案不等于目标值，表示数组无解，则默认返回-1 -1
        if (nums[left] != target) {
            return new int[]{-1, -1};
        }
        res[0] = left;
        left = 0;
        right = n - 1;
        // 再RBS（上界搜索，即寻找最后一个出现target的所有位置），找到右边界【模板代码2】
        while (left < right) {
            int mid = left + right + 1 >> 1;
            //todo：因为是找右边界，所以应该是向右搜索，那如何向右搜索呢？就是让判断条件为：nums[mid] <= target
            //      这样子保证在更新时是去向右去缩小规模
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1; // 有加必有减
            }
        }
        res[1] = left;
        return res;
    }

    //todo:题目4：977 有序数组的平方
    /*给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
    * 输入：nums = [-4,-1,0,3,10]
    输出：[0,1,9,16,100]
    解释：平方后，数组变为 [16,1,0,9,100]
    排序后，数组变为 [0,1,9,16,100]
    * */
    //双指针做法
    public int[] sortedSquares(int[] nums) {
        int [] result=new int[nums.length];
        int k=nums.length-1;
        int i=0;
        int j=nums.length-1;
        //todo：这里是<=，为什么是=？因为当i=j没有进入循环时，那么他们指向的数字也将不会进入新数组
        while (i<=j){
            //todo：判断谁比较大，因为根据题目，是有序数组，所以平方后最大的只会出现在最左/最右
            if(nums[i]*nums[i]<nums[j]*nums[j]){
                result[k]=nums[j]*nums[j];
                j--;
            }else {
                result [k]=nums[i]*nums[i];
                i++;
            }
            k--;
        }
        return result;
    }
    //todo：题目5：59.螺旋矩阵II
    /*给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
    示例 1：
    输入：n = 3
    输出：[[1,2,3],[8,9,4],[7,6,5]]*/
    public static int[][] generateMatrix(int n) {
        //todo:因为定义每一圈的起始点是变化的，所以得去定义，方便下面的边界
    int x=0;
    int y=0;
    int num[][]=new int[n][n];
    //定义
    int off=1; //todo：这里也是关键，因为下面的j的判断条件是变化的，所以得设定这个
    int i,j;
    int count=1;
    int loop=1; //圈数
    //为什么是n/2？
        //完整的绕圈数只有n/2
    while (loop<=n/2){
        //i,j坐标：i表示横坐标，j表示纵坐标
        //这里设定的是左闭右开，即每一行/竖的最后一位都交给下一竖/行去处理
        //todo：圈的顶部
        for (j=y;j<n-off;j++){
            num[x][j]=count++;
        }
        //圈的右列
        for (i=x;i<n-off;i++){
            num[i][j]=count++;
        }
        //这里表示一圈的下面那一行，此时的i=n-off；j=n-off；
        //然后这里横坐标不变，纵坐标减少
        //todo：这里为什么不能是j>0,i>0呢，因为这里的>0只符合第一圈，第二圈就不适用了
        //      也就是说，这里的判断条件0的位置是会变的，所以得用变量！也就有了定义x，y
        //圈的底部
        for (;j>y;j--){
            num[i][j]=count++;
        }
        //圈的左列
        for (;i>x;i--){
            num[i][j]=count++;
        }
        off++;
        x++;
        y++;
        loop++;
    }
    //当n是奇数时，因为n/2是向下取整，也就是会差最中间的那个数
        //[[1,2,3],
        // [8,9,4], 会差这个9 因为loop>n/2排除掉这个了
        // [7,6,5]]
        //todo:判断奇数的方法：% 如果n%2==0，那么n就是偶数
        if(n%2!=0){
            num[x][y]=count++;
        }
    return num;
    }
}
