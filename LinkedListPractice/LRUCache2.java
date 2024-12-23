package LinkedListPractice;

import java.util.HashMap;
import java.util.Map;
class LRUCache2 {
    // Constructor for initializing the cache capacity with the given value
    Node head, tail;
    Map<Integer,Node> nodeMap= new HashMap<>();
    int cap;
    LRUCache2(int cap) {
        // code here
        this.cap=cap;
    }

    // Function to return value corresponding to the key.
    public int get(int key) {
        // your code here
        if(this.nodeMap.containsKey(key)){
            Node node=this.nodeMap.get(key);
            //System.out.println("before "+node.key);
            remove(node);
            //System.out.println("after "+node.key);
            insert(node);
            return node.value;
        }
        else{
            return -1;
        }
    }

    // Function for storing key-value pair.
    public void set(int key, int value) {
        // your code here
        Node node= new Node(key, value);
        if(this.nodeMap.containsKey(key)){
            remove(this.nodeMap.get(key));
        }
        else{
            if(this.nodeMap.size()==this.cap){
                remove(this.head);
            }
        }
        insert(node);
    }
    public void insert(Node node){
        this.nodeMap.put(node.key, node);
        System.out.println(node.key);
        if(head==null){
            this.head=node;
            this.tail=node;
        }else{
            this.tail.next=node;
            node.prev=this.tail;
            this.tail=node;
        }
    }
    
    public void remove(Node node){
        
        this.nodeMap.remove(node.key);
        if(node==this.head){
            this.head=this.head.next;
            if(this.head!=null){
                this.head.prev=null;
            }
        }
        else{
            node.prev.next=node.next;
            if(node.next!=null){
                node.next.prev=node.prev;
            }
            if(node==this.tail){
                this.tail=node.prev;
            }
        }
        node.next= node.prev=null;
    }
    
    class Node{
        int key, value;
        Node next, prev;
        
        Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }
    public void printList() {
        Node curr = this.head;
        while (curr != null) {
            System.out.print(curr.key + " : "+curr.value+", ");
            curr=curr.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        LRUCache2 lruCache2=new LRUCache2(2);

        lruCache2.set(4, 4);
        lruCache2.printList();

        lruCache2.set(3, 3);
        lruCache2.printList();

        lruCache2.set(2, 2);
        lruCache2.printList();

        lruCache2.set(1, 1);
        lruCache2.printList();

        System.out.println("Accessing value of key: "+lruCache2.get(1));
        lruCache2.printList();
        System.out.println("Accessing value of key: "+lruCache2.get(2));
        System.out.println("Accessing value of key: "+lruCache2.get(3));
        System.out.println("Accessing value of key: "+lruCache2.get(4));

        lruCache2.printList();
    }
}