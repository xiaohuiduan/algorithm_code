package graph.undir;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author xiaohui
 * 广度优先遍历
 */
public class BreadthFirstSearch {
    private boolean[] marked;
    private final int s;
    private int[] edgeTo;

    public BreadthFirstSearch(Graph graph,int s) {
        this.s = s;
        this.marked = new boolean[graph.V()];
        this.edgeTo = new int[graph.V()];
        bfs(graph,s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        // 将s加入队列中
        queue.offer(s);
        while(!queue.isEmpty()){
            // 从队列中删除结点
            int v = queue.poll();
            for (int w: graph.adj(v)) {
                if (!marked[w]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.offer(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int i = v; i != s; i = edgeTo[i]) {
            path.push(i);
        }
        path.push(s);
        return path;
    }
}
