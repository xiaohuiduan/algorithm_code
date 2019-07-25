package search;

/**
 * 2-3 查找树
 * 我家门前有很多棵树，选择吊死哪棵树
 */
public class Tree23<Key extends Comparable<Key>,Value> {

    /**
     * 展示树的结构
     * @param node
     * @param level
     * @param childNum
     */
    private void fineDisplay(Node23 node, int level, int childNum) {
        System.out.println("level " + level + " child " + childNum);
        node.displayNode();
        for (int i = 0; i < node.getItemNum() + 1; i++) {
            Node23 child = node.getChild(i);
            if (child != null){
                fineDisplay(child, level + 1, i);
            }
            else{
                return;
            }
        }
    }

    public void displayTree() {
        fineDisplay(root, 0, 0);
    }


    /**
     * 保存key和value的键值对
     * @param <Key>
     * @param <Value>
     */
    private class Data<Key extends Comparable<Key>,Value>{
        private Key key;
        private Value value;

        public Data(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
        public void displayData(){
            System.out.println("/" + key+"---"+value);
        }
    }

    /**
     * 保存树结点的类
     * @param <Key>
     * @param <Value>
     */
    private class Node23<Key extends Comparable<Key>,Value>{

        public void displayNode() {
            for(int i = 0; i < itemNum; i++){
                itemDatas[i].displayData();
            }
            System.out.println("/");
        }

        private static final int N = 3;
        // 该结点的父节点
        private Node23 parent;
        // 子节点，子节点有3个，分别是左子节点，中间子节点和右子节点
        private Node23[] chirldNodes = new Node23[N];
        // 代表结点保存的数据（为一个或者两个）
        private Data[] itemDatas = new Data[N - 1];
        // 结点保存的数据个数
        private int itemNum = 0;

        /**
         * 判断是否是叶子结点
         * @return
         */
        private boolean isLeaf(){
            // 假如不是叶子结点。必有左子树（可以想一想为什么？）
            return chirldNodes[0] == null;
        }

        /**
         * 判断结点储存数据是否满了
         * （也就是是否存了两个键值对）
         * @return
         */
        private boolean isFull(){
            return itemNum == N-1;
        }

        /**
         * 返回该节点的父节点
         * @return
         */
        private Node23 getParent(){
            return this.parent;
        }

        /**
         * 将子节点连接
         * @param index 连接的位置（左子树，中子树，还是右子树）
         * @param child
         */
        private void connectChild(int index,Node23 child){
            chirldNodes[index] = child;
            if (child != null){
                child.parent = this;
            }
        }

        /**
         * 解除该节点和某个结点之间的连接
         * @param index 解除链接的位置
         * @return
         */
        private Node23 disconnectChild(int index){
            Node23 temp = chirldNodes[index];
            chirldNodes[index] = null;
            return temp;
        }

        /**
         * 获取结点左或右的键值对
         * @param index 0为左，1为右
         * @return
         */
        private Data getData(int index){
            return itemDatas[index];
        }

        /**
         * 获得某个位置的子树
         * @param index 0为左指数，1为中子树，2为右子树
         * @return
         */
        private Node23 getChild(int index){
            return chirldNodes[index];
        }

        /**
         * @return 返回结点中键值对的数量，空则返回-1
         */
        public int getItemNum(){
            return itemNum;
         }

        /**
         * 寻找key在结点的位置
         * @param key
         * @return 结点没有key则放回-1
         */
        private int findItem(Key key){
            for (int i = 0; i < itemNum; i++) {
                if (itemDatas[i] == null){
                    break;
                }else if (itemDatas[i].key.compareTo(key) == 0){
                    return i;
                }
            }
            return -1;
        }

        /**
         * 向结点插入键值对：前提是结点未满
         * @param data
         * @return 返回插入的位置 0或则1
         */
        private int insertData(Data data){
            itemNum ++;
            for (int i = N -2; i >= 0 ; i--) {
                if (itemDatas[i] == null){
                    continue;
                }else{
                    if (data.key.compareTo(itemDatas[i].key)<0){
                        itemDatas[i+1] = itemDatas[i];
                    }else{
                        itemDatas[i+1] = data;
                        return i+1;
                    }
                }
            }
            itemDatas[0] = data;
            return 0;
        }

        /**
         * 移除最后一个键值对（也就是有右边的键值对则移右边的，没有则移左边的）
         * @return 返回被移除的键值对
         */
        private Data removeItem(){
            Data temp = itemDatas[itemNum - 1];
            itemDatas[itemNum - 1] = null;
            itemNum --;
            return temp;
        }
    }

    /**
     * 根节点
     */
    private Node23 root = new Node23();

    /**
     * 查找含有key的键值对
     * @param key
     * @return 返回键值对中的value
     */
    public Value find(Key key) {
        Node23 curNode = root;
        int childNum;
        while (true) {
            if ((childNum = curNode.findItem(key)) != -1) {
                return (Value) curNode.itemDatas[childNum].value;
            }
            // 假如到了叶子节点还没有找到，则树中不包含key
            else if (curNode.isLeaf()) {
                return null;
            } else {
                curNode = getNextChild(curNode,key);
            }
        }
    }

