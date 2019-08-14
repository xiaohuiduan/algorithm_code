package graph.undir;


import java.util.Stack;

/**
 * @author xiaohui
 * 通过深度优先搜索寻找路径
 */
public class DepthFirstSearchPath {

    private boolean[] marked;
    /**
     * 从起点到一个顶点的已知路径上面的最后一个顶点，例如：
     * 0-3-4-5-6 则 edgeTo[6] = 5
     */
    private int[] edgeTo;
    /**
     * 起点
     */
    private final int s;

    /**
     * 在graph中找出所有起点为s的路径
     * @param graph
     * @param s
     */
    public DepthFirstSearchPath(Graph graph,int s) {
        marked = new boolean[graph.V()];
        this.s = s;
        edgeTo = new int[graph.V()];
        dfs(graph,s);
    }

    private void dfs(Graph graph, int s) {
        marked[s] = true;

        for (int v:graph.adj(s)){
            if (!marked[v]){
                edgeTo[v] = s;
                dfs(graph,v);
            }
        }
    }

    /**
     * v的顶点是否可达，也就是说是否存在s到v的路径
     * @param v
     * @return
     */
    public boolean hasPathTo(int v){
        return marked[v];
    }


    /**
     * 返回s到v的路径
     * @param v
     * @return
     */
    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
