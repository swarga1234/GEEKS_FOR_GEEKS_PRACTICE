package Stacks;


//Implement a stack using Linked list
public class MyStackLL {

    Node head;
    private int size;

    private class Node{
        int data;
        Node next;

        public Node(int data){
            this.data=data;
        }
    }   
    
    public void push(int val){
        if(head==null){
            head=new Node(val);
        }
        else{
            Node temp= new Node(val);
            temp.next=head;
            head=temp;
        }
        size++;
    }
    public int pop(){
        if(head==null){
            System.out.println("The stack is empty");
            return -1;
        }
        int res=head.data;
        head=head.next;
        size--;
        return res;
    }
    public int peek(){
        if(head==null){
            System.out.println("The stack is empty");
            return -1;
        }
        return head.data;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty()
    {
        if(head==null){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MyStackLL stackLL= new MyStackLL();
        System.out.println("A");
        stackLL.push(10);
        stackLL.push(20);
        System.out.println("The top most element of the stack is: "+stackLL.peek());
        System.out.println("The removed element of the stack is: "+stackLL.pop());
        stackLL.push(30);
        System.out.println("The top most element of the stackLL is: "+stackLL.peek());
        System.out.println("The size of the stack is: "+stackLL.size());
        System.out.println("The removed element of the stack is: "+stackLL.pop());
        System.out.println("The removed element of the stack is: "+stackLL.pop());
        System.out.println("Is the stack empty: "+stackLL.isEmpty());
    }
    
}
