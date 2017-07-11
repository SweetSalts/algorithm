import org.junit.Test;


import java.util.*;




/**
 * Created by PC on 2017/7/3.
 */
public class Solution {
    // 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    // 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    public boolean Find(int target, int [][] array) {
        int col = array.length;
        int row = array[0].length;
        int x = 0, y = col - 1;
        while(x < row && y >= 0){
            if(target > array[y][x])
                x++;
            else if(target < array[y][x])
                y--;
            else
                return true;
        }
        return false;
    }
    @Test
    public void test1(){
        int[][] a = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(Find(10,a));
    }
    // 请实现一个函数，将一个字符串中的空格替换成“%20”。
    // 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy
    public String replaceSpace(StringBuffer str) {
        char[] a = str.toString().toCharArray();
        StringBuffer newstr = new StringBuffer();
        for(int i = 0; i < a.length; i++){
            if(a[i] ==  ' ')
                newstr.append("%20");
            else
                newstr.append(a[i]);
        }
        return newstr.toString();
    }
    @Test
    public void test2(){
        StringBuffer str = new StringBuffer("we are happy");
        System.out.println(replaceSpace(str));
    }
    // 输入一个链表，从尾到头打印链表每个节点的值
    // 利用递归
    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }
    ArrayList<Integer> array = new ArrayList<>();//数组要放在外面
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode != null){
            printListFromTailToHead(listNode.next);
            array.add(listNode.val);
        }
        return  array;
    }
    @Test
    public void test3(){
        int[] a = {1,2,3,4,5};
        ListNode head = new ListNode(a[0]);
        ListNode temp = head;
        for(int i = 1; i < a.length; i++){
            temp.next = new ListNode(a[i]);
            temp = temp.next;
        }
        ArrayList<Integer> b = printListFromTailToHead(head);
        Iterator it = b.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    //汉诺塔，先将A上的n-1个通过C移动到B上，再将A上剩余的一个移到C上，最后将B上的n-1个移动到C上。
    //a是要移动的杆，b是中介杆，c是目标杆
    public void hanoi(int n,char a,char b,char c){
        if(n==1){
            System.out.println("将盘"+n+"从"+a+"移到"+c);
        }else{
            hanoi(n-1,a,c,b);
            System.out.println("将盘"+n+"从"+a+"移到"+c);
            hanoi(n-1,b,a,c);
        }

    }
    @Test
    public void test4(){
        hanoi(3,'A','B','C');
    }
    // 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
    // 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    // 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
    // 首先，根节点 是{ 1 }；pre[0]
    // 左子树是：前序{ 2,4,7 } ，中序{ 4,7,2 }；node.left(pre(1,i+1),in(0,i))
    // 右子树是：前序{ 3,5,6,8 } ，中序{ 5,3,8,6 }；node.right(pre(i+1,length),in(i+1,length))
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }
    }
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode node;
        if(pre.length==0 || in.length==0){
            return null;
        }else{
            node = new TreeNode(pre[0]);
            for (int i = 0; i < in.length; i++) {
                if(in[i]==pre[0]){
                    node.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,i+1),
                            Arrays.copyOfRange(in,0, i));
                    node.right = reConstructBinaryTree(Arrays.copyOfRange(pre,i+1,pre.length),
                            Arrays.copyOfRange(in,i+1,in.length));
                }
            }
        }
        return node;
    }
    //前序遍历
    public void preViewTree(TreeNode node){
        if(node == null){
            return;
        }
        System.out.print(node.val+" ");
        if(node.left != null){
            preViewTree(node.left);
        }
        if(node.right != null){
            preViewTree(node.right);
        }
    }
    //中序遍历
    public void inViewTree(TreeNode node){
        if(node == null){
            return;
        }
        if(node.left != null){
            inViewTree(node.left);
        }
        System.out.print(node.val+" ");
        if(node.right != null){
            inViewTree(node.right);
        }
    }
    //后序遍历
    public void postViewTree(TreeNode node){
        if(node == null){
            return;
        }
        if(node.left != null){
            postViewTree(node.left);
        }
        if(node.right != null){
            postViewTree(node.right);
        }
        System.out.print(node.val+" ");
    }
    @Test
    public void test5(){
        int[] pre = {1,2,4,7,3,5,6,8};
        int[] in = {4,7,2,1,5,3,8,6};
        TreeNode node = reConstructBinaryTree(pre,in);
        System.out.println(getNum(node));
        System.out.println(maxDepth(node));
        preViewTree(node);
        System.out.println();
        inViewTree(node);
        System.out.println();
        postViewTree(node);
    }
    //求树的结点个数
    public int getNum(TreeNode root){
        if(root == null){
            return 0;
        }
        else{
            return (getNum(root.left)+getNum(root.right)+1);
        }
    }
    //求树的深度
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        else{
            int i = maxDepth(root.left);
            int j = maxDepth(root.right);
            if(i>j)
                return i+1;
            return j+1;
        }
    }
    //二叉树的镜像
    public void Mirror(TreeNode root){
        //队列方式实现
        if(root == null)
            return;
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        TreeNode p = null;
        while(!q.isEmpty()){
            p = q.poll();
            if(p.left!=null || p.right!=null){
                TreeNode temp = p.left;
                p.left = p.right;
                p.right = temp;
            }
            if(p.left!=null)
                q.offer(p.left);
            if(p.right!=null)
                q.offer(p.right);
        }
        //递归方式实现
        if(root == null)
            return;
        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        Mirror(root.left);
        Mirror(root.right);
    }
    //链表的反转
    //递归
    public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode p = ReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
