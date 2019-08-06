package graph.undir;


/**
 * 无向图的深度优先搜索
 * @author xiaohui
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(UndirGraph graph,int s){
        marked = new boolean[graph.V()];
        dfs(graph,s);
    }

    private void dfs(UndirGraph graph, int s) {
        marked[s] = true;
        count++;
        for (int v:graph.adj(s)){
            if (!marked[v]){
                dfs(graph,v);
            }
        }
    }

    public boolean getMarked(int w) {
        return marked[w];
    }

    public int getCount() {
        return count;
    }
}

