package search;

import java.util.HashMap;

public class TestMain {

    public static void main(String[] args) {
//        SequentialSearch<String,Integer> stringSequentialSearch = new SequentialSearch<>();
//        stringSequentialSearch.put("A",1);
//        stringSequentialSearch.put("B",2);
//        stringSequentialSearch.put("C",13);
//        System.out.println(stringSequentialSearch.delete("A").toString());
//        System.out.println(stringSequentialSearch.get("A"));
        HashMap hashMap = new HashMap();

        BinarySearchST<Integer,String> sequentialSearch = new BinarySearchST(3);
        sequentialSearch.put(1,"veshi ");
        sequentialSearch.put(2,"22 ");
        sequentialSearch.put(3,"33");
        System.out.println(sequentialSearch.get(3));

        System.out.println(sequentialSearch.delete(3));

        System.out.println(sequentialSearch.get(3));

    }
}
