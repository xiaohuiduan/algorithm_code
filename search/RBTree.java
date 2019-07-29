package search;

import java.sql.Blob;

/**
 * 红黑树
 * 哪吒真好看(*^__^*)
 * I want kill you 红黑树，fuck!!fuck!!
 * 要炸了，心态爆炸，mmp的红黑树。
 */
public class RBTree<Key extends Comparable<Key>,Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value value;
        private boolean color;
        private Node rightNode, leftNode;
        // 这棵子树中结点的个数，包括该节点
        private int N;

        public Node(Key key, Value value, boolean color, int n) {
            this.key = key;
            this.value = value;
            this.color = color;
            N = n;
        }
    }

    private Node root;

    /**
     * 获得改结点与其父节点之间链接的颜色
     *
     * @param node
     * @return
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    /**
     * 左旋转
     * @param h
     * @return 返回根节点
     */
    private Node rotateLeft(Node h) {
        Node x = h.rightNode;
        h.rightNode = x.leftNode;
        x.leftNode = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.leftNode)+size(h.rightNode)+1;
        return x;
    }

    /**
     * 右旋转
     * @param h
     * @return 返回根节点
     */
    private Node rotateRight(Node h) {
        Node x = h.leftNode;
        h.leftNode = x.rightNode;
        x.rightNode = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.leftNode)+size(h.rightNode)+1;
        return x;
    }


    /**
     * 颜色转换
     * @param h 该节点的左右链接都为红链接
     */
    private void changeColor(Node h){
        h.color = !h.color;
        h.leftNode.color = !h.leftNode.color;
        h.rightNode.color = h.rightNode.color;
    }

    /**
     * 红黑树的插入
     * @param key
     * @param value
     */
    public void put(Key key,Value value){
        if(key == null){
            return ;
        }
        root = put(root,key,value);
        // 进行颜色变换会将本身结点变红，而根节点必须保持黑色
        root.color = BLACK;
    }

    /**
     * 插入操作进行递归调用
     * @param h
     * @param key
     * @param value
     * @return
     */
    private Node put(Node h, Key key, Value value) {
        // 当h为叶子结点的左子树或者右子树
        if (h == null){
            return new Node(key,value,RED,1);
        }
        // 假如key小于结点的key,就转向左结点
        if (key.compareTo(h.key)<0){
            h.leftNode = put(h.leftNode,key,value);
        }
        // 转向右结点
        else if (key.compareTo(h.key)>0){
            h.rightNode = put(h.rightNode,key,value);
        }
        // 更新
        else{
            h.value = value;
        }
        // 若左边结点是黑色，右边结点是红色，则左旋转
        if (isRed(h.rightNode) && !isRed(h.leftNode)){
            h = rotateLeft(h);
        }
        // 若左子节点为红色且左子节点的左子节点也为红色则右旋转
        if (isRed(h.leftNode) && isRed(h.leftNode.leftNode)){
            h = rotateRight(h);
        }
        // 左右结点都为红色，则颜色转换
        if (isRed(h.leftNode) && isRed(h.rightNode)){
            changeColor(h);
        }
        h.N = size(h.leftNode)+size(h.rightNode) + 1;
        return h;
    }

    public boolean contains(Key key) {
        if (key !=null){
            Node node = root;
            while (node !=null){
                if (node.key.compareTo(key)>0){
                    node = node.leftNode;
                }else if (node.key.compareTo(key)<0){
                    node = node.rightNode;
                }else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 平衡树
     * @param h
     * @return
     */
    private Node fixUp(Node h){
        if (isRed(h.rightNode)){
            h = rotateLeft(h);
        }
        if (isRed(h.leftNode) && isRed(h.leftNode.leftNode)){
            h = rotateRight(h);
        }
        if (isRed(h.leftNode) && isRed(h.rightNode)){
            changeColor(h);
        }
        h.N = size(h.leftNode)+size(h.rightNode)+1;
        return h;
    }

    /**
     * 将红色结点移动到右边
     * @param h
     * @return
     */
    private Node moveRedRight(Node h){
        changeColor(h);
        // 假如结点的左结点的左结点为红色结点
        if (isRed(h.leftNode.leftNode)){
            // 进行右转
            h = rotateRight(h);
            // 然后改颜色
            changeColor(h);
        }
        return h;
    }

    /**
     * 将红色结点移动到左边
     * @param h
     * @return
     */
    private Node moveRedLeft(Node h){
        changeColor(h);
        if (isRed(h.rightNode.leftNode)){
            h.rightNode = rotateLeft(h.rightNode);
            h = rotateLeft(h);
            changeColor(h);
        }
        return h;
    }

    public void deleteMax(){
        if (root == null){
            return;
        }
        if (!isRed(root.leftNode) && !isRed(root.rightNode)) {
            root.color = RED;
        }
        root = deleteMax(root);
        // 删除之后root不为空，则将root的颜色变为黑色
        if (root != null){
            root.color = BLACK;
        }

    }

    public void deleteMin(){
        if (root == null){
            return;
        }
        if (!isRed(root.leftNode) && !isRed(root.rightNode)) {
            root.color = RED;
        }

        root = deleteMin(root);
        if (root != null){
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node h) {
        if (h.leftNode == null) {
            return null;
        }

        if (!isRed(h.leftNode) && !isRed(h.leftNode.leftNode)){
            h = moveRedLeft(h);
        }
        h.leftNode = deleteMin(h.leftNode);
        return fixUp(h);
    }

    private Node deleteMax(Node h) {
        // 假如结点的左边是红色结点
        if (isRed(h.leftNode)){
            // 右旋转
            h = rotateRight(h);
        }
        // 如果右边为空的，则代表以及达到最大的结点
        if (h.rightNode == null){
            return null;
        }
        // 假如结点的右子节点为是黑色，右子节点的左子节点是黑色
        // 在这种情况下，我们进行右旋转没办法得到将红色的结点转到右边来
        // 所以我们执行moveRedRight并在里面创造红色的结点
        if (!isRed(h.rightNode) && !isRed(h.rightNode.leftNode)){
            h = moveRedRight(h);
        }

        h.rightNode = deleteMax(h.rightNode);
        return fixUp(h);
    }

    public void delete(Key key){
        if (key == null){
            return;
        }
        if (!isRed(root.leftNode) && !isRed(root.rightNode)) {
            root.color = RED;
        }
        root = delete(root,key);
        if (root != null){
            root.color = BLACK;
        }

    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key)<0){
            if (!isRed(h.leftNode) && !isRed(h.leftNode.leftNode)){
                h = moveRedLeft(h);
            }
            h.leftNode = delete(h.leftNode,key);
        }
        // 假如key比Node的key要大，或者相等
        else{
            // 左子节点为红色，则进行右旋转，这样能够将红色结点向右集中
            if (isRed(h.leftNode)){
                // 通过右转能够将红色结点上升
                h = rotateRight(h);
            }
            // 这一步中，假如h的右结点为空，则h为叶子结点（此叶子结点并不代表NULL结点）
            // 因为假如有左子节点的话，那么左子节点一定是红色（因为右子节点为空），那么在上面的一个if语句中h已经被右旋转到了右子节点
            // 且h必定为红色的结点，这个时候我们就可以直接删除
            if (key.compareTo(h.key) == 0 && h.rightNode == null){
                return null;
            }


            if (!isRed(h.rightNode) && !isRed(h.rightNode.leftNode)){
                h = moveRedRight(h);
            }

            if (key.compareTo(h.key) == 0){
                // 找到h的后继结点，然后交换key和value，然后就可以删除最小节点了
                Node x = min(h.rightNode);
                h.key = x.key;
                h.value = x.value;
                h.rightNode = deleteMin(h.rightNode);
            }
            else{
                h.rightNode = delete(h.rightNode,key);
            }
        }
        return fixUp(h);
    }

    /**
     * 找后继结点
     * @param x
     * @return
     */
    private Node min(Node x) {
        if (x.leftNode == null) {
            return x;
        }
        else{
            return min(x.leftNode);
        }
    }


}