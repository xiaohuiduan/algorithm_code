package graph.undir;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xiaohuiduan
 * @Date: 19-8-5 下午4:00
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
public class UndirGraph extends Graph {

    UndirGraph(int V) {
        super(V);
    }

    @Override
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        this.E ++;
    }

    @Override
    Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * 与结点相连通的所有结点
     *
     * @param s
     * @return
     */
    @Override
    Iterable<Integer> search(int s) {
        DepthFirstSearch dfs = new DepthFirstSearch(this,s);
        List list = new ArrayList(dfs.getCount());
        for (int i=0;i<this.V();i++) {
            if (dfs.getMarked(i)){
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 是否存在S结点到V结点的路径
     * @param s
     * @param v
     * @return
     */
    @Override
    boolean hasPathTo(int s, int v) {
        DepthFirstSearchPath dfsPath = new DepthFirstSearchPath(this,s);
        return dfsPath.hasPathTo(v);
    }

    /**
     * 找出s到v结点的路径
     *
     * @param s
     * @param v
     * @return
     */
    @Override
    Iterable<Integer> pathTo(int s, int v) {
        DepthFirstSearchPath dfsPath = new DepthFirstSearchPath(this,s);
        return dfsPath.pathTo(v);
    }
}
