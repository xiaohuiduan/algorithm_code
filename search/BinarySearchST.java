package search;

/**
 * 有序数组的二分查找
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> extends AbstractST <Key,Value> {

    private int size;
    private Key[] keys;
    private  Value[] values;

    /**
     * 初始化容量的大小
     * @param capacity 初始化大小
     */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Comparable[capacity];
        this.size = 0;
    }

    /**
     * 将键值存入表中
     *
     * @param key
     * @param value
     */
    @Override
    public void put(Key key, Value value) {
        // 假如值是空的，则代表删除这个键
        if (value == null){
            delete(key);
        }
        int position = rank(key);
        // 如果键存在则更新。之所以先【position < size 】是为了防止出现数组越界。
        if (position < size && keys[position].compareTo(key) == 0){
            values[position] = value;
            return;
        }

        // 如果容量已经满了，则进行扩容到原来的两倍
        if (size == keys.length){
            resize(2*size);
        }
        // 为position这个位置腾出空间
        for (int i = size; i > position ; i--) {
            keys[i] = keys[i -1];
            values[i] = values[i -1];
        }
        keys[position] = key;
        values[position] = value;

        size ++;
    }

    /**
     * 进行扩容
     * @param capacity 容量大小
     */
    public void resize(int capacity){
        Key[] newKeys = (Key[]) new Comparable[capacity];
        Value[] newValues = (Value[]) new Comparable[capacity];

        for (int i =0;i<size;i++){
            newKeys[i] = keys[i];
            newValues[i] = values[i];
        }
        keys = newKeys;
        values = newValues;
    }
    /**
     * 通过key获得value
     *
     * @param key
     * @return 若不存在则返回空
     */
    @Override
    public Value get(Key key) {
        if (size == 0){
            return  null;
        }
        // 获得所在位置
        int i = rank(key);

        if (i<size && keys[i].compareTo(key) == 0){
            return values[i];
        }
        else{
            return null;
        }

    }

    /**
     * 通过key删除结点
     *
     * @param key
     * @return
     */
    @Override
    public Value delete(Key key) {
        if (key == null){
            return null;
        }
        int position = rank(key);
        // 假如key不存在
        if (position < size && key.compareTo(keys[position]) != 0){
            return  null;
        }else if (position == size){
            return null;
        }

        Value returnValue = values[position];
        for (int i = position;i < size - 1;i++){
            keys[i] = keys[i+1];
            values[i] = values[i+1];
        }
        size --;
        // 减小容量
        if (size>0 && size == keys.length/4){
            resize(keys.length/2);
        }
        return returnValue;
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
     * 非递归二分查找
     * @param key
     * @return
     */
    @Override
    public int rank(Key key) {
        // lo……hi代表二分查找的范围
        int lo = 0,hi = size -1;

        while(lo<=hi){
            int mid = lo + ((hi-lo)>>1);
            int result = key.compareTo(keys[mid]);
            // 假如key 大于 keys[mid]则返回大于0的值
            if (result > 0){
                lo = mid + 1;
            }
            // 假如key 小于 keys[mid]则返回小于0的值
            else if(result < 0){
                hi = mid -1;
            }
            // 如果两个相等
            else {
                return mid;
            }
        }
        return lo;
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
