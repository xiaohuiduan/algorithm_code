package search;

import java.util.Random;

/**
 * 跳跃链表搜素
 */
public class SkipList<Key extends Comparable<Key>,Value>{

    // 首先我们先定义好结点。
    private class Node{
        Key key;
        Value value;
        Node up;
        Node down;
        Node right;
        Node left;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }

    // 当前跳表最大层数
    private int maxLevel = 1;
    // 当前插入结点的个数
    private int size;
    // 首结点
    private Node head;
    // 尾结点
    private Node tail;


    public SkipList() {
        // 创建首尾结点
        head = new Node((Key) null,null);
        tail = new Node((Key) null,null);
        head.right = tail;
        tail.left = head;
        size = 0;
        maxLevel = 0;
    }

    /**
     * 添加新的一层
     */
    public void addEmptyLevel(){
        Node newHead = new Node(null,null);
        Node newTail= new Node(null,null);

        newHead.right  = tail;
        tail.left = newHead;

        newHead.down = head;
        newTail.down = tail;

        head.up = newHead;
        tail.up = newTail;

        head = newHead;
        tail = newTail;

        maxLevel ++;
    }

    public void put(Key key, Value value) {
        // Key不能为null
        if (key == null){
            return;
        }
        // 插入的合适位置
        Node putPosition = getNode(key);

        // 如果相等则更新
        if (key.equals(putPosition.key)){
            putPosition.value = value;
            return;
        }
        // 进行新增操作
        Node newNode = new Node(key,value);
        /**
         * putPostion的key小于key,所以排序位置应该是
         * putPosition 【newNode】 putPosition.next
         * 所以进行下面的操作
         */
        newNode.right = putPosition.right;
        newNode.left = putPosition;
        putPosition.right.left = newNode;
        putPosition.right = newNode;
        Random random = new Random();

        int level = 0;

        // 产生0和1,使得新的结点有1/2的几率去增加level
        while (random.nextInt(2) == 0){

            // 假如高度达到了maxLevel则添加一个层
            if (level >= maxLevel){
                addEmptyLevel();
            }

            while (putPosition.up == null){
                putPosition = putPosition.left;
            }

           putPosition = putPosition.up;
            // 可以将skipNode中的value设置为空，不过为空也没什么关系
            Node skipNode = new Node(key, null);

            /**
             * 需要的顺序是：
             * putPosition 【skipNode】 putPosition.right
             */
            skipNode.left = putPosition;
            skipNode.right = putPosition.right;
            putPosition.right.left = skipNode;
            putPosition.right = skipNode;

            // 将newNode放到上一层
            /**
             * putpostion skipNode skipNode.right
             * newNode
             */

            skipNode.down = newNode;
            newNode.up = skipNode;
            newNode = skipNode;
            level ++;
        }

        size ++;
    }

    /**
     * 通过key获得node(node不一定是正确的)
     * @param key
     * @return 返回的node.key <= key
     */
    public Node getNode(Key key) {
        if (key == null){
            return  null;
        }

        Node node = head;

        while(true){

            /**
             * 假如node.right不为尾结点且key.value大于或者等于右边结点的key
             */
            while (node.right.key != tail.key && key.compareTo(node.right.key) >= 0){
                node = node.right;
            }
            if (node.down != null){
                node = node.down;
            }else {
                break;
            }
        }
        // 返回的node.key <= key
        return node;
    }

    /**
     * 通过key获得value
     * @param key
     * @return`
     */
    public Value get(Key key){
        if (key == null){
            return  null;
        }
        Node node = getNode(key);
        if (node.key.compareTo(key) == 0){
            return node.value;
        }else {
            return null;
        }
    }

    /**
     * 通过key删除结点
     *
     * @param key
     * @return
     */
    public Value delete(Key key) {
        Node deleteNode = getNode(key);
        if (deleteNode.key.compareTo(key) != 0){
            return null;
        }
        Value returnValue = deleteNode.value;

        // 切断
        while (deleteNode != null){
            deleteNode.left.right = deleteNode.right;
            deleteNode.right.left = deleteNode.left;
            deleteNode = deleteNode.up;
        }
        return returnValue;

    }

    /**
     * 表中的键值对数量
     *
     * @return
     */
    public int size() {
        return size;
    }
}