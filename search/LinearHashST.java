package search;

/**
 * 基于线性探测法的散列表
 * @param <Key>
 * @param <Value>
 */
public class LinearHashST<Key extends Comparable<Key>,Value> {

    private Key[] keys;
    private Value[] values;
    /**
     * 键值对的数量
     */
    private int N;

    /**
     * 默认数组大小
     */
    private int M = 16;

    public LinearHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    /**
     * 初始化容量
     * @param N 指令数组大小
     */
    public LinearHashST(int N) {
        M = N;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }


    private int hash(Key key){
        return (key.hashCode()&0x7fffffff)%M;
    }
    public void put(Key key,Value value){
        // 如果容量达到阀值，则扩容
        if (N>=M*0.8){
            resize(M*2);
        }
        // 得到hash值
        int h;
        for (h = hash(key);keys[h]!=null;h = (h+1)%M){
            // key相等则更新
            if (key.compareTo(keys[h]) == 0){
                values[h] = value;
                return;
            }
        }
        keys[h] = key;
        values[h] = value;
        N ++;
    }
    public Value get(Key key){
        int h;
        for (h = hash(key);keys[h]!=null;h=(h+1)%M){
            if (key.compareTo(keys[h]) == 0){
                return values[h];
            }
        }
        return null;
    }

    /**
     * 进行删除操作
     * @param key
     */
    public void delete(Key key){
        int h = hash(key);
        while(keys[h]!=null){
            // 假如key存在
            if (keys[h].compareTo(key) == 0){
                keys[h] = null;
                values[h] = null;
                // 键值对数量减1
                N--;
                for (h=(h+1)%M; keys[h] != null;h=(h+1)%M){
                    // 将被删除结点后面的重新排列一下
                    Key keyToRedo = keys[h];
                    Value valToRedo = values[h];
                    keys[h] = null;
                    values[h] = null;
                    // 之所以N--是因为在put操作中N++了
                    N--;
                    put(keyToRedo,valToRedo);
                }
                // 缩小容量
                if (N>0 && N == M/8){
                    resize(M/2);
                }

            }
            h = (h+1)%M;
        }
        return;
    }


    /**
     * 进行改变容量操作
     * @param cap 容量大小
     */
    private void resize(int cap){
        LinearHashST<Key,Value> linearHashST = new LinearHashST(cap);
        for (int i=0;i<M;i++) {
            if (keys[i] != null){
                linearHashST.put(keys[i],values[i]);
            }
        }
        keys = linearHashST.keys;
        values = linearHashST.values;
        M = linearHashST.M;
    }

}
