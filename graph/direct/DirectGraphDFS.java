package graph.direct;


/**
 * @author xiaohui
 * 有向图的深度优先算法
 */
public class DirectGraphDFS {
    private boolean[] marked;

    /**
     * 有向图的深度优先算法构造函数
     * @param diGraph
     * @param s 起点
     */
    public DirectGraphDFS(DiGraph diGraph,int s) {
        marked = new boolean[diGraph.V()];
        dfs(diGraph,s);
    }

    /**
     * 深度递归算法
     * @param diGraph
     * @param v
     */
    private void dfs(DiGraph diGraph, int v) {
        marked[v] = true;
        for (int w:diGraph.adj(v)) {
            if (!marked[w]){
                dfs(diGraph,v);
            }
        }
    }


    /**
     * 起点s可达到v吗
     * @param v
     * @return
     */
    public boolean pathTo(int v){
        return marked[v];
    }
}
