package graph.weight;

/**
 * @Author: xiaohuiduan
 * @Date: 19-8-13 下午8:02
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

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * prim算法延时实现
 */
public class LazyPrimMST {

    private boolean[] marked;
    private Queue<Edge> mst;
    /**
     * 横切边
     */
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph graph){
        marked = new boolean[graph.V()];
        pq = new PriorityQueue<>();
        mst = new LinkedList<>();
        mst(graph);
    }

    /**
     * 生成最小树
     * @param graph
     */
    public void mst(EdgeWeightedGraph graph){
        visit(graph,0);
        while (!pq.isEmpty()){
            // 从优先队列中得到最小的边
            Edge e= pq.poll();
            int v = e.either();
            int w = e.other(v);
            // 如果两个顶点都被标记了，则看下一条边
            if (marked[v] && marked[w]){
                continue;
            }
            // 将边加入mst
            mst.add(e);
            if (!marked[v]){
                visit(graph,v);
            }
            if (!marked[w]){
                visit(graph,w);
            }

        }
    }

    /**
     * 标记顶点v并将其（所有）边（边所相连接的另外一个顶点未被标记）加入队列中
     * @param graph
     * @param v
     */
    public void visit(EdgeWeightedGraph graph,int v){
        marked[v] = true;
        for (Edge e:graph.adj(v)){
            // 另外一个顶点
            if (!marked[e.other(v)]){
                // 将顶点假如优先队列
                pq.add(e);
            }
        }
    }


    /**
     * 返回最小生成树
     * @return
     */
    public Queue<Edge> getMst() {
        return this.mst;
    }
}
