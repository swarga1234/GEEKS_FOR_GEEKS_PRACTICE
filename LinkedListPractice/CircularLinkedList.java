package LinkedListPractice;

public class CircularLinkedList {

    Node head;
    // In circular linked list the last node points to the head.
    class Node{
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }    
    }

    // Print contents of the list
    public void printList(Node head){

        if(head==null){
            // If the list is empty don't print anything
            System.out.println();
            return;
        }
        Node temp=head;
        do{
            System.out.print(temp.value+" ");
            temp=temp.next;
        }while(temp!=head);
        System.out.println();
    }
    // Insert at the begining of the circular linkedlist
    public Node insertAtBegin(int val){
        // The naive method will be to travese through the list to the last node and add the new node between the last node and the head. 
        // Another approach which is quite efficient is mantaining two ptrs head and tail pointing to the first and the last node respectively. This is a O(1) solution. Another O(1) solution would be to insert a node after the head and swap the value of head wth the new node
        Node newNode= new Node(val);
        if(head==null){
            // The linkedlist is empty... insert it as the first node
            head=newNode;
            newNode.next=head;
            return head;
        }
        else{
            
            newNode.next=head.next;
            head.next=newNode;

            // Swap the values
            int data=head.value;
            head.value=newNode.value;
            newNode.value=data;

        }
        return head;
    }
    public Node insertAtEnd(int val){
        // same as insert at begining. The only difference is in the inserAtBegin our new node becomes the head and in this the old head remains.
        Node newNode= new Node(val);
        if(head==null){
            // The linkedlist is empty... insert it as the first node
            head=newNode;
            newNode.next=head;
            return head;
        }
        else{
            
            newNode.next=head.next;
            head.next=newNode;

            // Swap the values
            int data=head.value;
            head.value=newNode.value;
            newNode.value=data;
            head=head.next;
            return head;
        }
        
    }
    // Delete head of the circular linked list
    public Node deleteHead(){
        // There is a more intuitive way to find the last node firt and then delete the next node which is head. This solution takes O(n). A better solution should be to copy the value of the head.next node to head and then delete the head.next node.

        if(head==null){
            return null;
        }
        // if the list has only 1 node.. return null
        if(head.next==head){
            return null;
        }
        else{
            head.value=head.next.value;
            head.next=head.next.next;
            return head;
        }
    }
    // Delete kth node of circular linkedlist
    public Node deleteKthNode(int k){
        if(head==null){
            return null;
        }
        if(k==1){
            return deleteHead();
        }
        else{
            Node curr=head;
            for(int i=1;i<=k-2;i++){
                curr=curr.next;
            }
            curr.next=curr.next.next;
            return head;   
        }
    }
    public static void main(String[] args) {

        CircularLinkedList circularLinkedList= new CircularLinkedList();
        circularLinkedList.printList(circularLinkedList.insertAtBegin(20));
        circularLinkedList.printList(circularLinkedList.insertAtBegin(30));
        circularLinkedList.printList(circularLinkedList.insertAtBegin(40));
        circularLinkedList.printList(circularLinkedList.insertAtEnd(50));
        circularLinkedList.printList(circularLinkedList.insertAtEnd(60));
        circularLinkedList.printList(circularLinkedList.insertAtBegin(70));
        circularLinkedList.printList(circularLinkedList.deleteHead());
        circularLinkedList.printList(circularLinkedList.deleteHead());
        circularLinkedList.printList(circularLinkedList.deleteKthNode(3));
        
    }
    
}
