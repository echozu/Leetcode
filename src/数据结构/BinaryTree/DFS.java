package 数据结构.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

//层序遍历：
public class DFS {
    public static void main(String[] args) {

    }
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }
    //方法一：最简单的
    void levelOrderTraverse(TreeNode root) {
        //定义一个队列
        Queue<TreeNode> q=new LinkedList<>();
        //将第一层根节点存储进队列中
        q.offer(root);
        //遍历
        //这个判断条件：如果队列为空了，则代表着利用q.poll()已经删完了，即放进去的节点已经遍历完了
        while (!q.isEmpty()){
            //拿到队头并删除改元素，即减少这一层的元素
            //todo：这一步十分之妙，即将得到的队头取出并删掉，那么下次要遍历的节点，即下一个cur，只会是跟他同一层的其他节点和其下一层的节点
            //      且下一层的节点一定是在同一层的节点遍历之后才会，因为顺序问题！
            TreeNode cur=q.poll();
            //输出
            System.out.println(cur);
            //如果这一层的左/右子节点有值，那么就得放进队列，即下一次的队列里面存储的就是第二层的节点了
            //todo:这里跟递归不一样的，即这里的left和right都是本节点的子节点，也就是下一层，不会涉及到下一层。
            if(cur.left!=null){
                q.offer(cur.left);
            }
            if(cur.right!=null){
                q.offer(cur.right);
            }
        }
    }

    //方法二：可以记录高度
    void levelOrderTraverse2(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        // 初始高度为1，因为根节点
        int depth=1;

        while (!q.isEmpty()){
            //记录队列的长度
            int size=q.size();
            //好了现在只有根据每一层的大小去遍历
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                System.out.println("当前节点："+cur+"当前高度："+depth);
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
            //一次循环结束了表示一层结束了
            depth++;
        }
    }
    //方法三：可以得到树的最终权重
    //注意：下面依旧认为每条树枝的权重是1，所以是+1，但是计算出来了树枝的总权重
    class State{
        TreeNode node;
        int depth;
        State (TreeNode node,int depth){
            this.node=node;
            this.depth=depth;
        }
        }
    void levelOrderTraverse3(TreeNode root) {
        Queue<State> q=new LinkedList<>();
        //默认根节点的权重为1
        q.offer(new State(root,1));
        int depth=1;
        while (!q.isEmpty()){
            State state = q.poll();
            //// 访问 cur 节点，同时知道它所在的层数
            System.out.println("当前总depth="+state.depth+"val="+state.node.val);
            if(state.node.left!=null){
                //因为设置每个树枝的权重都为1，所以是+1
                //在本次循环中，depth都是1，所以无论是这个left/下面的right加载的新的state的depth都是2，所以可以得出层数·是2
                q.offer(new State(state.node.left,state.depth+1));
            }
            if(state.node.right!=null){
                //因为设置每个树枝的权重都为1，所以是+1
                q.offer(new State(state.node.right,state.depth+1));
            }
        }
    }


    }

