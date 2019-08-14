package graph.direct;

/**
 * 使用Kosaraju算法的得到强通分量
 * @author xiaohui
 */
public class DfsSCC {

    private boolean[] marked;
    private int[] id;
    private int count = 0;

    public DfsSCC(DiGraph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        DfsOrder order = new DfsOrder(graph.reverse());
        for (int s:order.reversePost()){
            dfs(graph,s);
            count++;
        }
    }

    private void dfs(DiGraph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w:graph.adj(v)){
            if (!marked[v]){
                dfs(graph,w);
            }
        }
    }

    /**
     * 返回某结点强连通的id
     * @param v
     * @return
     */
    public int id(int v){
        return id[v];
    }

    /**
     * 判断v和w是否属于强连通
     * @param v
     * @param w
     * @return
     */
    public boolean stronglyConnected(int v,int w){
        return id[v]==id[w];
    }

    /**
     * 返回强连通分量的数目
     * @return
     */
    public int cout(){
        return count;
    }
}
