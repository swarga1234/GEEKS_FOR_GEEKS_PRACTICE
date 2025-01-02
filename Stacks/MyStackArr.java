package Stacks;

public class MyStackArr {

    private int top=-1;
    private int cap;
    private int []arr;

    public MyStackArr(int cap ){
        this.cap=cap;
        this.arr= new int[cap];
    }
    public void push(int val){
        if(top==(cap-1)){
            System.out.println("The stack is already full!");
            return;
        }
        arr[++top]=val;
    }
    //remove the topmost element of the stack
    public int pop(){
        if(top==-1){
            System.out.println("The stack is empty!!");
            return Integer.MAX_VALUE;
        }
        int res=arr[top];
        top--;
        return res;
    }
    //Return the top most element of the stack
    public int peek(){
        if(top==-1){
            System.out.println("The stack is empty!!");
            return Integer.MAX_VALUE;
        }
        return arr[top];
    }

    //Return the current size of the stack
    public int size(){
        return top+1;
    }

    //Check if the stack is empty
    public boolean isEmpty(){
        if(top==-1){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        
        MyStackArr stack= new MyStackArr(4);
        stack.push(10);
        stack.push(20);
        System.out.println("The top most element of the stack is: "+stack.peek());
        System.out.println("The removed element of the stack is: "+stack.pop());
        stack.push(30);
        System.out.println("The top most element of the stack is: "+stack.peek());
        System.out.println("The size of the stack is: "+stack.size());
        System.out.println("The removed element of the stack is: "+stack.pop());
        System.out.println("The removed element of the stack is: "+stack.pop());
        System.out.println("Is the stack empty: "+stack.isEmpty());
    }
    
}
