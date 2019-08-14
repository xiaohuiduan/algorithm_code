package graph.direct;

/**
 * @author xiaohui
 * 定义有向图的抽象类的API
 */
public class DiGraph extends Graph {

    public DiGraph(int V) {
        super(V);
    }

    /**
     * 在图中添加一条边v-w
     *
     * @param v
     * @param w
     */
    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    /**
     * 遍历每一个结点，然后进行翻转
     * @return 返回翻转后的图
     */
    public DiGraph reverse(){
        DiGraph diGraph = new DiGraph(V);
        for (int i = 0; i < V; i++) {
            for (int w:adj(i)){
                diGraph.addEdge(w,i);
            }
        }
        return diGraph;
    }

    /**
     * 获得与v相邻的所有顶点
     *
     * @param v
     * @return
     */
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
        return null;
    }

    /**
     * 是否存在S结点到V结点的路径
     *
     * @param s
     * @param v
     * @return
     */
    @Override
    boolean hasPathTo(int s, int v) {
        return false;
    }

    /**
     * 找出s到v结点的所有路径
     *
     * @param s
     * @param v
     * @return
     */
    @Override
    Iterable<Integer> pathTo(int s, int v) {
        return null;
    }
}
