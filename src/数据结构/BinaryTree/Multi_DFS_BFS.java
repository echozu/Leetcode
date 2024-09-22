package 数据结构.BinaryTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//多叉树的层序遍历/递归遍历
//多叉树：不只有两个节点：左、右节点，而是可能有三/四个几点，未定
public class Multi_DFS_BFS {
    public static void main(String[] args) {

    }

    class Node {
        int val;
        List<Node> child;
    }

    //递归的多叉树遍历框架
    void RecursionTraversal(Node root) {
        if (root == null) {
            return;
        }
        //
        //遍历：由于这里没有了左右子树，所以需要去遍历出集合去递归
        for (Node child : root.child) {
            RecursionTraversal(child);
        }
    }

    void RecursionTraversal2(Node root) {
        if (root == null) {
            return;
        }
        //前序遍历
        System.out.println(root.val);
        for (Node child : root.child) {
            RecursionTraversal(child);
        }
        //后序遍历
        System.out.println(root.val);
    }

    //层序遍历框架1
    void SequenceTraversal(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.println(cur.val);
            // 把所有子节点放进队列
            for (Node node : cur.child) {
                q.offer(node);
            }

        }
    }
    //层序遍历2：记录节点深度
    void SequenceTraversal2(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int depth=1;
        while (!q.isEmpty()) {
            int size = q.size();
            //去循环出当前层的，方便下面的depth++；
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                System.out.println("节点："+cur+"深度="+depth);
                for (Node node : cur.child) {
                    q.offer(node);
                }
            }
            depth++;
        }
    }
    //层序遍历3：适配不同权重边
    class State{
        int depth;
        Node node;
        //空参构造
        public State(Node node,int depth){
            this.node=node;
            this.depth=depth;

        }
    }
    void SequenceTraversal3(Node root){
        if (root == null) {
            return;
        }
        Queue<State> q = new LinkedList<>();
        // 记录当前遍历到的层数（根节点视为第 1 层）
        q.offer(new State(root,1));
        while (!q.isEmpty()) {
            State state = q.poll();
            int depth=state.depth;
            Node cura = state.node;
            // 访问 cur 节点，同时知道它所在的层数
            System.out.println(cura.val+"depth="+depth);
            //
            for (Node node : cura.child) {
                //默认每个树枝的权重为1
                //todo：由于depth在本次循环中，depth都是1，所以这次for循环中添加的state的dpeth都是2，所以也可以得出他们的层数是2
                q.offer(new State(node,depth+1));
            }
        }
    }
}
