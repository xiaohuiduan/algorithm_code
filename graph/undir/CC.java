package graph.undir;

public class CC {
    private boolean[] marked;
    /**
     * id代表结点所属的连通分量为哪一个，例如：
     * id[1] =0,id[3]=1
     * 代表1结点属于0连通分量，3结点属于1连通分量
     */
    private int[] id;
    /**
     * count代表连通分量的表示，0，1……
     */
    private int count;

    public CC(Graph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        for (int s=0;s<graph.V();s++){
            if (!marked[s]){
                count++;
                dfs(graph,s);
            }
        }
    }

    private void dfs(Graph graph,int v) {
        marked[v] = true;
        id[v] = count;
        for (int w:graph.adj(v)) {
            if (!marked[w]){
                dfs(graph,w);
            }
        }
    }

    /**
     * v和w是否属于同一连通分量
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v,int w){
        return id[v]==id[w];
    }

    /**
     * 获得连通分量的数量
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * 结点属于哪一个连通分量
     * @param w
     * @return
     */
    public int id(int w){
        return id[w];
    }

}
