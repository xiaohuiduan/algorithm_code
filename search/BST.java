 package search;

 /**
 * 二叉查找树
 * 人生当苦无妨，良人当归即好。
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>,Value> extends AbstractST <Key,Value> {
    /**
     * 根节点
     */
    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left,right;
        // 以该结点为根的子树中结点总数,包括该节点
        private int N;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.N = n;
        }
    }
    @Override
    public void put(Key key, Value value) {
        if(key == null){
            return;
        }
        // 假如二叉树是空的
        if (root == null){
            root = new Node(key,value,1);
            return;
        }
        int addN = 1;
        if (containsNode(key)){
            addN = 0;
        }
        int temp;
        Node node = root;
        while(true){
             temp = node.key.compareTo(key);
             // key相等则进行更新
             if (temp == 0){
                node.value = value;
                return;
             }
             // 插入的key比node的key小，转向左子树
             if (temp>0){
                 node.N += addN;
                 if (node.left == null){
                    node.left = new Node(key,value,1);
                 }
                 node = node.left;
             }else {
                 node.N += addN;
                 if (node.right == null){
                     node.right = new Node(key,value,1);
                 }
                 node = node.right;
             }
        }

    }

    /**
     * 通过key获得value
     *
     * @param key
     * @return 若不存在则返回空
     */
    @Override
    public Value get(Key key) {
        // 默认返回值为空
        Value resultValue = null;

        // 假如key为空或则二叉树为空
        if (key == null){
            return resultValue;
        }

        Node node = root;
        int temp;
        // 可以使用递归或者非递归实现
        while (node!= null){
            temp=node.key.compareTo(key);
            // 假如key小于结点的key,则转向左子树
            if (temp>0){
                node = node.left;
            }else if (temp < 0){ // 转向右子树
                node = node.right;
            }else {
                resultValue = node.value;
                break;
            }
        }
        return resultValue;
    }


     /**
      * 使用复制删除
      * @param node 被删除的结点
      */
     private Node copyDelete(Node node){
         if (node.right == null){
             return node.left;
         }else if(node.left == null){
             return node.right;
         }
         // 既有左子树又有右子树
         else {
            Node tempNode = node.left;
            while(tempNode.right != null){
                tempNode.N --;
                tempNode = tempNode.right;
             }
            tempNode.right = node.right;
            tempNode.left = (node.left==tempNode?tempNode.left:node.left);
            tempNode.N = size(tempNode.left) + size(tempNode.right)+1;
            return tempNode;
         }
     }


     /**
     * 使用合并删除
     * @param node
     * @return 返回的结点为已经进行删除后新的结点
     */
    public Node mergeDelete(Node node){
        // 假如没有右子树
        if (node.right == null){
            return node.left;
        }
        // 假如没有左子树
        else if (node.left == null){
            return node.right;
        }
        // 既有右子树也有左子树
        else {
            Node tempNode = node.left;
            // 转向左子树中最右边的结点
            while (tempNode.right != null){
                tempNode= tempNode.right;
                tempNode.N += size(node.right);
            }
            // 将删除结点的右子树放入正确的位置
            tempNode.right = node.right;
            node.left.N += size(node.right);
            return node.left;
        }
    }


    public Value deleteByCopy(Key key){
        // 如果不包含key
        if (!containsNode(key)){
            return null;
        }
        Node node = root;
        Node preNode = node;
        int temp;
        while (true){
            node.N --;
            temp = node.key.compareTo(key);
            // 假如key小于结点的key,则转向左子树
            if (temp > 0) {
                preNode = node;
                node = node.left;
            } else if (temp < 0) { // 转向右子树
                preNode = node;
                node = node.right;
            } else {
                break;
            }
        }

        // 删除结点的返回值
        Value value = node.value;

        if (node == root){
            root = copyDelete(node);
        }
        // 假如删除的是父节点的左边的结点
        else if (preNode.left!=null && preNode.left == node){
            preNode.left = copyDelete(node);
        }else {
            preNode.right = copyDelete(node);
        }
        return value;
    }
    /**
     * 进行删除
     * @param key
     * @return 返回删除结点的value
     */
    @Override
    public Value delete(Key key){
        // 如果不包含key
        if (!containsNode(key)){
            return null;
        }
        // preNode代表删除结点的父节点
        Node node = root,preNode = root;
        int temp;
        while (true) {
            temp = node.key.compareTo(key);
            // 假如key小于结点的key,则转向左子树
            if (temp > 0) {
                preNode = node;
                // 在删除的同时，将结点的N--
                preNode.N --;
                node = node.left;
            } else if (temp < 0) { // 转向右子树
                preNode.N --;
                preNode = node;
                node = node.right;
            } else {
                break;
            }
        }
        // 删除结点的返回值
        Value value = node.value;

        // mergeDelete代表合并删除
        if (node == root){
            root = mergeDelete(node);
        }
        // 假如删除的是父节点的左边的结点
        else if (preNode.left!=null && preNode.left == node){
            preNode.left = mergeDelete(node);
        }else {
            preNode.right = mergeDelete(node);
        }
        return value;
    }
    /**
     * 获得查找二叉树中所有结点的数量
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * 获得以某结点为根所有子树结点的数量（包括该结点）
     * @param node
     * @return
     */
    public int size(Node node){
        if (node == null){
            return 0;
        }
        return node.N;
    }

    /**
     * 查看是否含有key
     * @param key
     * @return
     */
    public boolean containsNode(Key key){
        if (key == null){
            return false;
        }
        Node node = root;
        int temp;
        while (node!= null) {
            temp = node.key.compareTo(key);
            // 假如key小于结点的key,则转向左子树
            if (temp > 0) {
                node = node.left;
            } else if (temp < 0) { // 转向右子树
                node = node.right;
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * 返回小于key的数量
     *
     * @param key
     * @return
     */
    @Override
    public int rank(Key key) {
        return 0;
    }

    /**
     * 返回排名为index 的键
     *
     * @param index
     * @return
     */
    @Override
    public Key select(int index) {
        return null;
    }
}
