package graph.weight;

/**
 * @Author: xiaohuiduan
 * @Date: 19-8-13 上午11:26
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
import java.util.List;

/**
 * 加权无向图的数据结构
 */
public class EdgeWeightedGraph {
    /**
     * 顶点总数
     */
    private final int V;
    /**
     * 边的总数
     */
    private int E;
    /**
     * 边
     */
    private List<Edge>[] adj;

    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        this.E = 0;

        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Edge>();
        }
    }

    public int V() {
        return V;
    }
    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * 获取图中的所有边
     * @return
     */
    public Iterable<Edge> edges(){
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (Edge e:adj[i]){
                /**
                 * 如果i和j为一条边e，那么adj[i] = e;adj[j] = e;这两条边是一样的，所以我们需要去除一条边
                 */
                if (e.other(i)>i){
                    list.add(e);
                }
            }
        }
        return list;
    }
}

