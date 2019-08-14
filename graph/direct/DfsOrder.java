package graph.direct;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 江湖没什么好的，也就酒还行    ——阿良
 * 深度递归顶点排序
 * @author xiaohui
 */
public class DfsOrder {
    private boolean[] marked;
    /**
     * 前序
     */
    private Queue<Integer> pre;
    /**
     * 后序
     */
    private Queue<Integer> post;
    /**
     * 逆后序
     */
    private Stack<Integer> reversePost;

    public DfsOrder(Graph graph) {
        this.marked = new boolean[graph.V()];
        this.pre = new LinkedList<>();
        this.post = new LinkedList<>();
        this.reversePost = new Stack<>();
        for (int i = 0; i < graph.V(); i++) {
            if(!marked[i]){
                dfs(graph,i);
            }
        }
    }

    private void dfs(Graph graph, int v) {
        pre.offer(v);
        marked[v] = true;
        for (int w:graph.adj(v)) {
            if (!marked[w]){
                dfs(graph,w);
            }
        }
        post.offer(v);
        reversePost.push(v);
    }

    public Iterable<Integer> reversePost(){
        return this.reversePost;
    }

}
