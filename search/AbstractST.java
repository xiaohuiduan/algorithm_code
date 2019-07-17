package search;

public abstract class AbstractST<Key extends Comparable<Key>,Value>{

    /**
     * 将键值存入表中 
     * @param key
     * @param value
     */
    public abstract void put(Key key,Value value);
    /**
     * 通过key获得value
     * @param key
     * @return 若不存在则返回空
     */
    public abstract Value get(Key key);

    /**
     * 通过key删除结点
     * @param key
     * @return
     */
    public abstract Value delete(Key key);

    /**
     * 表中的键值对数量
     * @return
     */
    public abstract int size();

    /**
     * 判断是否为空
     * @return
     */
    public boolean isEmpty(){
        return size() == 0;
    }
    
    /**
     * 判断建是否存在
     * @param key
     * @return
     */
    public boolean contains(Key key){
        return get(key) != null;
    }


    /**
     * 返回小于key的数量
     * @param key
     * @return
     */
    public abstract int rank(Key key);

    /**
     * 返回排名为index 的键
     * @param index
     * @return
     */
    public abstract Key select(int index);

}