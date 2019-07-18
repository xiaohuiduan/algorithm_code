package search;

import java.util.HashMap;

/**
 * 无序链表
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearch<Key extends Comparable<Key>,Value> extends AbstractST<Key,Value> {

    private Node first;
    private  int size;

    /**
     * 将键值存入表中
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Key key, Value value) {
        // 进行更新
        for (Node  x = first ; x!=null; x = x.next) {
            if (x.key.equals(key)){
                x.value = value;
                return;
            }
        }
        // 新的创建
        first = new Node(key,value,first);
        size ++;
    }

    /**
     * 通过key获得value
     *
     * @param key
     * @return 若不存在则返回空
     */
    @Override
    public Value get(Key key) {

        // 命中
        for (Node  x = first ; x!=null; x = x.next) {
            if (x.key.equals(key)){
             return x.value;
            }
        }
        //  没有命中。
        return null;
    }

    /**
     * 通过key删除结点
     *
     * @param key
     * @return
     */
    @Override
    public Value delete(Key key) {
        Node pre = first;

        for (Node x = first;x!=null;pre = x, x = x.next){
            if (x.key.equals(key)){
                Value value = x.value;
                pre.next = x.next;
                return value;
            }
        }
        return null;
    }

    /**
     * 表中的键值对数量
     *
     * @return
     */
    @Override
    public int size() {
        return size;
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

    private class Node{
        Key key;
        Value value;
        Node next;

        public Node(Key key,Value value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}