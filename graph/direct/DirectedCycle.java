package graph.direct;

import java.util.Stack;

/**
 * 查找有向图中是否存在环
 * @author xiaohui
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    /**
     * 有向环中所有顶点
     */
    private Stack<Integer> cycle;
    /**
     * 顶点是否在递归调用栈上
     */
    private boolean[] onStack;

    public DirectedCycle(Graph graph) {
        onStack = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        marked = new boolean[graph.V()];
        for (int v=0;v<graph.V();v++){
            if (!marked[v]){
                dfs(graph,v);
            }
        }
    }


    private void dfs(Graph graph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w:graph.adj(v)){
            if (this.hasCycle()){
                return;
            }
            else if(!marked[w]){
                edgeTo[w] = v;
                dfs(graph,w);
            }else if (onStack[w]){
                cycle = new Stack<>();
                for (int x= v;x != w;x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
            onStack[v] = false;
        }
    }

    /**
     * 有向图中是否含有环
     * @return
     */
    public boolean hasCycle(){
        return cycle == null;
    }

    /**
     * 获得有向环中的顶点
     * @return
     */
    public Iterable cycle(){
        return this.cycle;
    }
}
