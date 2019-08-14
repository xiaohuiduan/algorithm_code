package graph.weight;

/**
 * @Author: xiaohuiduan
 * @Date: 19-8-13 上午11:18
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\  =  /O
 * ____/`---'\____
 * .'  \\|     |//  `.
 * /  \\|||  :  |||//  \
 * /  _||||| -:- |||||-  \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |   |
 * \  .-\__  `-`  ___/-. /
 * ___`. .'  /--.--\  `. . __
 * ."" '<  `.___\_<|>_/___.'  >'"".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 佛祖保佑       永无BUG
 */

/**
 * 定义一条边的数据类型结构
 */
public class Edge implements Comparable<Edge> {
    /**
     * 一条边的某一个顶点
     */
    private final int v;
    /**
     * 一条边的另外一个顶点
     */
    private final int w;
    /**
     * 边的权重
     */
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public double weight(){
        return weight;
    }

    /**
     * 得到边的某一个顶点
     * @return
     */
    public int either(){
        return v;
    }

    /**
     * 通过某一个顶点得到边的另外一个顶点
     * @param vertex
     * @return
     */
    public int other(int vertex){
        if(vertex == w){
            return v;
        }else if(vertex==v){
            return w;
        }else{
            throw new RuntimeException("没有这一条边");
        }
    }

    /**
     * 边进行比较
     * @param o
     * @return
     */
    @Override
    public int compareTo(Edge o) {
        if (this.weight() > o.weight()){
            return 1;
        }else if (this.weight() < o.weight()){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
