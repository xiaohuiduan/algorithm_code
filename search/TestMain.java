package search;

import java.util.HashMap;
import java.util.Random;

public class TestMain {

    public static void main(String[] args) {
        BST<Integer,Integer> bst = new BST<>();
        bst.put(2,2);
            bst.put(1,1);
        bst.put(3,3);

            bst.delete(2);

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer x =  random.nextInt(10);
            bst.put(x,x);
        }
        for (int i = 1; i < 10; i++) {
            System.out.println(bst.get(i));
        }

        for (int i = 0; i < 3; i++) {
            Integer x =  random.nextInt(30);
            bst.delete(x);
        }


    }
}