    /**
     * 在key的条件下获得结点的子节点（可能为左子结点，中间子节点，右子节点）
     * @param node
     * @param key
     * @return 返回子节点，若结点包含key,则返回传参结点
     */
    private Node23 getNextChild(Node23 node,Key key){
        for (int i = 0; i < node.getItemNum(); i++) {
            if (node.getData(i).key.compareTo(key)>0){
                return node.getChild(i);
            }
            else if (node.getData(i).key.compareTo(key) == 0){
                return node;
            }
        }
        return node.getChild(node.getItemNum());
    }

    /**
     * 最重要的插入函数
     * @param key
     * @param value
     */
    public void insert(Key key,Value value){
        Data data = new Data(key,value);
        Node23 curNode = root;
        // 一直找到叶节点
        while(true){
            if (curNode.isLeaf()){
                break;
            }else{
                curNode = getNextChild(curNode,key);
                for (int i = 0; i < curNode.getItemNum(); i++) {
                    // 假如key在node中则进行更新
                    if (curNode.getData(i).key.compareTo(key) == 0){
                        curNode.getData(i).value =value;
                        return;
                    }
                }
            }
        }

        // 若插入key的结点已经满了，即3-结点插入
        if (curNode.isFull()){
            split(curNode,data);
        }
        // 2-结点插入
        else {
            // 直接插入即可
            curNode.insertData(data);
        }
    }

    /**
     * 这个函数是裂变函数，主要是裂变结点。
     * 这个函数有点复杂，我们要把握住原理就好了
     * @param node 被裂变的结点
     * @param data 要被保存的键值对
     */
    private void split(Node23 node, Data data) {
        Node23 parent = node.getParent();
        // newNode用来保存最大的键值对
        Node23 newNode = new Node23();
        // newNode2用来保存中间key的键值对
        Node23 newNode2 = new Node23();
        Data mid;

        if (data.key.compareTo(node.getData(0).key)<0){
            newNode.insertData(node.removeItem());
            mid = node.removeItem();
            node.insertData(data);
        }else if (data.key.compareTo(node.getData(1).key)<0){
            newNode.insertData(node.removeItem());
            mid = data;
        }else{
            mid = node.removeItem();
            newNode.insertData(data);
        }
        if (node == root){
            root = newNode2;
        }
        /**
         * 将newNode2和node以及newNode连接起来
         * 其中node连接到newNode2的左子树，newNode
         * 连接到newNode2的右子树
         */
        newNode2.insertData(mid);
        newNode2.connectChild(0,node);
        newNode2.connectChild(1,newNode);
        /**
         * 将结点的父节点和newNode2结点连接起来
         */
        connectNode(parent,newNode2);
    }

    /**
     * 链接node和parent
     * @param parent
     * @param node node中只含有一个键值对结点
     */
    private void connectNode(Node23 parent, Node23 node) {
        Data data = node.getData(0);
        if (node == root){
            return;
        }
        // 假如父节点为3-结点
        if (parent.isFull()){
            // 爷爷结点（爷爷救葫芦娃）
            Node23 gParent = parent.getParent();
            Node23 newNode = new Node23();
            Node23 temp1,temp2;
            Data itemData;

            if (data.key.compareTo(parent.getData(0).key)<0){
                temp1 = parent.disconnectChild(1);
                temp2 = parent.disconnectChild(2);
                newNode.connectChild(0,temp1);
                newNode.connectChild(1,temp2);
                newNode.insertData(parent.removeItem());

                itemData = parent.removeItem();
                parent.insertData(itemData);
                parent.connectChild(0,node);
                parent.connectChild(1,newNode);
            }else if(data.key.compareTo(parent.getData(1).key)<0){
                temp1 = parent.disconnectChild(0);
                temp2 = parent.disconnectChild(2);
                Node23 tempNode = new Node23();

                newNode.insertData(parent.removeItem());
                newNode.connectChild(0,newNode.disconnectChild(1));
                newNode.connectChild(1,temp2);

                tempNode.insertData(parent.removeItem());
                tempNode.connectChild(0,temp1);
                tempNode.connectChild(1,node.disconnectChild(0));

                parent.insertData(node.removeItem());
                parent.connectChild(0,tempNode);
                parent.connectChild(1,newNode);
            } else{
                itemData = parent.removeItem();

                newNode.insertData(parent.removeItem());
                newNode.connectChild(0,parent.disconnectChild(0));
                newNode.connectChild(1,parent.disconnectChild(1));
                parent.disconnectChild(2);
                parent.insertData(itemData);
                parent.connectChild(0,newNode);
                parent.connectChild(1,node);
            }
            // 进行递归
            connectNode(gParent,parent);
        }
        // 假如父节点为2结点
        else{
            if (data.key.compareTo(parent.getData(0).key)<0){
                Node23 tempNode = parent.disconnectChild(1);
                parent.connectChild(0,node.disconnectChild(0));
                parent.connectChild(1,node.disconnectChild(1));
                parent.connectChild(2,tempNode);
            }else{
                parent.connectChild(1,node.disconnectChild(0));
                parent.connectChild(2,node.disconnectChild(1));
            }
            parent.insertData(node.getData(0));
        }
    }


}
