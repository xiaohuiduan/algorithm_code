package graph.weight;

/**
 * @Author: xiaohuiduan
 * @Date: 19-8-13 下午3:56
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\  =  /O
 * ____/`---'\____
 * .'  \\|     |//  `.
 * /  \\|||  :  |||//  \
 * /  _||||| -:- |||||-  \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |   |
 * \  .-\__  `-`  ___/-. /
 * ___`. .'  /--.--\  `. . __
 * ."" '<  `.___\_<|>_/___.'  >'"".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 佛祖保佑       永无BUG
 */

/**
 * 加权quick-union算法
 */
public class WeightQuickUnionUF {

    /**
     * 结点的父节点
     */
    private int[] id;
    /**
     * （由结点索引的）各个根节点所对应的根节点的大小
     */
    private int[] sz;
    /**
     * 连通分量的数量
     */
    private int count;

    /**
     * 进行初始化，初始化后其中每一个结点都是一个连通分量
     * 其中结点的父节点为自己本身
     * @param N
     */
    public WeightQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    /**
     * p和q是否相链接，若相连接，则在同一个连通分量里面
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p,int q){
       return find(p) == find(q); 
    }

    /**
     * 找到根节点
     * @param v
     * @return
     */
    private int find(int v) {
        // 在根节点中id[v]= v（在初始化的时候定义的）
        while(v != id[v]){
            v = id[v];
        }
        return v;
    }

    /**
     * 在p和q之间添加一条链接
     * @param p
     * @param q
     */
    public void union(int p,int q){
        int i = find(p);
        int j = find(q);
        // 如果是同一条连通分量，则返回，没必要添加
        if (i==j){
            return ;
        }
        // 这一步的目的是将小树加入大树
        if (sz[i]<sz[j]){
            id[i] = j;
            sz[j] += sz[i];
        }else{
            id[j] = i;
            sz[i] += sz[j];
        }
        count --;
    }
}


