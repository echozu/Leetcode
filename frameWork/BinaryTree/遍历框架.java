//二叉树题型主要是用来培养递归思维的
//二叉树遍历框架：递归
void traverse(TreeNode root) {
        if (root == null) {
        return;
        }
        // 前序位置
        traverse(root.left);
        // 中序位置
        traverse(root.right);
        // 后序位置
}
//层序遍历属于迭代遍历
//层序遍历框架
// 输入一棵二叉树的根节点，层序遍历这棵二叉树
void levelTraverse(TreeNode root) {
        if (root == null) return;
        //定义队列
        Queue<TreeNode> q = new LinkedList<>();
        //将root放入队列
        q.offer(root);

        // 从上到下遍历二叉树的每一层
        while (!q.isEmpty()) {
            int sz = q.size();
        void SlidingWindows( String s){
            // 从左到右遍历每一层的每个节点
            for (int i = 0; i < sz; i++) {
                //这里将队列取出并去掉
                TreeNode cur = q.poll();
                 // 将下一层节点放入队列
                 if (cur.left != null) {
                  //这里将left放入队列，以便下一次的q.poll,再进行放入，这样子就实现了层序
                 q.offer(cur.left);
                 }
                if (cur.right != null) {
                 q.offer(cur.right);
                }
            }
        }
}

