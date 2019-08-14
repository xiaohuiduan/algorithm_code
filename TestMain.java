import graph.weight.*;

import java.util.Arrays;

/**
 * @Author: xiaohuiduan
 * @Date: 19-8-13 下午5:02
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
public class TestMain {
    public static void main(String[] args) {
        EdgeWeightedGraph e = new EdgeWeightedGraph(6);
        e.addEdge(new Edge(1,5,1));
        e.addEdge(new Edge(1,4,2));
        e.addEdge(new Edge(1,3,3));

        e.addEdge(new Edge(3,4,4));
        e.addEdge(new Edge(3,5,5));
        e.addEdge(new Edge(5,2,6));
        e.addEdge(new Edge(4,2,7));
        e.addEdge(new Edge(0,5,9));

        KruskalMST mst = new KruskalMST(e);
        System.out.println(mst.getMst());

        LazyPrimMST lazyPrimMST = new LazyPrimMST(e);
        System.out.println(lazyPrimMST.getMst());

        PrimMST primMST = new PrimMST(e);
        System.out.println(primMST.mst());
    }
}
