package Labu.数据结构.BinaryTree;
//递归遍历
public class BFS {
    public static void main(String[] args) {

    }
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }
    void traversal(TreeNode root){
        if(root==null){
            return;
        }
        //这里·
        traversal(root.left);
        //这里
        traversal(root.right);
        //这里
        //todo：这三个位置放置不同的代码就会产生不同的效果，但是递归顺序是一定的，即先左后右！
    }
}
