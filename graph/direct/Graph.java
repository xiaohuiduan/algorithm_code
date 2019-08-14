package graph.direct;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的抽象数据结构
 * @author xiaohui
 */
public abstract class Graph {
    // 顶点数量
    int V;
    // 边的数量
    int E;
    // 邻接表
    List[] adj;

    // 构造一个含有V个顶点的图，但是不含边
    Graph(int V) {
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        this.V = V;
    }

    /**
     * @return 返回顶点的数量
     */
    int V(){
        return V;
    }

    /**
     * @return 返回边的数量
     */
    int E(){
        return E;
    }

    /**
     * 在图中添加一条边v-w
     * @param v
     * @param w
     */
    abstract void addEdge(int v, int w);

    /**
     * 获得与v相邻的所有顶点
     * @param v
     * @return
     */
    abstract Iterable<Integer> adj(int v);

    /**
     * 与结点相连通的所有结点
     * @param s
     * @return
     */
    abstract Iterable<Integer>search(int s);

    /**
     * 是否存在S结点到V结点的路径
     * @param s
     * @param v
     * @return
     */
    abstract boolean hasPathTo(int s,int v);

    /**
     * 找出s到v结点的所有路径
     * @param s
     * @param v
     * @return
     */
    abstract Iterable<Integer> pathTo(int s,int v);


    /**
     * 便于进行打印
     * @return
     */
    @Override
    public String toString() {
        String s = "Graph{" +
                "V=" + V +
                ", E=" + E +
                '}';
        for (int v=0;v<V;v++){
            s += (v+":");
            for (int w :this.adj(v)) {
                s += w+" ";
            }
            s+= "\n";
        }
        return s;
    }
}
