package LinkedListPractice;

public class DoublyLinkedList {

    Node head;

    class Node{
        int value;
        Node next;
        Node prev;
        public Node(int value) {
            this.value = value;
        }    
    }

    public Node insertAtBegin(int val){
        Node newNode=new Node(val);
        newNode.next=head;
        if(head!=null){
            head.prev=newNode;
        }
        head=newNode;
        return head;
    }
    public Node insertAtEnd(int val){
        
        if(head==null){
            return insertAtBegin(val);
        }
        else{
            Node temp=head;
            Node newNode=new Node(val);
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=newNode;
            newNode.prev=temp;
        }
        return head;
    }
    public void printList(Node head){
        Node temp=head;
        while(temp!=null){
            System.out.print(temp.value+" ");
            temp=temp.next;
        }
        System.out.println();
    }
    /*
        Given a doubly-linked list, a position p, and an integer x. The task is to add a new node with value x at the position just after pth node in the doubly linked list and return the head of the updated list.
    */
    public Node insertAfterPos(Node head, int p, int x) {
        // Your code here
        Node temp=head;
        for(int i=0; i<=p-1 && temp!=null;i++ ){
            temp=temp.next;
        }
        Node node= new Node(x);
        node.next=temp.next;
        node.prev=temp;
        temp.next=node;
        return head;
    }
    // Delete the head of a doubly linked list
    public Node deleteHead(){
        if(head==null || head.next==null){
            return null;
        }else{
            head=head.next;
            head.prev=null;
            return head;
        }
    }
    // Reverse a doubly linked list
    public Node reverse(){
        // WE JUST NEED TO REVERSE THE NEXT AND PREV POINTERS IE THE CONNECTIONS TO REVERSE THE LINKED LIST
        if(head==null || head.next==null){
            return head;
        }
        Node temp=head;
        Node previous=null;
        while(temp!=null){
            previous=temp.prev;
            temp.prev=temp.next;
            temp.next=previous;
            temp=temp.prev;
        }
        System.out.println(previous.value);
        head=previous.prev;
        return head;

    }
    // Delete the last node of the doubly linked list
    public Node deleteLast(){
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.prev.next=null;
        return head;
    }
    public static void main(String[] args) {
        
        DoublyLinkedList doublyLinkedList= new DoublyLinkedList();
        // Insert at the begining
        doublyLinkedList.printList(doublyLinkedList.insertAtEnd(10));
        doublyLinkedList.printList(doublyLinkedList.insertAtBegin(20));

        // insert at the end
        doublyLinkedList.printList(doublyLinkedList.insertAtEnd(30));
        doublyLinkedList.printList(doublyLinkedList.insertAtEnd(40));
        doublyLinkedList.printList(doublyLinkedList.insertAtEnd(50));
        doublyLinkedList.printList(doublyLinkedList.insertAtEnd(60));

        doublyLinkedList.printList(doublyLinkedList.deleteHead());
        doublyLinkedList.printList(doublyLinkedList.deleteHead());

        doublyLinkedList.printList(doublyLinkedList.reverse());

        // Deleting the last node
        doublyLinkedList.printList(doublyLinkedList.deleteLast());
        
    }
    
}
