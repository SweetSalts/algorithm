import com.sun.deploy.util.StringUtils;
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
    //合并两个有序链表
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode newnode = head;
        if(list1==null)
            return list2;
        if(list2==null)
            return list1;
        if(list1==null && list2==null)
            return null;
        while(list1!=null && list2!=null){
            if(list1.val<list2.val){
                newnode.next = list1;
                list1 =list1.next;
            }else{
                newnode.next = list2;
                list2 = list2.next;
            }
            newnode = newnode.next;
        }
        if(list1!=null){
            newnode.next = list1;
        }
        if(list2!=null){
            newnode.next = list2;
        }
        return head.next;
    }
    //输入两棵二叉树A，B，判断B是不是A的子结构
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(root2==null || root1==null)
            return false;
        return IsSubtree(root1,root2) || HasSubtree(root1.left,root2) || HasSubtree(root1.right,root2);
    }
    public boolean IsSubtree(TreeNode root1,TreeNode root2) {
        if(root2 == null)
            return true;
        if(root1 == null)
            return false;
        return root1.val==root2.val && IsSubtree(root1.left,root2.left) && IsSubtree(root1.right,root2.right);
    }
    //顺时针打印数组输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
    // 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
    // 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int column = matrix[0].length;
        int row = matrix.length;
        int start = 0;
        ArrayList<Integer> b = new ArrayList();
        //只有一行
        if (row == 1) {
            for (int i = 0; i < column; i++) {
                b.add(matrix[0][i]);
            }
            return b;
        }
        //只有一列
        if (column == 1) {
            for (int i = 0; i < row; i++) {
                b.add(matrix[i][0]);
            }
            return b;
        }
        while (column > 2 * start && row > 2 * start) {
            int x = column - 1 - start;
            int y = row - 1 - start;
            //从左到右打印
            for (int i = start; i <= x; i++) {
                b.add(matrix[start][i]);
            }
            //从上到下打印
            if (start < y) {
                for (int i = start + 1; i <= y; i++) {
                    b.add(matrix[i][x]);
                }
            }
            //从右往左打印
            if (start < x && start < y) {
                for (int i = x - 1; i >= start; i--) {
                    b.add(matrix[y][i]);
                }
            }
            //从下往上打印
            if (start < x && start < y - 1) {
                for (int i = y - 1; i >= start + 1; i--) {
                    b.add(matrix[i][start]);
                }
            }
            start++;
        }
        return b;
    }

    //输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
    // 假设压入栈的所有数字均不相等。
    // 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，
    // 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack stack = new Stack();
        int i = 0;
        for(int j=0;j<pushA.length;j++){
            stack.push(pushA[j]);
            while(!stack.empty() && stack.peek().equals(popA[i])){
                stack.pop();
                i++;
            }
        }
        return stack.empty();
    }
    //输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
    // 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence ==null||sequence.length ==0){
            return false;
        }
        int indexleft =0;//左根的初始值
        int indexright =sequence.length-1;//右根的初始值
        int root = sequence[indexright];
        while(sequence[indexleft]<root){
            indexleft++;//找到左子树的边界值

        }
        while(indexright>indexleft){
            if(sequence[indexright]<root)
                return false;//找到不满足右节点特点的节点，返回false
            indexright--;
        }
        //判断是否满足后序遍历的结果，左边都得小于根，右边都得大于根
        boolean left=true;
        boolean right=true;
        if(indexleft>0)
            left =VerifySquenceOfBST(Arrays.copyOfRange(sequence,0,indexleft));//这东西是前闭后开的
        if(indexright<sequence.length-1)
            right=VerifySquenceOfBST(Arrays.copyOfRange(sequence,indexleft,sequence.length-1));
        return left&&right;
    }
    //输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
    // 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> a = new ArrayList<Integer>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null)
            return arrays;
        a.add(root.val);
        target -= root.val;
        if(target==0 && root.left==null && root.right==null)
            arrays.add(new ArrayList<Integer>(a));
        FindPath(root.left,target);
        FindPath(root.right,target);
        a.remove(a.size()-1);
        return arrays;
    }
    //复杂链表的复制
    //输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
    // 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
    public RandomListNode Clone(RandomListNode pHead){
        if(pHead == null)
            return null;
        RandomListNode cur = pHead;
        while(cur != null){
            RandomListNode newnode = new RandomListNode(cur.label);
            newnode.next = cur.next;
            cur.next = newnode;
            cur = newnode.next;
        }
        cur = pHead;
        while(cur!=null){
            if(cur.random!=null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        cur = pHead;
        RandomListNode cloneHead = pHead.next;
        RandomListNode temp = null;
        while(cur.next!=null){
            temp = cur.next;
            cur.next = temp.next;
            cur = temp;
        }
        return cloneHead;
    }
   //输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
    // 要求不能创建任何新的结点，只能调整树中结点指针的指向。
    //采用递归
   public TreeNode Convert(TreeNode pRootOfTree) {
       if(pRootOfTree == null)
           return null;
       if(pRootOfTree.left==null && pRootOfTree.right==null)
           return pRootOfTree;
       TreeNode left = Convert(pRootOfTree.left);
       TreeNode p = left;
       //p指向左子树最右边的节点
       while(p!=null && p.right!=null){
           p = p.right;
       }
       if(left!=null){
           p.right = pRootOfTree;
           pRootOfTree.left = p;
       }
       TreeNode right = Convert(pRootOfTree.right);
       if(right!=null){
           pRootOfTree.right = right;
           right.left = pRootOfTree;
       }
       return left!=null?left:pRootOfTree;
   }
   //输入一个字符串,按字典序打印出该字符串中字符的所有排列。字符串可能有重复
    // 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
    //思路：将字符串的第一位和后面的每一位进行交换，交换后将后面的字符串再进行全排列，如此递归，直到递归到数组中的最后一位
   public ArrayList<String> Permutation(String str) {
       ArrayList<String> array = new ArrayList<String>();
       if(str!=null || str.length()>0){
           PermutationHelper(str.toCharArray(),0,array);
           Collections.sort(array);
       }
       return array;

   }
    public void PermutationHelper(char[] s,int i,ArrayList array){
        if(i == s.length-1){
            String str = String.valueOf(s);
            //查看字符串是否有重复
            if(!array.contains(str))
                array.add(str);
        }else{
            for(int j = i; j < s.length; j++){
                swap(s,i,j);
                PermutationHelper(s,i+1,array);
                swap(s,i,j);
            }
        }
    }
    public void swap(char[] s,int i,int j){
        char temp;
        temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    //动态规划求连续子向量最大和
    public int FindGreatestSumOfSubArray(int[] array) {
        //int[] m = new int[array.length];
        //for(int j = 0; j < array.length; j++ ){
        //    int max = array[j];
        //    int sum = max;
        //    for(int i = j+1; i < array.length; i++){
        // 	    sum = sum + array[i];
        //        if(sum > max)
        //           max = sum;
        //    }
        //    m[j] = max;
        //}
        //Arrays.sort(m);
        //return m[m.length-1];
        int res = array[0];
        int max = array[0];
        for(int i=1;i<array.length;i++){
            max = Math.max(max+array[i],array[i]);
            res = Math.max(max,res);
        }
        return res;
    }
    //求出1到n之间，1出现的次数，包括各位中所有的1.
    //思路来自：http://blog.csdn.net/yi_afly/article/details/52012593
    public int NumberOf1Between1AndN_Solution(int n) {
        if(n < 1)
            return 0;
        int count = 0;
        int round = n;
        int base = 1;
        while(round > 0){
            int weight = round % 10;
            round/=10;
            count += round * base;
            if(weight == 1)
                count += (n%base) + 1;
            if(weight > 1)
                count += base;
            base *= 10;
        }
        return count;
    }
    //输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
    // 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
    //若ab > ba 则 a > b,若ab < ba 则 a < b，若ab = ba 则 a = b；
    // 解释说明：比如 "3" < "31"但是 "331" > "313"，所以要将二者拼接起来进行比较
    public String PrintMinNumber(int [] numbers) {
        String[] str = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, (o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s1.compareTo(s2);
        });
        StringBuilder s = new StringBuilder();
        for(String a : str){
            s.append(a);
        }
        return s.toString();
    }
    // 把只包含因子2、3和5的数称作丑数（Ugly Number）。
    // 例如6、8都是丑数，但14不是，因为它包含因子7。
    // 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
    // k[0]:1
    // 1*2,1*3,1*5 min:2,k[1]:2;
    // 2*2,1*3,1*5 min:3,k[2]:3;
    // 2*2,2*3,1*5 min:4,k[3]:4;
    // 3*2,2*3,1*5 min:5,k[4]:5;
    public int GetUglyNumber_Solution(int index) {
        if(index <= 0)
            return 0;
        int[] k = new int[index];
        k[0] = 1;
        int x=0,y=0,z=0;
        for(int i = 1; i < index; i++){
            int a = k[x] * 2;
            int b = k[y] * 3;
            int c = k[z] * 5;
            int min = Math.min(a,Math.min(b,c));
            k[i] = min;
            if(min == k[x] * 2)
                x++;
            if(min == k[y] * 3)
                y++;
            if(min == k[z] * 5)
                z++;
        }
        return k[index-1];
    }

}
