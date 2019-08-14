package graph.weight;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Author: xiaohuiduan
 * @Date: 19-8-13 下午4:50
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
public class KruskalMST {
    /**
     * 优先队列
     */
    private PriorityQueue<Edge> pq;

    /**
     * 最小生成树
     */
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph graph) {
        pq = new PriorityQueue<>();
        mst =  new LinkedList<>();
        // 将边加入优先队列
        for (Edge edge:graph.edges()){
            pq.add(edge);
        }
        mst(graph);
    }

    private void mst(EdgeWeightedGraph graph) {
        WeightQuickUnionUF uf = new WeightQuickUnionUF(graph.V());
        while(!pq.isEmpty()){
            // 从里面取出最小的元素
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            // 防止成环
            if (uf.connected(v,w)){
                continue ;
            }
            uf.union(v,w);
            mst.offer(e);
        }
    }

    public Queue getMst() {
        return mst;
    }
}
