package xiaoshiyilang.datastructure.tree;

/**
 * @author zhang.cheng
 * @description 二叉树
 * @date 2019/12/11 20:09
 */
public class BinaryTreeDemo {

    /**
     * 使用 前序，中序和后序对下面的二叉树进行遍历
     * 二叉树创建思路(联想树的结构)：
     * 1.先创建树的节点类xxxNode,
     * 1.1、包括树的每个节点的编号no和对应的值value或name
     * 1.2、包括每个节点的左子节点和右子节点
     * 1.3、创建编号和value的构造方法，便于创建每一个节点 并重写toString方法
     * 2.创建二叉树 有一个根节点root就OK
     * <p>
     * 3、三种遍历方式的做法
     * 3.1、前序遍历：
     * 先输出当前节点即root节点、如果左子节点不为空，则递归前序遍历、如果右子节点不为空，则递归前序遍历；
     * 3.2、中序遍历：
     * 如果左子节点不为空，则递归中序遍历、输出当前节点即root节点、如果右子节点不为空，则递归中序遍历；
     * 3.3、后序遍历：
     * 如果左子节点不为空，则递归后序遍历、如果右子节点不为空，则递归后序遍历、输出当前节点即root节点；
     * <p>
     * 总结：三种遍历方式关键是父节点的输出时间，最先输出则是前序；第二个输出则是中序，最后输出则是后序；
     */

    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        HeroNode node6 = new HeroNode(6, "不知道666");
        HeroNode node7 = new HeroNode(7, "不知道777");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node6);
        node2.setRight(node7);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        binaryTree.preOrder();
    }

}

class BinaryTree {

    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

}


/**
 * 创建树的节点
 */
class HeroNode {
    //树节点编号
    private Integer no;
    //树节点的值
    private String name;
    //左子节点
    private HeroNode left;
    //右子节点
    private HeroNode right;

    public HeroNode(Integer no, String name) {
        this.no = no;
        this.name = name;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            //递归前序遍历
            this.left.preOrder();
        }
        if (this.right != null) {
            //递归前序遍历
            this.right.preOrder();
        }
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}

