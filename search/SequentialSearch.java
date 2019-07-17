package search;

public class SequentialSearch extends AbstractST {

    private class Node{
        Key key;
        Value value;
        Node next;


        public classNode(Key key,Value value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}