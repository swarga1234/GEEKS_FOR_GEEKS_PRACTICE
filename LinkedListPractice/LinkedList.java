package LinkedListPractice;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {

    Node head;

    class Node {

        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    // Write a function to insert a new node at the end of the linkedlist
    public void append(int value) {

        // So the first think we should check is if the head is null. if the head is
        // null then this node will be the first node
        if (head == null) {
            head = new Node(value);
            System.out.println("First node added!!: " + value);
            return;
        }
        // If the head is not null, the we will have to traverse the whole linked list
        // to the end of the linkedlist and add there
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        Node newNode = new Node(value);
        temp.next = newNode;
        System.out.println("New node added!!: " + value);
    }

    // Write a function to insert a node at the starting of the list
    public void insertAtBegin(int value) {

        // To insert the node at begining of an already existing linked list the next of
        // new node should be head and then the new node should be head
        Node node = new Node(value);
        node.next = head;
        head = node;
    }

    // Print the linked list
    public void printLinkedList(Node head) {

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println("");
    }

    // Print the linked list using recursion
    public void printLinkedListRecursion(Node head) {
        if (head == null) {
            System.out.println("");
            return;
        }
        System.out.print(head.value + " ");
        printLinkedListRecursion(head.next);
    }

    // Check for loop in a linked list and count for no of nodes in the loop
    public int countNodesinLoop(Node head) {
        // We will take 2 ptrs slow and fast both starting from head. We will increment
        // the slow by 1 and fast by 2. If there a loop in the linked list then
        // somewhere there the two ptrs will meet. Now to count no of noes in the loop
        // we can start counting the nodes from the meeting point untill we again reach
        // the meeting point.
        Node slow = head;
        Node fast = head;
        int res = 0;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                res = getCount(slow);
                break;
            }
        }
        return res;
    }

    public int getCount(Node slow) {
        int len = 1;
        Node temp = slow;
        while (temp.next != slow) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    // Insert at a position in linked lsit
    public Node insertAtPos(int pos, int value) {

        // insert at 1st position
        Node newNode = new Node(value);
        if (pos == 1) {
            newNode.next = head;
            return newNode;
        }
        // Insert at any other given position
        Node temp = head;
        for (int i = 1; i <= pos - 2 && temp != null; i++) {
            temp = temp.next;
        }
        // If temp becomes null then surely we don't have the pos to insert. Like a
        // linkedlist has 3 nodes and you are trying to insert at 6th position. So the
        // insertion should fail
        if (temp == null) {
            return head;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        return head;

    }

    public Node deleteAtBegin() {
        if (head == null) {
            return null;
        }
        head = head.next;
        return head;
    }

    // Delete the last node of the linked list
    public Node deleteLast() {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        return head;
    }

    // Searching in a linked list
    public int search(int val) {
        int pos = 1;
        for (Node temp = head; temp != null; temp = temp.next) {
            if (temp.value == val) {
                return pos;
            } else {
                pos++;
            }
        }
        return -1;
    }

    // Searching in a linked list using recursion
    public int searchRec(Node head, int val, int pos) {
        if (head == null) {
            return -1;
        }
        if (head.value == val) {
            return pos;
        } else {
            return searchRec(head.next, val, pos + 1);
            // if(res==-1){
            // return -1;
            // }
            // return(res+1);
        }
    }

    /*
     * Given two numbers, num1, and num2, represented by linked lists. The task is
     * to return the head of the linked list representing the sum of these two
     * numbers.
     * 
     * For example, the number 190 will be represented by the linked list,
     * 1->9->0->null, similarly 25 by 2->5->null. Sum of these two numbers is 190 +
     * 25 = 215, which will be represented by 2->1->5->null. You are required to
     * return the head of the linked list 2->1->5->null.
     * 
     * Note: There can be leading zeros in the input lists, but there should not be
     * any leading zeros in the output list.
     */
    public Node addTwoLists(Node num1, Node num2) {
        // code here
        // return head of sum list
        // Lets use the extra space first. We will create a res linkedlist to store the
        // sum of num1 and num2. First we will reverse both the lists and then add them
        // and again reverse the res.

        num1 = reverse(num1);
        num2 = reverse(num2);

        Node res = null;
        int carry = 0;
        Node curr = res;
        // So we add the nos node by node. This addition has to stop when both the
        // linkedlists have been traversed and there is no carry.
        while (num1 != null || num2 != null || carry != 0) {
            int sum = carry;

            if (num1 != null) {
                sum += num1.value;
                num1 = num1.next;
            }
            if (num2 != null) {
                sum += num2.value;
                num2 = num2.next;
            }
            Node newNode = new Node(sum % 10);
            carry = sum / 10;

            if (res == null) {
                res = newNode;
                curr = newNode;
            } else {
                curr.next = newNode;
                curr = curr.next;
            }

        }
        res = reverse(res);
        // This is to remove any leading zeroes in the output
        while (res != null && res.value == 0) {
            res = res.next;
        }
        return res;
    }

    public Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = null;
        while (curr != null) {

            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // Swap Kth nodes from end
    public Node swapKthNode(Node head, int k) {
        // code here
        // Find the node at kth position first
        if (head == null) {
            return null;
        }
        // count the no of nodes
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        Node curr = head;
        for (int i = 0; i <= k - 2 && curr != null; i++) {
            curr = curr.next;
        }
        if (curr == null) {
            return head;
        }

        Node curr2 = head;
        for (int i = 1; i < count - k && curr2 != null; i++) {
            curr2 = curr2.next;
        }
        if (curr2 == null) {
            return head;
        }
        if (k == 1) {
            // temp=curr;
            curr2.next.next = head.next;
            head = curr2.next;

            curr.next = null;
            curr2.next = curr;

        } else {
            Node temp1 = curr;
            Node temp2 = curr2;
            curr.next = curr2.next.next;
            curr2.next = curr;

            temp2.next = temp1.next.next;
            temp1.next = temp2;

        }
        return head;
    }

    public Node sortedInsert(int val) {
        // Insert a new value in to a already sorted linked list such that the linked
        // list remains sortedafter the insertion
        Node temp = new Node(val);
        if (head == null) {
            // Linked list is empty
            head = temp;
            return head;
        }
        if (val < head.value) {
            // The value to be inserted is lesser than the value of the head node
            temp.next = head;
            head = temp;
            return head;
        }
        Node curr = head;
        while (curr.next != null && curr.next.value <= val) {
            curr = curr.next;
        }
        temp.next = curr.next;
        curr.next = temp;
        return head;

        // TC O(pos), pos is the pos where the no has to be inserted. SC O(1)
    }

    public Node removeDuplicates(Node head) {
        // Remove duplicates from the sorted linkedlist

        if (head == null || head.next == null) {
            return head;
        }
        Node curr = head.next;
        Node prev = head;
        while (curr != null) {

            if (curr.value != prev.value) {
                prev = curr;
                curr = curr.next;
            } else {
                while (curr != null && curr.value == prev.value) {
                    curr = curr.next;
                }
                prev.next = curr;
                prev = curr;
                if (curr != null) {
                    curr = curr.next;
                }

            }

        }
        return head;
    }

    public int getMidNdode(Node head) {
        // For getting the middle node of a linked list we can maintain 2 ptrs slow and
        // fast. Fast will move twice the speed of slow. If fast reaches the end of the
        // linked list then slow will be at the middle of the linkedlist

        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.value;

    }

    // Get the nth node from the end
    public int getNthNodeFromEnd(int n) {
        if (this.head == null) {
            // Here -1 represents the linkedlist does not even has n nodes
            return -1;
        }
        Node first = this.head;
        for (int i = 1; i <= n; i++) {
            if (first == null) {
                return -1;
            }
            first = first.next;

        }
        Node second = this.head;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second.value;
    }

    // Reverse a linked list using recursion method1
    public Node reverseRec1(Node head) {

        // This solution is based on the principal that lets say we have nodes from 1 to
        // n. like x1,x2,x3......,xn. Now if we reverse the linked list from x2 to xn
        // and then reverse the link of node x1 we have a reversed linked list

        if (head == null || head.next == null) {
            // This is for an empty linked list or a linked list with only 1 node
            return head;
        }
        Node restHead = reverseRec1(head.next);
        Node restTail = head.next;
        restTail.next = head;
        head.next = null;
        return restHead;
    }

    // Reverse a linked list using recursion method2
    public Node reverseRec2(Node curr, Node prev) {
        // This is very similar to the iterative way
        if (curr == null) {
            return prev;
        }
        Node next = curr.next;
        curr.next = prev;
        return reverseRec2(next, curr);

    }

    // 10 20 30 40 50 60 70
    // 30 20 10 60 50 40 70
    // Reverse linked lists in groups of size k
    public Node reverseK(Node head, int k) {
        Node curr = head, prevFirst = null;
        boolean firstPass = true;
        while (curr != null) {

            Node first = curr, prev = null;
            int count = 0;

            while (curr != null && count < k) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;
            }
            // In the first iteration we will reverse the first k nodes.So the kth node
            // should be the head after the whole linked list is reversed this way.
            if (firstPass) {
                head = prev;
                firstPass = false;
            } else {
                prevFirst.next = prev;
            }
            prevFirst = first;
        }
        return head;
    }

    // Remove a cycle/loop in the linked list
    public void removeLoop(Node head) {
        if (head == null || head.next == null) {
            // Either the list is empty or has a single node with no cycle
            return;
        }

        if (head.next == head) {
            // The list has only one node pointing to itself thus forming a loop
            head.next = null;
            return;
        }

        Node slow_p = head, fast_p = head;
        // First detect the loop usinf floyd's cycle

        while (fast_p != null && fast_p.next != null) {
            slow_p = slow_p.next;
            fast_p = fast_p.next.next;

            if (slow_p == fast_p) {
                // A loop is detected... break
                break;
            }
        }
        // Now as a loop is detected so we need to remove it now. So the way to do is we
        // will reset the slow_p to head. Now we will move the slow_p and fast_p by each
        // time untill they meet. So mathematically when they meet, they will meet at
        // the start of the cycle. Thus if we stop our while loop at the condition when
        // slow_p.next==fast_p.next, then we can do fast_p.next=null to break the loop.
        // The only time this trick fails is when the starting node of the cycle is
        // head/ its a circular linked list. In that case we need to explicitly move the
        // fast ptr to the end of the linked list and change its next to null.
        if (fast_p == null) {
            while (fast_p.next != head) {
                fast_p = fast_p.next;
            }
            fast_p.next = null;
            return;
        }
        // Now as we have handled the case for when the cycle starts at head.. we can
        // now focus on the general logic.
        slow_p = head;
        while (slow_p.next != fast_p.next) {
            slow_p = slow_p.next;
            fast_p = fast_p.next;
        }
        fast_p.next = null;
    }

    // Segregate the even and odd valued nodes in a linked list.
    /*
     * example: 5->10->7->3->2
     * output: 10->2->5->7->3
     * 
     * The relative order of the odd and even nodes in the orginal list should be
     * maintained in the output list. Also the output list should have the even
     * nodes first followed by the odd nodes
     */
    public Node segregate(Node head) {

        Node os = null; // tracks the starting node of the odd nos
        Node oe = null; // tracks the ending node of the odd nos
        Node es = null; // tracks the starting node of the even nos
        Node ee = null; // tracks the starting node of the even nos

        // Now we will traverse each node and check if the value is even / odd. If even
        // then for the first even node we will mark it as es and ee. Same for the odd
        // node. The subsecuent even nodes wil be added to the ee and the odd nodes to
        // oe. After the whole list is proccessed as we need even nodes followed by odd
        // nodes we will attach os to to ee.

        Node curr = head;
        while (curr != null) {

            int num = curr.value;
            if (num % 2 == 0) {

                if (es == null) {
                    es = curr;
                    ee = curr;
                } else {
                    ee.next = curr;
                    ee = ee.next;
                }
            } else {
                if (os == null) {
                    os = curr;
                    oe = curr;
                } else {
                    oe.next = curr;
                    oe = oe.next;
                }
            }
            curr = curr.next;
        }
        // Once all the nodes are traversed then we need to join the os with ee.next.
        // But there may be scenario where there are no odd nos in the linked list or no
        // even nos in the linked list. Then our original list is the answer. It should
        // be unaltered.
        if (os == null || es == null) {
            return head;
        }
        ee.next = os;
        oe.next = null;
        head = es;
        return head;
    }

    // Find the intersection point of 2 linked lists
    public int findIntersectionPoint(Node head1, Node head2) {

        // The head1 and head2 forms a Y shaped linked list which in like Y both the
        // linked lists intersect at a point. How to find that? Lets count the no of
        // nodes in head1 and head2 linked lists. Now take the abs(count1-count2).
        // Traverse the first abs(count1-count2) nodes of the longer list, that would
        // give both the list a level playing field. Now traverse both of them
        // simultaneously. Anywhere the two nodes are equal is a intersection point.

        int count1 = getcountNodes(head1);
        int count2 = getcountNodes(head2);

        int diff = Math.abs(count1 - count2);
        Node curr1 = head1;
        Node curr2 = head2;
        if (count1 > count2) {
            // curr1=head1;
            for (int i = 1; i <= diff; i++) {
                curr1 = curr1.next;
            }
        } else {
            // curr2=head2;
            for (int i = 1; i <= diff; i++) {
                curr2 = curr2.next;
            }
        }
        // Once both curr1 and curr2 are at same distance from the intersection point
        while (curr1 != null && curr2 != null) {
            if (curr1 == curr2) {
                return curr1.value;
            }
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        // TC O(count1+count2), SC O(1)
        return -1; // -1 indicates no intersection point
    }

    public int getcountNodes(Node head) {
        Node curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    public static Node mergeSortedLinkedLists(Node head1, Node head2) {

        if (head1 == null) {
            return head1;
        }
        if (head2 == null) {
            return head2;
        }
        Node mergedHead = null;
        Node tail = null;
        Node curr1 = head1, curr2 = head2;
        if (head1.value <= head2.value) {
            mergedHead = tail = head1;
            curr1 = curr1.next;
        } else {
            mergedHead = tail = head2;
            curr2 = curr2.next;
        }
        while (curr1 != null && curr2 != null) {
            if (curr1.value <= curr2.value) {
                tail.next = curr1;
                tail = curr1;
                curr1 = curr1.next;
            } else {
                tail.next = curr2;
                tail = curr2;
                curr2 = curr2.next;
            }
        }
        if (curr1 == null) {
            tail.next = curr2;
        } else {
            tail.next = curr1;
        }

        return mergedHead;
    }

    public static Node mergeKLists(List<Node> arr) {

        Node orgHead = arr.get(0);
        int k = arr.size();
        for (int i = 1; i < k; i++) {
            orgHead = mergeSortedLinkedLists(orgHead, arr.get(i));
        }
        return orgHead;
    }

    // Check if a linked list is palindrome or not
    public boolean checkPalindrom() {
        // First find out the middle of the linked list. Then reverse the 2nd half of
        // the linked list. Then compare the nodes from head to mid with mid+1 to end.

        if (this.head == null) {
            return false;
        }
        if (this.head.next == null) {
            return true;
        }
        // Find the mid point
        Node slow = this.head, fast = this.head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // Node midHead=reverse(slow.next);
        if (slow.next != null && slow.next.next != null) {
            Node prev = null;
            Node curr = slow.next;
            while (curr != null) {
                System.out.println("reversing");
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            slow.next=prev;
            System.out.println("Done reversing!! "+prev.value);
            printLinkedList(this.head);
            Node midHead = prev;
            curr = this.head;
            while (midHead != null) {
                //System.out.println("comparing vals "+curr.value+" and "+midHead.value);
                if (curr.value != midHead.value) {
                    return false;
                }
                curr=curr.next;
                midHead=midHead.next;
                //System.out.println("Next midhaed val: "+midHead.value);
            }
        }else if(slow.next==null){
            //The list probably has 2 nodes only.. so compare the 2 nodes if they are equal
            if(this.head.value!=slow.value ){
                return false;
            }
        }else{
            //The list only has 3/4 nodes
            if(this.head.value!=slow.next.value || this.head.next.value!=slow.value){
                return false;
            }
        }   

        return true;
    }
    //Pairwise swapping of nodes in  Linked list
    public Node pairWiseSwap(){

        //If there are no nodes or only one node then no need to swap
        if(this.head==null || this.head.next==null){
            return head;
        }
        //Now we will explicitly handle the swapping of first 2 nodes
        Node curr=this.head.next.next;
        Node prev=this.head;
        this.head=this.head.next;
        this.head.next=prev;

        //After we have explicitly swapped the first 2 nodes we will now swap the rest of the nodes
        while(curr!=null && curr.next!=null){
            prev.next=curr.next;
            prev=curr;
            Node next= curr.next.next;
            curr.next.next=curr;
            curr=next;
        }
        //In case of odd the last node will be curr and we will connect the 2nd last node with the last node. In case of even nos of nodes the curr will be null thus making prev.next=null
        prev.next=curr;
        return this.head;
    }

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        int[] arr = { 10, 20, 30, 40 };
        // Insert the contents of arr into the linkedlist
        for (int num : arr) {
            linkedList.append(num);
        }
        linkedList.printLinkedList(linkedList.head);

        int num = 50;
        System.out.println("Inserting num at the begining of linked list: ");
        linkedList.insertAtBegin(num);
        linkedList.printLinkedList(linkedList.head);
        System.out.println("Inserting num at the begining of linked list: ");
        linkedList.insertAtBegin(30);
        linkedList.printLinkedList(linkedList.head);

        // print linked list using recursion
        Node temp = linkedList.head;
        linkedList.printLinkedListRecursion(temp);

        int pos = 3;
        linkedList.head = linkedList.insertAtPos(pos, 60);
        linkedList.printLinkedList(linkedList.head);

        // Delete the first node of the linked list
        linkedList.printLinkedList(linkedList.deleteAtBegin());
        linkedList.printLinkedList(linkedList.deleteAtBegin());

        System.out.println("Deleting the last node!!");
        linkedList.printLinkedList(linkedList.deleteLast());

        System.out.println("Deleting the last node!!");
        linkedList.printLinkedList(linkedList.deleteLast());

        // Searching a node linkedlist
        int key = 20;
        System.out.println("The key: " + key + " is present at: " + linkedList.search(key));
        temp = linkedList.head;
        System.out.println("The key: " + key + " is present at: " + linkedList.searchRec(temp, 10, 1));

        LinkedList ll1 = new LinkedList();
        ll1.append(4);
        ll1.append(5);

        LinkedList ll2 = new LinkedList();
        ll2.append(3);
        ll2.append(4);
        ll2.append(5);

        LinkedList ll3 = new LinkedList();
        ll3.head = ll3.addTwoLists(ll1.head, ll2.head);
        ll3.printLinkedList(ll3.head);

        LinkedList ll4 = new LinkedList();
        ll4.append(10);
        ll4.append(20);
        ll4.append(30);
        ll4.append(40);

        // Print the current linked list
        ll4.printLinkedList(ll4.head);

        // Inserting in to the sorted linked list
        ll4.printLinkedList(ll4.sortedInsert(35));
        ll4.printLinkedList(ll4.sortedInsert(5));
        ll4.printLinkedList(ll4.sortedInsert(50));

        LinkedList ll5 = new LinkedList();
        ll5.append(2);
        ll5.append(2);
        ll5.append(2);
        ll5.append(5);

        ll5.printLinkedList(ll5.head);
        ll5.printLinkedList(ll5.removeDuplicates(ll5.head));
        System.out.println("The value of middle node is: " + ll4.getMidNdode(ll4.head));

        System.out.println("The n th node from end is: " + ll4.getNthNodeFromEnd(6));

        ll4.head = ll4.reverseRec1(ll4.head);
        ll4.printLinkedList(ll4.head);
        // ll4.printLinkedList(ll4.head);
        ll4.head = ll4.reverseRec2(ll4.head, null);
        ll4.printLinkedList(ll4.head);
        // ll4.printLinkedList(ll4.head);
        ll4.head = ll4.reverseK(ll4.head, 3);
        ll4.printLinkedList(ll4.head);

        ll4.head = ll4.segregate(ll4.head);
        ll4.printLinkedList(ll4.head);

        int[] arr2 = { 10, 20, 30, 40, 45, 56, 60 };
        LinkedList ll6 = new LinkedList();
        for (int item : arr2) {
            ll6.append(item);
        }

        int[] arr3 = { 5, 15, 17, 18, 35 };
        LinkedList ll7 = new LinkedList();
        for (int item : arr3) {
            ll7.append(item);
        }
        System.out.println("Merging 2 sorted arrays!!");
        ll7.printLinkedList(LinkedList.mergeSortedLinkedLists(ll6.head, ll7.head));

        List<Node> nodeList = new ArrayList<>();

        int[] arr4 = { 1, 2, 3 };
        int[] arr5 = { 4, 5 };
        int[] arr6 = { 5, 6 };
        int[] arr7 = { 7, 8 };
        LinkedList ll8 = new LinkedList();
        for (int item : arr4) {
            ll8.append(item);
        }
        LinkedList ll9 = new LinkedList();
        for (int item : arr5) {
            ll9.append(item);
        }
        LinkedList ll10 = new LinkedList();
        for (int item : arr6) {
            ll10.append(item);
        }
        LinkedList ll11 = new LinkedList();
        for (int item : arr7) {
            ll11.append(item);
        }
        nodeList.add(ll8.head);
        nodeList.add(ll9.head);
        nodeList.add(ll10.head);
        nodeList.add(ll11.head);

        System.out.println("Merging k sorted linked lists: ");
        LinkedList ll12 = new LinkedList();
        ll12.head = LinkedList.mergeKLists(nodeList);
        ll12.printLinkedList(ll12.head);

        LinkedList ll13 = new LinkedList();
        ll13.append(1);
        ll13.append(2);
        ll13.append(1);
        //ll13.append(1);
        // ll13.append(3);


        System.out.println("The mid node is : " + ll13.getMidNdode(ll13.head));
        System.out.println("The linked list is palindrome: "+ll13.checkPalindrom());
        ll12.head=ll12.pairWiseSwap();
        System.out.println("After pairwise swapping of nodes!");
        ll12.printLinkedList(ll12.head);

    }
}
