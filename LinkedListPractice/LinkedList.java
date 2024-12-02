package LinkedListPractice;

public class LinkedList {

    Node head;
    class Node{

        int value;
        Node next;

        public Node(int value){
            this.value=value;
            this.next=null;
        }
    }

    // Write a function to insert a new node at the end of the linkedlist
    public void append(int value){

        // So the first think we should check is if the head is null. if the head is null then this node will be the first node
        if (head==null){
            head= new Node(value);
            System.out.println("First node added!!: "+value);
            return;
        }
        // If the head is not null, the we will have to traverse the whole linked list to the end of the linkedlist and add there
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        Node newNode= new Node(value);
        temp.next=newNode;
        System.out.println("New node added!!: "+value);
    }
    // Write a function to insert a node at the starting of the list
    public void insertAtBegin(int value){

        // To insert the node at begining of an already existing linked list the next of new node should be head and then the new node should be head
        Node node= new Node(value);
        node.next=head;
        head=node;
    }
    // Print the linked list
    public void printLinkedList(Node head){

        Node temp=head;
        while(temp!=null){
            System.out.print(temp.value+" ");
            temp=temp.next;
        }
        System.out.println("");
    }
    // Check for loop in a linked list and count for no of nodes in the loop
    public int countNodesinLoop(Node head) {
        // We will take 2 ptrs slow and fast both starting from head. We will increment the slow by 1 and fast by 2. If there a loop in the linked list then somewhere there the two ptrs will meet. Now to count no of noes in the loop we can start counting the nodes from the meeting point untill we again reach the meeting point.
        Node slow=head;
        Node fast=head;
        int res=0;
        while(slow!=null && fast!=null && fast.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
            
            if(slow==fast){
                res=getCount(slow);
                break;
            }
        }
        return res;
    }
    public int getCount(Node slow){
        int len=1;
        Node temp=slow;
        while(temp.next!=slow){
            len++;
            temp=temp.next;
        }
        return len;
    }
    public static void main(String[] args) {
        
        LinkedList linkedList= new LinkedList();
        int []arr={10,20,30,40};
        // Insert the contents of arr into the linkedlist
        for(int num: arr){
            linkedList.append(num);
        }
        linkedList.printLinkedList(linkedList.head);

        int num=50;
        System.out.println("Inserting num at the begining of linked list: ");
        linkedList.insertAtBegin(num);
        linkedList.printLinkedList(linkedList.head);
    }
}
