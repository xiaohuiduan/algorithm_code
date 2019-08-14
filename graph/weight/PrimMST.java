package graph.weight;

/**
 * @Author: xiaohuiduan
 * @Date: 19-8-13 下午9:17
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 即时版本的prim算法
 */
public class PrimMST {

    /**
     * 某结点距离树最近的边
     */
    private Edge[] edgeTo;
    /**
     * 某结点距离树的权重距离
     */
    private double[] distTo;

    /**
     * 结点是否在树中
     */
    private boolean[] marked;

    /**
     * 有效的横切边（也就是被保存在优先队列中有效的边）
     * key: 结点的id
     * value:权重
     */
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.V()];
        distTo = new double[graph.V()];
        marked = new boolean[graph.V()];
        pq = new IndexMinPQ<>(graph.V());
        // 初始化结点到树的距离为无限大
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        pq.insert(0,0.0);
        while(!pq.isEmpty()){
            visit(graph,pq.delMin());
        }

    }


    private void visit(EdgeWeightedGraph graph, int v) {

        marked[v] = true;
        // 遍历与v相连的结点
        for (Edge e:graph.adj(v)){
            int w = e.other(v);
            // 假如w已经为最小树的结点,则不进行处理
            if (marked[w]){
                continue;
            }
            // 假如w结点的权重小于w结点到树的距离
            if (e.weight()<distTo[w]){
                // w结点到最小树的边为e
                edgeTo[w] = e;
                // 距离为e边的权重
                distTo[w] = e.weight();
                // 将结点放入优先队列
                if (pq.contains(w)){
                    pq.changeKey(w,distTo[w]);
                }
                else{
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }

    /**
     * 返回最小生成树
     * @return
     */
   public ArrayList<Edge> mst(){
       ArrayList<Edge> list = new ArrayList<>();
       for (Edge e:edgeTo) {
           if (e!=null){
               list.add(e);
           }

       }
       return list;
   }
}
