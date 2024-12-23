package LinkedListPractice;

import java.util.HashMap;

public class LRUCache {

    Node head;
    Node tail;
    int capacity;
    HashMap<Integer, Node> nodeMap = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    class Node {
        int key, value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public void printList() {
        Node curr = this.head;
        while (curr != null) {
            System.out.print(curr.key + " : "+curr.value+" ");
            curr=curr.next;
        }
        System.out.println();
    }

    public void insert(Node node) {
        // The new node has to be always inserted at the start of the linked list
        this.nodeMap.put(node.key, node);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            // When the list is not empty
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
    }

    public void remove(Node node) {
        this.nodeMap.remove(node.key);
        // The removal has 2 scenarios. When we encounter a key which is already present
        // in the linked list we generally remove it from the position in the linked
        // list and then insert it in the front of the linked list. Or when the linked
        // list full we remove the least recently used node ie the tail node from the
        // linked list
        if (node == this.tail) {
            if(this.tail.prev!=null){
                this.tail = this.tail.prev;
                this.tail.next = null;
            }else{
                this.tail=this.head=null;
            }
            
            
        } else {
            if(node.prev!=null){
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            else{
                this.head=head.next;
                head.prev=null;
            }
            
        }
    }

    public int get(int key) {
        // So when we try to get a node we check if thats present in the map. If not
        // present we return -1. If present as the key is accesed it has to be moved to
        // the front of the linked list as it is the most recently used data
        if (this.nodeMap.containsKey(key)) {
            // Remove the node from the usual position
            Node node = this.nodeMap.get(key);
            remove(node);
            insert(node);
            return node.value;
        } else {
            return -1;
        }

    }

    // Function for storing key-value pair.
    public void set(int key, int value) {
        // your code here
        // So in this case before inserting we check if the value is already present in
        // the cache. If present we remove it from its current position and bring it to
        // the begining of the linked list. If not we have to insert it. So we check if
        // the cache is full... if full we remove the least recently used item and
        // insert our item else we directly insert at the begining
        Node node= new Node(key, value);
        if (this.nodeMap.containsKey(key)) {
            //Node node = this.nodeMap.get(key);
            remove(this.nodeMap.get(key));
            insert(node);
        } else {
            
            if (this.nodeMap.size() == this.capacity) {
                remove(this.tail);
            }
            insert(node);
        }
    }
    public static void main(String[] args) {
        LRUCache lruCache= new LRUCache(2);
        //int []arr={10,20,10,30,40,50,30,40,60,30};
        lruCache.set(1, 2);
        lruCache.printList();
        lruCache.set(2, 3);
        lruCache.printList();
        lruCache.set(1, 5);
        lruCache.printList();
        lruCache.set(4, 5);
        lruCache.printList();
        lruCache.set(6, 7);
        lruCache.printList();
        System.out.println("Accessing value of key: "+lruCache.get(4));
        lruCache.printList();
        lruCache.set(1, 2);
        lruCache.printList();
        System.out.println("Accessing value of key: "+lruCache.get(3));
        lruCache.printList();
        System.out.println("Accessing value of key: "+lruCache.get(1));
        lruCache.printList();
    }
}
